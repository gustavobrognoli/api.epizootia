package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Viscera;

@Transactional(readOnly = true)
public interface VisceraRepository extends JpaRepository<Viscera, Integer> {

	@Override
	List<Viscera> findAll();
}
