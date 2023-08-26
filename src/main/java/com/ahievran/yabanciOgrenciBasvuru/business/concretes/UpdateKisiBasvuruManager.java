package com.ahievran.yabanciOgrenciBasvuru.business.concretes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ahievran.yabanciOgrenciBasvuru.business.abstracts.UpdateKisiBasvuruService;
import com.ahievran.yabanciOgrenciBasvuru.business.requests.UpdateApplicationRequest;
import com.ahievran.yabanciOgrenciBasvuru.core.utilities.mapper.MapperService;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.KisiBasvuruRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.KisiDosyaRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.KisiProgramRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.ProgramRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.SehirRepository;
import com.ahievran.yabanciOgrenciBasvuru.dataAccess.UlkelerRepository;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiBasvuru;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiDosya;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiProgram;
import com.ahievran.yabanciOgrenciBasvuru.entities.Program;
import com.ahievran.yabanciOgrenciBasvuru.entities.Sehir;

@Service
public class UpdateKisiBasvuruManager implements UpdateKisiBasvuruService{
	@Autowired private KisiBasvuruRepository basvuruRepository;
	@Autowired private MapperService mapperService;
	@Autowired private SehirRepository sehirRepository;
	@Autowired private UlkelerRepository ulkelerRepository;
	@Autowired private ProgramRepository programRepository;
	@Autowired private KisiDosyaRepository kisiDosyaRepository;
	@Autowired private KisiProgramRepository kisiProgramRepository;
	@Value("${app.upload.dir:${user.home}}")
	public String IMAGES_FILE_PATH;
	
	@Override
	public UpdateApplicationRequest getBasvuruById(int id) {
		if(basvuruRepository.existsById(id)) {
			KisiBasvuru kisiBasvuru = basvuruRepository.findById(id).get();
			UpdateApplicationRequest updateApplicationRequest= null;
			try {
				updateApplicationRequest = mapperService.forRequest().map(kisiBasvuru, UpdateApplicationRequest.class);
			}catch (Exception e) {
			}
			try {
				updateApplicationRequest.setTarihDogum(updateApplicationRequest.getDogumTarihi().toString());
				updateApplicationRequest.setBaslangicLise(updateApplicationRequest.getLiseBaslangic().toString());
				updateApplicationRequest.setBitisLise(updateApplicationRequest.getLiseBitis().toString());
				return updateApplicationRequest;
			} catch (Exception e) {
			}
		}
		return null;
	}

	@Override
	public List<Sehir> getSehirlerByUlkeId(int id) {
		return sehirRepository.findByUlkeId(id);
	}

	@Transactional
	@Override
	public UpdateApplicationRequest updateApp(UpdateApplicationRequest updateApplicationRequest) {
		
		if(basvuruRepository.existsById(updateApplicationRequest.getId())) {
			KisiBasvuru kisiBasvuru = basvuruRepository.findById(updateApplicationRequest.getId()).get();
			try {
				kisiBasvuru.setAd(updateApplicationRequest.getAd().toLowerCase());
				kisiBasvuru.setSoyad(updateApplicationRequest.getSoyad().toLowerCase());
				kisiBasvuru.setAnneAdi(updateApplicationRequest.getAnneAdi());
				kisiBasvuru.setBabaAdi(updateApplicationRequest.getBabaAdi());
				kisiBasvuru.setPasaportNo(updateApplicationRequest.getPasaportNo());
				kisiBasvuru.setDogumYeri(updateApplicationRequest.getDogumYeri());
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        kisiBasvuru.setDogumTarihi(dateFormat.parse(updateApplicationRequest.getTarihDogum()));
		        if(updateApplicationRequest.getGender().equals("Erkek"))
		        	kisiBasvuru.setCinsiyet(true);
		        else
		        	kisiBasvuru.setCinsiyet(false);
		        if(updateApplicationRequest.getUlkeId() != null)
		        	kisiBasvuru.setUlke(ulkelerRepository.findById(updateApplicationRequest.getUlkeId()).get());
		        
		        kisiBasvuru.setVatandaslikSekli(updateApplicationRequest.getVatandaslikSekli());
		        if(updateApplicationRequest.getDigerUlkeId() != null)
		        	kisiBasvuru.setDigerUlke(ulkelerRepository.findById(updateApplicationRequest.getDigerUlkeId()).get());
		        
		        kisiBasvuru.setDigerVatandaslikSekli(updateApplicationRequest.getDigerVatandaslikSekli());
		        kisiBasvuru.setTelefonNo(updateApplicationRequest.getTelefonNo());
		        kisiBasvuru.setAdres(updateApplicationRequest.getAdres());
		        
		        if(updateApplicationRequest.getYasadigiUlkeId() != null)
		        	kisiBasvuru.setYasadigiUllke(ulkelerRepository.findById(updateApplicationRequest.getYasadigiUlkeId()).get());
		        
		        
		        
		        kisiBasvuru.setMezunOlunanLise(updateApplicationRequest.getMezunOlunanLise());
		        
		        if(updateApplicationRequest.getLiseninUlkesiId() != null) 
		        	kisiBasvuru.setLiseninUlkesi(ulkelerRepository.findById(updateApplicationRequest.getLiseninUlkesiId()).get());
		        	
		        kisiBasvuru.setLiseSehirText(updateApplicationRequest.getLiseSehirText());
		        kisiBasvuru.setYasadigiSehirText(updateApplicationRequest.getYasadigiSehirText());
		        
		        kisiBasvuru.setLiseEgitimSuresi(updateApplicationRequest.getLiseEgitimSuresi());
		        
		        kisiBasvuru.setLiseAlan(updateApplicationRequest.getLiseAlan());
		        
		        kisiBasvuru.setLiseBaslangic(dateFormat.parse(updateApplicationRequest.getBaslangicLise()));
		        
		        kisiBasvuru.setLiseBitis(dateFormat.parse(updateApplicationRequest.getBitisLise()));
		        
		        kisiBasvuru.setNotSistemi(updateApplicationRequest.getNotSistemi());
		        kisiBasvuru.setLiseNotOrtalamasi(updateApplicationRequest.getLiseNotOrtalamasi());
		        kisiBasvuru.setYosPuani(updateApplicationRequest.getYosPuani());
		        
		       // List<Integer> guz = new ArrayList<>(List.of(2, 3, 4, 5,6,7,8)); 
		        
		        kisiBasvuru.setBasvuruYil(Calendar.getInstance().get(Calendar.YEAR));
		       // int ay = Calendar.getInstance().get(Calendar.MONTH);
		        //if(guz.contains(ay))	
		        	kisiBasvuru.setBasvuruDonem(true);
		        //else
		        //	kisiBasvuru.setBasvuruDonem(true);
		        		        	
				Date currentDate = new Date();	        
		        
		        kisiBasvuru.setBasvuruTarihi(currentDate);
		        
		        
		        
//		        List<KisiProgram> dbKisiProgramlar = kisiBasvuru.getKisiProgram();
//		        List<Integer> idler = new ArrayList<>();		       		        
//		        for(KisiProgram kisiProgram : dbKisiProgramlar) {
//		        	 	idler.add(kisiProgram.getProgram().getId());
//		        }      
		        try {
		        	kisiProgramRepository.deleteByKisiBasvuruId(kisiBasvuru.getId());
		        }catch (Exception e) {
				}
		       List<KisiProgram> yeniTercihler = kisiBasvuru.getKisiProgram();
		       int a = 1;
		       for(Integer programId : updateApplicationRequest.getSecimler()) {											    
			        Program secilenProgram = programRepository.findById(programId).get();
			        KisiProgram tercih = new KisiProgram();
			        tercih.setKisiBasvuru(kisiBasvuru);
			        tercih.setProgram(secilenProgram);
			        tercih.setTercihSirasi(a);
			        yeniTercihler.add(tercih);
			        a++;			    
		       }
		       kisiBasvuru.setKisiProgram(yeniTercihler);
		       		       		       
		       

					try {
						kisiDosyaRepository.deleteByKisiBasvuruId(kisiBasvuru.getId());
					}catch (Exception e) {
						e.printStackTrace();
					}
					
			       if (updateApplicationRequest.getFoto() != null && !updateApplicationRequest.getFoto().isEmpty()) {		           
				        MultipartFile foto = updateApplicationRequest.getFoto();
				        String path = generateUuuid();
				        try (InputStream inputStream = foto.getInputStream()) {
				            Files.copy(inputStream, Paths.get(IMAGES_FILE_PATH, path));
				            inputStream.close();
				        } catch (IOException e) {
				            e.printStackTrace();
				        }				       
						KisiDosya kisiDosya = new KisiDosya();
			           kisiDosya.setDosyaYol(path);
			           kisiDosya.setKisiBasvuru(kisiBasvuru);

				        kisiDosyaRepository.save(kisiDosya);
			       }
			        if(updateApplicationRequest.getKimlikOn() != null  && !updateApplicationRequest.getKimlikOn().isEmpty()) {
				        KisiDosya kisiDosya2 = new KisiDosya();
				        MultipartFile kimlikOn = updateApplicationRequest.getKimlikOn();
				        String path = generateUuuid();
				        try(InputStream inputStream = kimlikOn.getInputStream()){
				        	Files.copy(inputStream, Paths.get(IMAGES_FILE_PATH, path));
				        	inputStream.close();
				        }catch (Exception e) {
							// TODO: handle exception
						}
				        
				        
				        kisiDosya2.setDosyaYol(path);
				        kisiDosya2.setKisiBasvuru(kisiBasvuru);				
					     kisiDosyaRepository.save(kisiDosya2);

			
			        }
			        if(updateApplicationRequest.getKimlikArka() != null && !updateApplicationRequest.getKimlikArka().isEmpty()) {	
				        KisiDosya kisiDosya3 = new KisiDosya();
				        MultipartFile kimlikArka = updateApplicationRequest.getKimlikArka();
				        String path = generateUuuid();
				        Files.copy(kimlikArka.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
				        kisiDosya3.setDosyaYol(path);
				        kisiDosya3.setKisiBasvuru(kisiBasvuru);	 

				        kisiDosyaRepository.save(kisiDosya3);

			        }
			       if(updateApplicationRequest.getPasaportOn() != null && !updateApplicationRequest.getPasaportOn().isEmpty()) {	  
				        KisiDosya kisiDosya4 = new KisiDosya();
			    	   MultipartFile pasaportOn = updateApplicationRequest.getPasaportOn();
			    	   String path = generateUuuid();
				        Files.copy(pasaportOn.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
				        kisiDosya4.setDosyaYol(path);
				        kisiDosya4.setKisiBasvuru(kisiBasvuru);
		
				        kisiDosyaRepository.save(kisiDosya4);

			       }
			       if(updateApplicationRequest.getPasaportArka() != null && !updateApplicationRequest.getPasaportArka().isEmpty() ) {	 
				        KisiDosya kisiDosya5 = new KisiDosya();	        
				        MultipartFile pasaportArka = updateApplicationRequest.getPasaportArka();
				        String path = generateUuuid();
				        Files.copy(pasaportArka.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
				        kisiDosya5.setDosyaYol(path);
				        kisiDosya5.setKisiBasvuru(kisiBasvuru);
				  
				        kisiDosyaRepository.save(kisiDosya5);

			       }
			       if(updateApplicationRequest.getDiplomaOn() != null && !updateApplicationRequest.getDiplomaOn().isEmpty()) {	  
				        KisiDosya kisiDosya6 = new KisiDosya();
				        MultipartFile diplomaOn = updateApplicationRequest.getDiplomaOn();
				        String path = generateUuuid();
				        try(InputStream inputStream = diplomaOn.getInputStream()){
				        	Files.copy(diplomaOn.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
				        	inputStream.close();
				        }catch (Exception e) {
							// TODO: handle exception
						}
				        
				        kisiDosya6.setDosyaYol(path);
				        kisiDosya6.setKisiBasvuru(kisiBasvuru);				 
				        kisiDosyaRepository.save(kisiDosya6);
			       }
			       if(updateApplicationRequest.getDiplomaArka() != null && !updateApplicationRequest.getDiplomaArka().isEmpty()){	   
				        KisiDosya kisiDosya7 = new KisiDosya();
				        MultipartFile diplomaArka = updateApplicationRequest.getDiplomaArka();
				        String path = generateUuuid();
				        try(InputStream inputStream = diplomaArka.getInputStream()){
				        	Files.copy(diplomaArka.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
				        	inputStream.close();
				        }catch (Exception e) {
							// TODO: handle exception
						}
				        		 	   
				        kisiDosya7.setDosyaYol(path);
				        kisiDosya7.setKisiBasvuru(kisiBasvuru);
				        kisiDosyaRepository.save(kisiDosya7);
			       }
			       if(updateApplicationRequest.getTrYosSonuc() != null && !updateApplicationRequest.getTrYosSonuc().isEmpty()){	     
				        KisiDosya kisiDosya8 = new KisiDosya();
				        MultipartFile trYosSonuc = updateApplicationRequest.getTrYosSonuc();
				        String path = generateUuuid();
				        try(InputStream inputStream = trYosSonuc.getInputStream()){
				        	Files.copy(trYosSonuc.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
				        	inputStream.close();
				        }catch (Exception e) {
							// TODO: handle exception
						}
				        
				        kisiDosya8.setDosyaYol(path);
				        kisiDosya8.setKisiBasvuru(kisiBasvuru);				  
				        kisiDosyaRepository.save(kisiDosya8);
			       }
			       if(updateApplicationRequest.getDekont() != null && !updateApplicationRequest.getDekont().isEmpty()){	     
				        KisiDosya kisiDosya9 = new KisiDosya();
				        MultipartFile dekont = updateApplicationRequest.getDekont();				        
				        String path = generateUuuid();
				        Files.copy(dekont.getInputStream(), Paths.get(IMAGES_FILE_PATH, path));
				        kisiDosya9.setDosyaYol(path);
				        kisiDosya9.setKisiBasvuru(kisiBasvuru);
				        kisiDosyaRepository.save(kisiDosya9);
			       }
			       
				basvuruRepository.save(kisiBasvuru);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private String generateUuuid() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString().replace("-", "") + ".jpg";
        return uuidString;
	}

}
