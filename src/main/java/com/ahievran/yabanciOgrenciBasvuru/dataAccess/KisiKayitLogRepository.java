package com.ahievran.yabanciOgrenciBasvuru.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.yabanciOgrenciBasvuru.entities.KisiKayitLog;

public interface KisiKayitLogRepository extends  JpaRepository<KisiKayitLog, Integer>{
	List<KisiKayitLog> findByGonderilenAdres(String gonderilenAdres);
}
