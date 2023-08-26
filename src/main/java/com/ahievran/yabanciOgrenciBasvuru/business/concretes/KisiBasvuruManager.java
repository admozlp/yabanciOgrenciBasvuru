package com.ahievran.yabanciOgrenciBasvuru.business.concretes;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.KisiBasvuruService;
import com.ahievran.yabanciOgrenciBasvuru.business.requests.CreateBasvuruRequest;
import com.ahievran.yabanciOgrenciBasvuru.business.responses.AdminBasvurularResponse;
import com.ahievran.yabanciOgrenciBasvuru.business.responses.SehirlerResponse;
import com.ahievran.yabanciOgrenciBasvuru.core.utilities.mapper.MapperService;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.KisiBasvuruRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.KisiKayitRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.KisiProgramRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.ProgramRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.SehirRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.UlkelerRepository;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiBasvuru;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiDosya;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiKayit;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiProgram;
import com.ahievran.yabanciOgrenciBasvuru.entities.Program;
import com.ahievran.yabanciOgrenciBasvuru.entities.Sehir;
import com.ahievran.yabanciOgrenciBasvuru.entities.Ulke;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;

@Service
public class KisiBasvuruManager implements KisiBasvuruService {
	@Autowired
	private KisiBasvuruRepository kisiBasvuruRepository;
	@Autowired
	private KisiKayitRepository kisiKayitRepository;
	@Autowired
	private UlkelerRepository ulkelerRepository;
	@Autowired
	private SehirRepository sehirRepository;
	@Autowired
	private MapperService mapperService;
	@Autowired
	private ProgramRepository programRepository;
	@Autowired KisiProgramRepository kisiProgramRepository;
	@Autowired
    private Random random;

	
	
	@Value("${app.upload.dir:${user.home}}")
	public String IMAGES_FILE_PATH;
	
	@Override
	public CreateBasvuruRequest add(CreateBasvuruRequest createBasvuruRequest) throws Exception {		


		try {
//			KisiKayit dbKisiKayit =  kisiKayitRepository.findByEmail(createBasvuruRequest.getEmail());
//			KisiBasvuru dbKisiBasvuru = kisiBasvuruRepository.findByKisiKayitIdAndBasvuruYilAndBasvuruDonem(dbKisiKayit.getId());
//			if(dbKisiBasvuru != null)
//				return null;
			
			KisiBasvuru kisiBasvuru = new KisiBasvuru();
			createBasvuruRequest.setAd(createBasvuruRequest.getAd().toLowerCase());
			createBasvuruRequest.setSoyad(createBasvuruRequest.getSoyad().toLowerCase());
			try {
				mapperService.forRequest().map(createBasvuruRequest, kisiBasvuru);
			}catch(Exception e){
				
			}
								
			if(createBasvuruRequest.getDigerUlke() !=  null) {
				Ulke digerUlke = ulkelerRepository.findById(createBasvuruRequest.getDigerUlke()).get();
				kisiBasvuru.setDigerUlke(digerUlke);
			}			
			kisiBasvuru.setUlke(ulkelerRepository.findById(createBasvuruRequest.getUlke()).get());
			kisiBasvuru.setLiseninUlkesi(ulkelerRepository.findById(createBasvuruRequest.getLiseninUlkesi()).get());
			kisiBasvuru.setYasadigiUllke(ulkelerRepository.findById(createBasvuruRequest.getYasadigiUlke()).get());
			
			
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        kisiBasvuru.setDogumTarihi(dateFormat.parse(createBasvuruRequest.getDogumTarihi()));	        
	        kisiBasvuru.setLiseBaslangic(dateFormat.parse(createBasvuruRequest.getLiseBaslangic()));	        
	        kisiBasvuru.setLiseBitis(dateFormat.parse(createBasvuruRequest.getLiseBitis()));
	        
	        kisiBasvuru.setYasadigiSehirText(createBasvuruRequest.getYasadigiSehirText());
	        kisiBasvuru.setLiseSehirText(createBasvuruRequest.getLiseSehirText());
	        
	        boolean cinisyet;
	        if(createBasvuruRequest.getCinsiyet().equals("Erkek"))
	        	cinisyet = true;
	        else
	        	cinisyet = false;
	        kisiBasvuru.setCinsiyet(cinisyet);
	       
	        

	        int a = 1;
	        List<KisiProgram> tercihler = new ArrayList<>();
	        for(Integer program : createBasvuruRequest.getSecimler()) {
	        	if(program != null) {
			        Program secilenProgram = programRepository.findById(program).get();
			        KisiProgram tercih = new KisiProgram();
			        tercih.setKisiBasvuru(kisiBasvuru);
			        tercih.setProgram(secilenProgram);
			        tercih.setTercihSirasi(a);
			        tercihler.add(tercih);
			        a++;
	        	}
	        }
	        kisiBasvuru.setKisiProgram(tercihler);
	        

	        
	        List<KisiDosya> kisiDosyalari = new ArrayList<>();	       	        
	        if(createBasvuruRequest.getFoto() != null && !createBasvuruRequest.getFoto().isEmpty() ) {	        	
	        	KisiDosya kisiDosya1 = new KisiDosya();
	        	MultipartFile foto = createBasvuruRequest.getFoto();
	        	String path = generateUuuid();
	        	Files.copy(foto.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));		        
		        kisiDosya1.setDosyaYol(path);
		        kisiDosya1.setKisiBasvuru(kisiBasvuru);
		        kisiDosyalari.add(kisiDosya1);		        
	        }
	        if(createBasvuruRequest.getKimlikOn() != null  && !createBasvuruRequest.getKimlikOn().isEmpty()) {
		        KisiDosya kisiDosya2 = new KisiDosya();
		        MultipartFile kimlikOn = createBasvuruRequest.getKimlikOn();
		        String path = generateUuuid();
		        Files.copy(kimlikOn.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
		        kisiDosya2.setDosyaYol(path);
		        kisiDosya2.setKisiBasvuru(kisiBasvuru);
		        kisiDosyalari.add(kisiDosya2);
	        }
	        if(createBasvuruRequest.getKimlikArka() != null && !createBasvuruRequest.getKimlikArka().isEmpty()) {	
		        KisiDosya kisiDosya3 = new KisiDosya();
		        MultipartFile kimlikArka = createBasvuruRequest.getKimlikArka();
		        String path = generateUuuid();
		        Files.copy(kimlikArka.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
		        kisiDosya3.setDosyaYol(path);
		        kisiDosya3.setKisiBasvuru(kisiBasvuru);	 
		        kisiDosyalari.add(kisiDosya3);

	        }
	       if(createBasvuruRequest.getPasaportOn() != null && !createBasvuruRequest.getPasaportOn().isEmpty()) {	  
		        KisiDosya kisiDosya4 = new KisiDosya();
	    	   MultipartFile pasaportOn = createBasvuruRequest.getPasaportOn();
	    	   String path = generateUuuid();
		        Files.copy(pasaportOn.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
		        kisiDosya4.setDosyaYol(path);
		        kisiDosya4.setKisiBasvuru(kisiBasvuru);
		        kisiDosyalari.add(kisiDosya4);
	       }
	       if(createBasvuruRequest.getPasaportArka() != null && !createBasvuruRequest.getPasaportArka().isEmpty() ) {	 
		        KisiDosya kisiDosya5 = new KisiDosya();	        
		        MultipartFile pasaportArka = createBasvuruRequest.getPasaportArka();
		        String path = generateUuuid();
		        Files.copy(pasaportArka.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
		        kisiDosya5.setDosyaYol(path);
		        kisiDosya5.setKisiBasvuru(kisiBasvuru);
		        kisiDosyalari.add(kisiDosya5);
	       }
	       if(createBasvuruRequest.getDiplomaOn() != null && !createBasvuruRequest.getDiplomaOn().isEmpty()) {	  
		        KisiDosya kisiDosya6 = new KisiDosya();
		        MultipartFile diplomaOn = createBasvuruRequest.getDiplomaOn();
		        String path = generateUuuid();
		        Files.copy(diplomaOn.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
		        kisiDosya6.setDosyaYol(path);
		        kisiDosya6.setKisiBasvuru(kisiBasvuru);
		        kisiDosyalari.add(kisiDosya6);
	       }
	       if(createBasvuruRequest.getDiplomaArka() != null && !createBasvuruRequest.getDiplomaArka().isEmpty()){	   
		        KisiDosya kisiDosya7 = new KisiDosya();
		        MultipartFile diplomaArka = createBasvuruRequest.getDiplomaArka();
		        String path = generateUuuid();
		        Files.copy(diplomaArka.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));		 	   
		        kisiDosya7.setDosyaYol(path);
		        kisiDosya7.setKisiBasvuru(kisiBasvuru);
		        kisiDosyalari.add(kisiDosya7);
	       }
	       if(createBasvuruRequest.getTrYosSonuc() != null && !createBasvuruRequest.getTrYosSonuc().isEmpty()){	     
		        KisiDosya kisiDosya8 = new KisiDosya();
		        MultipartFile trYosSonuc = createBasvuruRequest.getTrYosSonuc();
		        String path = generateUuuid();
		        Files.copy(trYosSonuc.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
		        kisiDosya8.setDosyaYol(path);
		        kisiDosya8.setKisiBasvuru(kisiBasvuru);
		        kisiDosyalari.add(kisiDosya8);
	       }
	       if(createBasvuruRequest.getDekont() != null && !createBasvuruRequest.getDekont().isEmpty()){	     
		        KisiDosya kisiDosya9 = new KisiDosya();
		        MultipartFile dekont = createBasvuruRequest.getDekont();
		        String path = generateUuuid();
		        Files.copy(dekont.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
		        kisiDosya9.setDosyaYol(path);
		        kisiDosya9.setKisiBasvuru(kisiBasvuru);
		        kisiDosyalari.add(kisiDosya9);
	       }
	       
	       	kisiBasvuru.setKisiDosya(kisiDosyalari);
         
	        KisiKayit kisiKayit = kisiKayitRepository.findByEmail(createBasvuruRequest.getEmail());
	        kisiBasvuru.setKisiKayit(kisiKayit);
	        
	        kisiBasvuru.setBasvuruYil(Calendar.getInstance().get(Calendar.YEAR));
	        
//	        List<Integer> guz = new ArrayList<>(List.of(2, 3, 4, 5,6,7,8)); 
	        
	        kisiBasvuru.setBasvuruYil(Calendar.getInstance().get(Calendar.YEAR));
//	        int ay = Calendar.getInstance().get(Calendar.MONTH);
//	        if(guz.contains(ay))	
	        	kisiBasvuru.setBasvuruDonem(true);
//	        else
//	        	kisiBasvuru.setBasvuruDonem(true);
	        
			Date currentDate = new Date();	        
	        
	        kisiBasvuru.setBasvuruTarihi(currentDate);
	        
	        kisiBasvuru.setBasvuruHash(generateHash());
	        
	        kisiBasvuruRepository.save(kisiBasvuru);
	        return createBasvuruRequest;
	        	        
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getLocalizedMessage());
		}
	}

    private String generateHash() {
        int codeLength = 12;
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    
    @Override
	public List<Ulke> getAllCountries() {
		return ulkelerRepository.findAll();
	}

	@Override
	public List<SehirlerResponse> getSehirByUlke(int ulkeId) {
		List<Sehir> sehirler =  sehirRepository.findByUlkeId(ulkeId);
		
		List<SehirlerResponse> sehirlerResponses = sehirler.stream().map(sehir-> mapperService.forResponse().map(sehir, SehirlerResponse.class)).collect(Collectors.toList());
		
		return sehirlerResponses;
	}
	
	private String generateUuuid() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString().replace("-", "") + ".jpg";
        return uuidString;
	}
	
	@Override
	public List<KisiBasvuru> getAllKisiBasvuruByEmail(String email) {
		KisiKayit kisiKayit = kisiKayitRepository.findByEmail(email);
		if(kisiKayit != null) {
			int id = kisiKayit.getId();
			List<KisiBasvuru> kisiBasvurular = kisiBasvuruRepository.findByKisiKayitId(id);
			return kisiBasvurular;
		}
		return null;
	}
	
	@Override
	public Document getHtmlContent(KisiBasvuru kisiBasvuru, Document document, List<KisiDosya> kisiDosyalari) throws Exception {

        BaseFont unicodeFont = BaseFont.createFont("C:/eclipse-workspace/yabanciOgrenciBasvuru/src/main/resources/static/font/UTF8.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(unicodeFont, 12);
        
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.UNDERLINE);
        Font boldFont2 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        
        Paragraph hash = new Paragraph(kisiBasvuru.getBasvuruHash(), boldFont2);  
        hash.setSpacingBefore(40);
        hash.setSpacingAfter(20);
        hash.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(hash); 
        
        String logoPath = IMAGES_FILE_PATH + "/default/ahievranlogo.jpg";
        Image image = Image.getInstance(logoPath);
        image.scaleToFit(100, 100);
        image.setAlignment(Image.ALIGN_CENTER);
        image.setBorderWidth(5);
        image.setBorderColor(com.itextpdf.text.BaseColor.WHITE);

        
        
        Paragraph ahiEvran = new Paragraph("AHI EVRAN UNIVERSITY", boldFont2);
        ahiEvran.setAlignment(Element.ALIGN_CENTER);
        
   
     //////////////////////////////////////////////
        document.setMargins(50, 50, 50, 50); 

        Paragraph kisiselBilgiler = new Paragraph("Personal Information Of The Candidate", boldFont);        
        kisiselBilgiler.setSpacingBefore(20);
        kisiselBilgiler.setAlignment(Element.ALIGN_LEFT);
        Paragraph ad= new Paragraph("Name : " + kisiBasvuru.getAd(),font);  
        ad.setSpacingBefore(10);
        Paragraph soyad= new Paragraph("Surname: " + kisiBasvuru.getSoyad(),font);
        soyad.setSpacingBefore(5);
        Paragraph gender = new Paragraph("Gender : " + (kisiBasvuru.isCinsiyet() ? "Male" : "Female"),font);
        gender.setSpacingBefore(5);
        Paragraph uyruk= new Paragraph("Nationality: " + kisiBasvuru.getUlke().getIngilizceAd(),font);
        uyruk.setSpacingBefore(8);
        Paragraph anneAdi= new Paragraph("Mother name : " + kisiBasvuru.getAnneAdi(),font);
        anneAdi.setSpacingBefore(5);
        Paragraph babaAdi= new Paragraph("Fatger name : " + kisiBasvuru.getBabaAdi(),font);
        babaAdi.setSpacingBefore(5);
        Paragraph dogumYeri = new Paragraph("Place of birth : " + kisiBasvuru.getDogumYeri(),font);
        dogumYeri.setSpacingBefore(5);
        Paragraph dogumTarihi = new Paragraph("Date of birth : " + kisiBasvuru.getDogumTarihi().toString().substring(0, 10).replace('-', '.'),font);                
        dogumTarihi.setSpacingBefore(5);
        Paragraph vatandaslikSekli= new Paragraph("Citizenship type : " + kisiBasvuru.getVatandaslikSekli(),font);
        document.add(image);
        document.add(ahiEvran);
        document.add(kisiselBilgiler);        
        document.add(ad);
        document.add(soyad);
        document.add(gender);
        document.add(uyruk);
        document.add(anneAdi);
        document.add(babaAdi);
        document.add(dogumYeri);
        document.add(dogumTarihi);
        document.add(vatandaslikSekli);
                
        vatandaslikSekli.setSpacingBefore(5);
        if(kisiBasvuru.getDigerUlke() != null) {
        	Paragraph digerVatandaslik = new Paragraph("Other nationality: " + kisiBasvuru.getDigerUlke().getIngilizceAd().toString(),font);
        	digerVatandaslik.setSpacingBefore(5);
            document.add(digerVatandaslik);
        }
        
        if(kisiBasvuru.getDigerVatandaslikSekli() != null) {
        	Paragraph digerVatandaslikSekli = new Paragraph("Other type of citizenship: " + kisiBasvuru.getDigerVatandaslikSekli().toString(),font);
            digerVatandaslikSekli.setSpacingBefore(5);       
            document.add(digerVatandaslikSekli);	
        }
        
        //////////////////////////////////////////////////////
        
        Paragraph tercihler = new Paragraph("Preferred Programmes", boldFont);        
        tercihler.setSpacingBefore(20);
        tercihler.setAlignment(Element.ALIGN_LEFT);
        document.add(tercihler);
        int a = 1;
        for(KisiProgram kisiProgram : kisiBasvuru.getKisiProgram()) {
            Paragraph tercih= new Paragraph(a +  ".) " + kisiProgram.getProgram().getIngilizceAd(),font);  
            tercih.setSpacingBefore(10);
            document.add(tercih);
            a++;
        }
        ////////////////////////////////////////////////////////
        
        Paragraph iletisim = new Paragraph("Contact Information", boldFont);        
        iletisim.setSpacingBefore(20);
        iletisim.setAlignment(Element.ALIGN_LEFT);
        document.add(iletisim);        
        Paragraph telefon = new Paragraph("Telephone : " + kisiBasvuru.getTelefonNo(),font);  
        telefon.setSpacingBefore(10);
        document.add(telefon);        
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        Paragraph email = new Paragraph("Email : " +  kisiBasvuru.getKisiKayit().getEmail(),font);  
        email.setSpacingBefore(10);
        document.add(email);
        Paragraph adres = new Paragraph("Address : " + kisiBasvuru.getAdres(),font);  
        adres.setSpacingBefore(10);
        document.add(adres);
        Paragraph ulkeSehir = new Paragraph("Country / City  : " + kisiBasvuru.getYasadigiUllke().getIngilizceAd() + "/" + kisiBasvuru.getYasadigiSehirText(),font);        
        ulkeSehir.setSpacingBefore(10);
        document.add(ulkeSehir);
        ///////////////////////////////////////////////
        
        Paragraph lise = new Paragraph("High School Information", boldFont);        
        lise.setSpacingBefore(65);
        lise.setAlignment(Element.ALIGN_LEFT);
        document.add(lise);        
        
        Paragraph liseAd = new Paragraph("Graduated Lycee : " + kisiBasvuru.getMezunOlunanLise(),font);  
        liseAd.setSpacingBefore(10);
        document.add(liseAd);           
        
        Paragraph liseUlkeSehir = new Paragraph("Country / City of the lycee : " + kisiBasvuru.getLiseninUlkesi().getIngilizceAd() + "/" + kisiBasvuru.getLiseSehirText(),font);  
        liseUlkeSehir.setSpacingBefore(10);
        document.add(liseUlkeSehir);        
        
        Paragraph toplamSure = new Paragraph("Total duration of lycee education : " + kisiBasvuru.getLiseEgitimSuresi(),font);  
        toplamSure.setSpacingBefore(10);
        document.add(toplamSure);        
        
        Paragraph alan = new Paragraph("Field of study : " + kisiBasvuru.getLiseAlan(),font);  
        alan.setSpacingBefore(10);
        document.add(alan);        
        
        Paragraph basBitis = new Paragraph("Start /End dates of lycee : " + kisiBasvuru.getLiseBaslangic().toString().substring(0, 10).replace('-', '.') + "/" + kisiBasvuru.getLiseBitis().toString().substring(0, 10).replace('-', '.'),font);  
        basBitis.setSpacingBefore(10);
        document.add(basBitis);        
        
        Paragraph notSistemi = new Paragraph("Grading System : " + kisiBasvuru.getNotSistemi(),font);  
        notSistemi.setSpacingBefore(10);
        document.add(notSistemi);        
        
        Paragraph liseOrt = new Paragraph("Grade point avarage : " + kisiBasvuru.getLiseNotOrtalamasi(),font);  
        liseOrt.setSpacingBefore(10);
        document.add(liseOrt);        
        
        Paragraph yosPuan = new Paragraph("YÖS point  : " + kisiBasvuru.getYosPuani(), boldFont2);  
        yosPuan.setSpacingBefore(10);
        yosPuan.setSpacingAfter(30);
        document.add(yosPuan);        
     ////////////////////////////////////////////////////////////////////////////////////////////////  
       
     for(KisiDosya kisiDosya : kisiDosyalari) {    	
    	 try {
             String fotoPath = IMAGES_FILE_PATH + "/" + kisiDosya.getDosyaYol();
             Image foto = Image.getInstance(fotoPath);
             foto.scaleToFit(400, 400);
             foto.setAlignment(Image.ALIGN_CENTER);

             document.add(foto); 
    	 }catch (Exception e) {
			// TODO: handle exception
		}
     }                    
		return document;         		
	}
	
	@Override
	public KisiBasvuru findKisiBasvuruById(int id) {
		if(kisiBasvuruRepository.existsById(id)) {
			KisiBasvuru kisiBasvuru = kisiBasvuruRepository.findById(id).get();
			if(kisiBasvuru == null) {
				return null;
			}
			return kisiBasvuru;
		}
		return null;
	}

	@Override
	public boolean checkApplication(String email) {
		if(kisiKayitRepository.existsByEmail(email)) {
			KisiKayit kisiKayit = kisiKayitRepository.findByEmail(email);
			int id = kisiKayit.getId();
			// kisi kayit, yıl dönem
//	        List<Integer> guz = new ArrayList<>(List.of(2, 3, 4, 5,6,7,8)); 
//
//	        int ay = Calendar.getInstance().get(Calendar.MONTH);
//	        boolean donem = false;
//	        if(guz.contains(ay))	
//	        	donem = true;
//	        else
//	        	donem = false;
			
			if(kisiBasvuruRepository.existsByKisiKayitIdAndBasvuruYil(id, Calendar.getInstance().get(Calendar.YEAR))) {
				return true;
			}else {
				return false;
			}
		}
		return true;
	}



	@Override
	public List<Program> getAllProgram() {
		return programRepository.findAll();
	}

	@Override
	public List<KisiBasvuru> getBasvuruByUlke(int ulkeId) {
		return kisiBasvuruRepository.findByUlkeId(ulkeId);
	}
	@Override
	public List<AdminBasvurularResponse> getAllBasvuru(Pageable pageable) {
		Page<KisiBasvuru> tumBasvurular =  kisiBasvuruRepository.findAllByOrderByYosPuaniDesc(pageable);
		
		List<AdminBasvurularResponse> adminPanelBasvurular = new ArrayList<AdminBasvurularResponse>();
		for(KisiBasvuru kisiBasvuru : tumBasvurular.getContent()) {
			AdminBasvurularResponse adminPanelBasvuru = new AdminBasvurularResponse();
			adminPanelBasvuru.setId(kisiBasvuru.getId());
			adminPanelBasvuru.setAd(kisiBasvuru.getAd().toUpperCase());
			adminPanelBasvuru.setSoyad(kisiBasvuru.getSoyad().toUpperCase());
			if(kisiBasvuru.isCinsiyet())
				adminPanelBasvuru.setCinsiyet("Erkek");
			else
				adminPanelBasvuru.setCinsiyet("Bayan");
			adminPanelBasvuru.setUlke(kisiBasvuru.getUlke().getIngilizceAd());
			adminPanelBasvuru.setLiseNotOrtalamasi(kisiBasvuru.getLiseNotOrtalamasi());
			adminPanelBasvuru.setYosPuani(kisiBasvuru.getYosPuani());
			
			adminPanelBasvurular.add(adminPanelBasvuru);
		}
		
		return adminPanelBasvurular;
	}
	
	@Override
	public List<AdminBasvurularResponse> findByAdContainingIgnoreCaseOrSoyadContainingIgnoreCase(String ad, String soyad,
			Pageable pageable) {
		Page<KisiBasvuru> filtreliTumBasvurular =  kisiBasvuruRepository.findByAdContainingIgnoreCaseOrSoyadContainingIgnoreCaseOrderByYosPuaniDesc(ad, soyad, pageable);

		List<AdminBasvurularResponse> adminPanelBasvurular = new ArrayList<AdminBasvurularResponse>();
		for(KisiBasvuru kisiBasvuru : filtreliTumBasvurular.getContent()) {
			AdminBasvurularResponse adminPanelBasvuru = new AdminBasvurularResponse();
			adminPanelBasvuru.setId(kisiBasvuru.getId());
			adminPanelBasvuru.setAd(kisiBasvuru.getAd().toUpperCase());
			adminPanelBasvuru.setSoyad(kisiBasvuru.getSoyad().toUpperCase());
			if(kisiBasvuru.isCinsiyet())
				adminPanelBasvuru.setCinsiyet("Erkek");
			else
				adminPanelBasvuru.setCinsiyet("Bayan");
			adminPanelBasvuru.setUlke(kisiBasvuru.getUlke().getIngilizceAd());
			adminPanelBasvuru.setLiseNotOrtalamasi(kisiBasvuru.getLiseNotOrtalamasi());
			adminPanelBasvuru.setYosPuani(kisiBasvuru.getYosPuani());
			
			adminPanelBasvurular.add(adminPanelBasvuru);
		}
		
		return adminPanelBasvurular;
	}

	

	@Override
	public List<AdminBasvurularResponse> getAllBasvuruByUlke(int ulkeId, Pageable pageable) {
		Page<KisiBasvuru> tumBasvurular =  kisiBasvuruRepository.findByUlkeIdOrderByYosPuaniDesc(ulkeId, pageable);
		
		List<AdminBasvurularResponse> adminPanelBasvurular = new ArrayList<AdminBasvurularResponse>();
		for(KisiBasvuru kisiBasvuru : tumBasvurular.getContent()) {
			AdminBasvurularResponse adminPanelBasvuru = new AdminBasvurularResponse();
			adminPanelBasvuru.setId(kisiBasvuru.getId());
			adminPanelBasvuru.setAd(kisiBasvuru.getAd().toUpperCase());
			adminPanelBasvuru.setSoyad(kisiBasvuru.getSoyad().toUpperCase());
			if(kisiBasvuru.isCinsiyet())
				adminPanelBasvuru.setCinsiyet("Erkek");
			else
				adminPanelBasvuru.setCinsiyet("Bayan");
			adminPanelBasvuru.setUlke(kisiBasvuru.getUlke().getIngilizceAd());
			adminPanelBasvuru.setLiseNotOrtalamasi(kisiBasvuru.getLiseNotOrtalamasi());
			adminPanelBasvuru.setYosPuani(kisiBasvuru.getYosPuani());
			
			adminPanelBasvurular.add(adminPanelBasvuru);
		}
		
		return adminPanelBasvurular;
	}

	@Override
	public List<AdminBasvurularResponse> getByProgramId(int programId, Pageable pageable) {
		Page<KisiBasvuru> filtreliTumBasvurular = kisiBasvuruRepository.findByKisiProgramProgramIdOrderByYosPuaniDesc(programId, pageable);
						
		List<AdminBasvurularResponse> adminPanelBasvurular = new ArrayList<AdminBasvurularResponse>();
		for(KisiBasvuru kisiBasvuru : filtreliTumBasvurular.getContent()) {			
			AdminBasvurularResponse adminPanelBasvuru = new AdminBasvurularResponse();
			adminPanelBasvuru.setId(kisiBasvuru.getId());
			adminPanelBasvuru.setAd(kisiBasvuru.getAd().toUpperCase());
			adminPanelBasvuru.setSoyad(kisiBasvuru.getSoyad().toUpperCase());
			if(kisiBasvuru.isCinsiyet())
				adminPanelBasvuru.setCinsiyet("Erkek");
			else
				adminPanelBasvuru.setCinsiyet("Bayan");
			adminPanelBasvuru.setUlke(kisiBasvuru.getUlke().getIngilizceAd());
			adminPanelBasvuru.setLiseNotOrtalamasi(kisiBasvuru.getLiseNotOrtalamasi());
			adminPanelBasvuru.setYosPuani(kisiBasvuru.getYosPuani());
			
			adminPanelBasvurular.add(adminPanelBasvuru);		
		}
		
		return adminPanelBasvurular;
	}

	@Override
	public List<AdminBasvurularResponse> getByProgramIdAndUlkeId(int programId, int ulke, Pageable pageable) {

		Page<KisiBasvuru> filtreliTumBasvurular = kisiBasvuruRepository.findByUlkeIdAndKisiProgramProgramIdOrderByYosPuaniDesc(ulke, programId, pageable);
		
		List<AdminBasvurularResponse> adminPanelBasvurular = new ArrayList<AdminBasvurularResponse>();
		for(KisiBasvuru kisiBasvuru : filtreliTumBasvurular.getContent()) {			
			AdminBasvurularResponse adminPanelBasvuru = new AdminBasvurularResponse();
			adminPanelBasvuru.setId(kisiBasvuru.getId());
			adminPanelBasvuru.setAd(kisiBasvuru.getAd().toUpperCase());
			adminPanelBasvuru.setSoyad(kisiBasvuru.getSoyad().toUpperCase());
			if(kisiBasvuru.isCinsiyet())
				adminPanelBasvuru.setCinsiyet("Erkek");
			else
				adminPanelBasvuru.setCinsiyet("Bayan");
			adminPanelBasvuru.setUlke(kisiBasvuru.getUlke().getIngilizceAd());
			adminPanelBasvuru.setLiseNotOrtalamasi(kisiBasvuru.getLiseNotOrtalamasi());
			adminPanelBasvuru.setYosPuani(kisiBasvuru.getYosPuani());
			
			adminPanelBasvurular.add(adminPanelBasvuru);		
		}
		
		return adminPanelBasvurular;
	}
	
	

}
