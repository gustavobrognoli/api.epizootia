package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.ClassificacaoFA;
import com.epizootia.repositories.ClassificacaoFARepository;
import com.epizootia.services.ClassificacaoFAService;

@Service
public class ClassificacaoFAImpl implements ClassificacaoFAService {

	private static final Logger log = LoggerFactory.getLogger(ClassificacaoFAImpl.class);

	@Autowired
	private ClassificacaoFARepository repository;

	@Override
	public Optional<ClassificacaoFA> findById(int id) {
		log.info("Buscando ClassificacaoFA pelo código {}", id);
		Optional<ClassificacaoFA> classificacaoFA = repository.findById(id);
		return classificacaoFA;
	}

	@Override
	public ClassificacaoFA persistir(ClassificacaoFA classificacaoFA) {
		log.info("Cadastrando ClassificacaoFA: {}", classificacaoFA.toString());
		return this.repository.save(classificacaoFA);
	}

	@Override
	public void apagar(ClassificacaoFA classificacaoFA) {
		log.info("Apagando ClassificacaoFA: {}", classificacaoFA.toString());
		repository.delete(classificacaoFA);
	}

	@Override
	public List<ClassificacaoFA> findAll() {
		return repository.findAll();
	}}
