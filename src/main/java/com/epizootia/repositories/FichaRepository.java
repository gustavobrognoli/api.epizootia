package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Ficha;

@Transactional(readOnly = true)
public interface FichaRepository extends JpaRepository<Ficha, Integer>{

	@Transactional
	@Query("SELECT * FROM Ficha f WHERE f.id_classificacaoFA = ?1")
	List<Ficha> ListFichasByClassificacao(int id_classificacaoFA);
	
	
}
