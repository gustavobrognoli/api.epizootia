package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Ficha;

@Transactional(readOnly = true)
public interface FichaRepository extends JpaRepository<Ficha, Integer>{

	@Query("SELECT f FROM Ficha f WHERE f.classificacaoFA.id = ?1")
	List<Ficha> findAllByClassificacao(int idClassificacaoFA);
	
}
