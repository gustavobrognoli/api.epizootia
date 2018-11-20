package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Ficha;

@Transactional(readOnly = true)
public interface FichaRepository extends JpaRepository<Ficha, Integer>{

	@Query(value="SELECT f FROM Ficha f WHERE f.id_classificacaoFA = :idClassificacaoFA", nativeQuery = true)
	List<Ficha> ListFichasByClassificacao(@Param("idClassificacaoFA") int id_classificacaoFA);
	
}
