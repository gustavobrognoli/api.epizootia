package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.NomePopular;

@Transactional(readOnly = true)
public interface NomePopularRepository extends JpaRepository<NomePopular, Integer>{

	@Override
	List<NomePopular> findAll();
}
