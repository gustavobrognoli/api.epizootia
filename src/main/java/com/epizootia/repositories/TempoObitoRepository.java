package com.epizootia.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.TempoObito;

@Transactional(readOnly = true)
public interface TempoObitoRepository extends JpaRepository<TempoObito, Integer>{

	@Override
	List<TempoObito> findAll();
}
