package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Equipamento;

public interface EquipamentoService {
	Optional<Equipamento> findById(int id);
	
	Equipamento persistir(Equipamento equipamento);
	
	void apagar(Equipamento equipamento);
	
	List<Equipamento> findAll();
}
