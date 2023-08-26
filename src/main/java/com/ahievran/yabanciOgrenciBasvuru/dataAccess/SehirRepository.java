package com.ahievran.yabanciOgrenciBasvuru.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.yabanciOgrenciBasvuru.entities.Sehir;

public interface SehirRepository  extends JpaRepository<Sehir, Integer>{
	List<Sehir> findByUlkeId(int id);
}
