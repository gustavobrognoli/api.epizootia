package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Apreensao;

@Transactional(readOnly = true)
public interface ApreensaoRepository extends JpaRepository<Apreensao, Integer> {

	@Override
	List<Apreensao> findAll();

}
