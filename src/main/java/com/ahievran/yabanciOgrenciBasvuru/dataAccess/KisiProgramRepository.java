package com.ahievran.yabanciOgrenciBasvuru.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.yabanciOgrenciBasvuru.entities.KisiProgram;

public interface KisiProgramRepository  extends JpaRepository<KisiProgram, Integer>{
	List<KisiProgram> findByProgramId(int id);
	void deleteByKisiBasvuruId(int id);
}
