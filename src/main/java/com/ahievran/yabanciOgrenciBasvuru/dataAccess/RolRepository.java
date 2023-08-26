package com.ahievran.yabanciOgrenciBasvuru.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.yabanciOgrenciBasvuru.entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
	Rol findByName(String name);
}
