package com.ahievran.yabanciOgrenciBasvuru.business.concretes;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.KisiKayitService;
import com.ahievran.yabanciOgrenciBasvuru.business.requests.CreateKisiKayitRequest;
import com.ahievran.yabanciOgrenciBasvuru.business.responses.ReCaptchaResponse;
import com.ahievran.yabanciOgrenciBasvuru.core.utilities.exceptions.EmailVerificationException;
import com.ahievran.yabanciOgrenciBasvuru.core.utilities.mapper.MapperService;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.KisiKayitLogRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.KisiKayitRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.RolRepository;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiKayit;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiKayitLog;
import com.ahievran.yabanciOgrenciBasvuru.entities.Rol;


@Service
public class KisiKayitManager implements KisiKayitService{	
	private KisiKayitRepository kisiKayitRepository;
	private MapperService mapperService;
	private RolRepository roleRepository;
    private Random random;
	private BCryptPasswordEncoder passwordEncoder;	 
	private JavaMailSender javaMailSender;
	private KisiKayitLogRepository kayitLogRepository;
	
	@Value("${recaptcha.secretKey}")
	private String recaptchaSecret;
	
	@Value("${recaptcha.url}")
	private String recaptchaServerUrl;
	

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	public KisiKayitManager(KisiKayitRepository kisiKayitRepository, MapperService mapperService, RolRepository roleRepository, Random random, @Lazy BCryptPasswordEncoder passwordEncoder, JavaMailSender javaMailSender, KisiKayitLogRepository kayitLogRepository) {
		this.kisiKayitRepository = kisiKayitRepository;
		this.mapperService = mapperService;
		this.roleRepository = roleRepository;
		this.random = random;
		this.passwordEncoder = passwordEncoder;
		this.javaMailSender = javaMailSender;
		this.kayitLogRepository = kayitLogRepository;
	}

	
	@Override
	public KisiKayit addKisiKayit(CreateKisiKayitRequest createKisiKayitRequest) throws Exception {
		KisiKayit dbKisiKayit = kisiKayitRepository.findByEmail(createKisiKayitRequest.getEmail());
		if(dbKisiKayit != null) {
			throw new Exception("Email mevcut");
		}
		
		KisiKayit kisiKayit = new KisiKayit();		
		kisiKayit = mapperService.forRequest().map(createKisiKayitRequest, KisiKayit.class);				
		kisiKayit.setDogrulamaKodu(generateVerificationCode());
		
		
		if(!sendVerifiacationCode(kisiKayit)) {
			return null;
		}
		kisiKayit.setPassword(passwordEncoder.encode(createKisiKayitRequest.getPassword()));
		kisiKayit.setDogrulandiMi(false);
		
		Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
        String formattedDateTime = dateFormat.format(currentDate);
        Date parsedDate = dateFormat.parse(formattedDateTime);
        
        kisiKayit.setKayitTarihi(parsedDate);
        
        Rol defaultRole = roleRepository.findByName("ogrenci");
        
        kisiKayit.getRoller().add(defaultRole);           		
		return kisiKayitRepository.save(kisiKayit);
	}

    private String generateVerificationCode() {
        int codeLength = 20;
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
	
	@Override
	@Async
	public boolean sendVerifiacationCode(KisiKayit kisiKayit) {
		String gonderilecekMailAdresi = kisiKayit.getEmail();
		String dogrulamaKodu = kisiKayit.getDogrulamaKodu();
		
		KisiKayitLog kisiKayitLog = new KisiKayitLog();
 
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = null;
			helper = new MimeMessageHelper(message, true);
	        helper.setTo(gonderilecekMailAdresi);
	        helper.setSubject("Kayıt mail doğrulama");
	        
	        String body = "<html>" +
	                "<body style=\"font-family: Arial, sans-serif;\">" +
	                "<h2 style=\"color: #007BFF;\">Kırşehir Ahi Evran Üniversitesi</h2>" +
	               "<p style=\"color: #333;\">Hesabınızı aşağıdaki linke tıklayarak doğrulayabilirsiniz.</p>" +
	                "<p style=\"color: #333;\">You can verify your account by clicking the link below.</p>" +
	                "<p style=\"color: #333;\">Your password : </p>" + kisiKayit.getPassword() + "<br>" +
	                "<a href='https://yosonline.ahievran.edu.tr/yb/verifyaccount?code=" + dogrulamaKodu + "' style='display: inline-block; padding: 10px 20px; background-color: #007BFF; color: #FFFFFF; text-decoration: none; border-radius: 5px;'>Hesabımı Doğrula</a>" +
	                "<p style=\"color: #333;\">İyi günler!</p>" +
	                "</body>" +
	                "</html>";
	        		
	        		
	      
	        helper.setText(body, true); 	       	        
	        javaMailSender.send(message);

	        // veri tabanına logla	      	        
	        kisiKayitLog.setIslemAyrinti(helper.getMimeMessage().toString());
	        kisiKayitLog.setGonderilenAdres(gonderilecekMailAdresi);
	        kisiKayitLog.setGonderimBasarıiliMi(true);	       	        
		} catch (Exception e) {
			e.printStackTrace(); 

			// veri tabanına log*la hatayı			        
	        kisiKayitLog.setGonderimBasarıiliMi(false);	        
	        kayitLogRepository.save(kisiKayitLog);
	        return false;
		}        				
		kayitLogRepository.save(kisiKayitLog);
		return true;
	}
	

	@Override
	public boolean verifyReCaptcha(String gRecaptchaResponse) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); 
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>(); 
		map.add("secret", recaptchaSecret); 
		map.add("response", gRecaptchaResponse); 
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<> (map, headers);
		
		ReCaptchaResponse response = restTemplate.postForObject(recaptchaServerUrl, request, ReCaptchaResponse.class);
		return response.isSuccess();
	}

	
	
	
	
	@Override
	public KisiKayit updateVerification(String code) {
		KisiKayit kisiKayit = kisiKayitRepository.findByDogrulamaKodu(code);
		if(kisiKayit != null) {
			kisiKayit.setDogrulandiMi(true);
			kisiKayitRepository.save(kisiKayit);
			return kisiKayit;
		}
		return null;
	}

	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		KisiKayit kisiKayit = kisiKayitRepository.findByEmail(username);
		if(kisiKayit == null) {
			throw new UsernameNotFoundException("Kullanıcı adı veya email yanlış (Password or email incorrect)");
		}		
		List<KisiKayitLog> kisiKayitLogList = kayitLogRepository.findByGonderilenAdres(username);
		KisiKayitLog kisiKayitLog = null;
		if(kisiKayitLogList != null) {
			kisiKayitLog = kisiKayitLogList.get(kisiKayitLogList.size() - 1);
		}
		if(kisiKayit != null && kisiKayitLog != null && kisiKayitLog.isGonderimBasarıiliMi() == true && kisiKayit.isDogrulandiMi() == false) {
			throw new EmailVerificationException("veya hesap doğrulaması yapılmadı. Posta kutunuzu kontrol edin");
		}
		
		
		return new org.springframework.security.core.userdetails.User(kisiKayit.getEmail(), kisiKayit.getPassword(),mapRolesToAuthorities(kisiKayit.getRoller()));		
	}
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}


	@Override
	public KisiKayit findByEmail(String email) {
		KisiKayit kisiKayit = kisiKayitRepository.findByEmail(email);
		if(kisiKayit == null)
			return null;
		return kisiKayit;
	}


	@Override
	public KisiKayit sendNewPassword(String email) throws Exception {
		try {
			String gonderilecekMailAdresi = email;
			String dogrulamaKodu = generateVerificationCodeForPassword();
			KisiKayit dbKisiKayit = kisiKayitRepository.findByEmail(email);
			if(dbKisiKayit != null) {
				dbKisiKayit.setPassword(passwordEncoder.encode(dogrulamaKodu));
				MimeMessage message = javaMailSender.createMimeMessage();
		        MimeMessageHelper helper = null;
				helper = new MimeMessageHelper(message, true);
		        helper.setTo(gonderilecekMailAdresi);
		        helper.setSubject("Şifre Yenileme");
		        
		        String body = "<html>" +
		                "<body style=\"font-family: Arial, sans-serif;\">" +
		                "<h2 style=\"color: #007BFF;\">Kırşehir Ahi Evran Üniversitesi</h2>" +
		               "<p style=\"color: #333;\">Yeni şifreniz ile giriş yapabilirsiniz</p>" +
		                "<p style=\"color: #333;\">You can log in with new password</p>" +
		                "<p style=\"color: #333;\">Passowd : </p>" + dogrulamaKodu + 
		                "<p style=\"color: #333;\">İyi günler!</p>" +
		                "</body>" +
		                "</html>";
		        			        			   
		        helper.setText(body, true); 	       	        
		        javaMailSender.send(message);       
		        kisiKayitRepository.save(dbKisiKayit);
		        return dbKisiKayit;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}				
		return null;
	}


	private String generateVerificationCodeForPassword() {
        int codeLength = 10;
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
	}



}
