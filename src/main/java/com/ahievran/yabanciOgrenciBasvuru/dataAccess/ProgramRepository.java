package com.ahievran.yabanciOgrenciBasvuru.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.yabanciOgrenciBasvuru.entities.Program;

public interface ProgramRepository  extends JpaRepository<Program, Integer>{
}
