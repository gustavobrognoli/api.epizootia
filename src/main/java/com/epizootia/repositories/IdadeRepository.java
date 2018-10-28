package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Idade;

@Transactional(readOnly = true)
public interface IdadeRepository extends JpaRepository<Idade, Integer>{

	@Override
	List<Idade> findAll();
}
