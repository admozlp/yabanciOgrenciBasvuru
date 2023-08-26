package com.ahievran.yabanciOgrenciBasvuru.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.yabanciOgrenciBasvuru.entities.KisiDosya;

public interface KisiDosyaRepository  extends JpaRepository<KisiDosya, Integer>{
	void deleteByKisiBasvuruId(int id);
	List<KisiDosya> findByKisiBasvuruId(int id);
}
