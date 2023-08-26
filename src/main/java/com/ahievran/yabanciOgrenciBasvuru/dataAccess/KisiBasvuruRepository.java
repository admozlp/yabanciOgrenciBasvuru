package com.ahievran.yabanciOgrenciBasvuru.dataAccess;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.yabanciOgrenciBasvuru.entities.KisiBasvuru;

public interface KisiBasvuruRepository  extends JpaRepository<KisiBasvuru, Integer>{
	
	KisiBasvuru findByKisiKayitIdAndBasvuruYilAndBasvuruDonem(int kisiKayit, int basvuruYil, boolean basvuruDonem);
	List<KisiBasvuru> findByKisiKayitId(int kisiKayitId);
	boolean existsById(int id);
	boolean existsByKisiKayitIdAndBasvuruYil(int kisiKayitId, int basvuruYil);
	List<KisiBasvuru> findByUlkeId(int ulkeId);
	
	Page<KisiBasvuru> findAllByOrderByYosPuaniDesc(Pageable pageable);
	Page<KisiBasvuru> findByAdContainingIgnoreCaseOrSoyadContainingIgnoreCaseOrderByYosPuaniDesc(String ad, String soyad,
			Pageable pageable);
	
	Page<KisiBasvuru> findByUlkeIdOrderByYosPuaniDesc(int ulkeId, Pageable pageable);
	
	Page<KisiBasvuru> findByKisiProgramProgramIdOrderByYosPuaniDesc(int programId, Pageable pageable);
	Page<KisiBasvuru> findByUlkeIdAndKisiProgramProgramIdOrderByYosPuaniDesc(int programId,int ulkeId, Pageable pageable);

}
