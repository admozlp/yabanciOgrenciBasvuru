package com.ahievran.yabanciOgrenciBasvuru.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.yabanciOgrenciBasvuru.entities.Ulke;

public interface UlkelerRepository  extends JpaRepository<Ulke, Integer>{

}
