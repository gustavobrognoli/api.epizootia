package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.VidaLivre;

@Transactional(readOnly = true)
public interface VidaLivreRepository extends JpaRepository<VidaLivre, Integer>{
	
	@Override
	List<VidaLivre> findAll();

}
