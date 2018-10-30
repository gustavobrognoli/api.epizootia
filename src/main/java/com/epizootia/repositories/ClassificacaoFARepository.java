package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epizootia.entities.ClassificacaoFA;

public interface ClassificacaoFARepository extends JpaRepository<ClassificacaoFA, Integer> {

	@Override
	List<ClassificacaoFA> findAll();
}
