package com.ahievran.yabanciOgrenciBasvuru.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ahievran.yabanciOgrenciBasvuru.business.requests.CreateBasvuruRequest;
import com.ahievran.yabanciOgrenciBasvuru.business.responses.AdminBasvurularResponse;
import com.ahievran.yabanciOgrenciBasvuru.business.responses.SehirlerResponse;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiBasvuru;
import com.ahievran.yabanciOgrenciBasvuru.entities.KisiDosya;
import com.ahievran.yabanciOgrenciBasvuru.entities.Program;
import com.ahievran.yabanciOgrenciBasvuru.entities.Ulke;
import com.itextpdf.text.Document;

public interface KisiBasvuruService {
	CreateBasvuruRequest add(CreateBasvuruRequest createBasvuruRequest) throws Exception;
	List<Ulke> getAllCountries();
	public List<SehirlerResponse> getSehirByUlke(int ulkeId);
	List<KisiBasvuru> getAllKisiBasvuruByEmail(String email);
	Document getHtmlContent(KisiBasvuru kisiBasvuru, Document document, List<KisiDosya> kisiDosyalari) throws Exception;
	KisiBasvuru findKisiBasvuruById(int id);
	boolean checkApplication(String email);
	
	List<AdminBasvurularResponse> getAllBasvuru(Pageable pageable);
	List<AdminBasvurularResponse> findByAdContainingIgnoreCaseOrSoyadContainingIgnoreCase(String ad, String soyad, Pageable pageable);
	
	List<AdminBasvurularResponse> getAllBasvuruByUlke(int ulkeId, Pageable pageable);
	
	List<AdminBasvurularResponse> getByProgramId(int programId, Pageable pageable);
	List<AdminBasvurularResponse> getByProgramIdAndUlkeId(int programId,int ulke, Pageable pageable);
	
	List<Program> getAllProgram();
	List<KisiBasvuru> getBasvuruByUlke(int ulkeId);
}
