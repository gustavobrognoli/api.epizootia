package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Equipamento;
import com.epizootia.repositories.EquipamentoRepository;
import com.epizootia.services.EquipamentoService;

@Service
public class EquipamentoServiceImpl implements EquipamentoService{
	
	private static final Logger log = LoggerFactory.getLogger(EquipamentoServiceImpl.class);

	@Autowired
	private EquipamentoRepository repository;
	
	@Override
	public Optional<Equipamento> findById(int id) {
		log.info("Buscando Equipamentos pelo codigo {}", id);
		Optional<Equipamento> equipamentos = repository.findById(id);
		return equipamentos;
	}

	@Override
	public Equipamento persistir(Equipamento equipamento) {
		log.info("Cadastrando equipamentos: {}", equipamento.toString());
		return this.repository.save(equipamento);
	}

	@Override
	public void apagar(Equipamento equipamento) {
		log.info("Apagando equipamentos: {}", equipamento.toString());
		repository.delete(equipamento);
	}

	@Override
	public List<Equipamento> findAll() {
		return repository.findAll();
	}

}
