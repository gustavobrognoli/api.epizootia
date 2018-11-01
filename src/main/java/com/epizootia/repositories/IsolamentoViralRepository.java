package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epizootia.entities.IsolamentoViral;

public interface IsolamentoViralRepository extends JpaRepository<IsolamentoViral, Integer>{
	
	@Override
	List<IsolamentoViral> findAll();
}
