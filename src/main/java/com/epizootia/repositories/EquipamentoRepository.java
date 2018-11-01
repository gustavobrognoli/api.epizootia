package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Equipamento;

@Transactional(readOnly = true)
public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {

	@Override
	List<Equipamento> findAll();
}
