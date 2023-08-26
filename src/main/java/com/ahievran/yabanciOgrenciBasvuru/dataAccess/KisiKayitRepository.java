package com.ahievran.yabanciOgrenciBasvuru.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.yabanciOgrenciBasvuru.entities.KisiKayit;

public interface KisiKayitRepository  extends JpaRepository<KisiKayit, Integer>{
	KisiKayit findByEmail(String email);
	KisiKayit findByDogrulamaKodu(String dogrulamaKodu);
	boolean existsByEmail(String email);
}
