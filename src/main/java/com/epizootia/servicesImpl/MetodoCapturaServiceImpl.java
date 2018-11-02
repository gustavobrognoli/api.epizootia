package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.MetodoCaptura;
import com.epizootia.repositories.MetodoCapturaRepository;
import com.epizootia.services.MetodoCapturaService;

@Service
public class MetodoCapturaServiceImpl implements MetodoCapturaService{

	private static final Logger log = LoggerFactory.getLogger(MetodoCapturaServiceImpl.class);

	@Autowired
	private MetodoCapturaRepository repository;

	@Override
	public Optional<MetodoCaptura> findById(int id) {
		log.info("Buscando Metodo de Captura Vacinal pelo código {}", id);
		Optional<MetodoCaptura> metodoCaptura = repository.findById(id);
		return metodoCaptura;
	}

	@Override
	public MetodoCaptura persistir(MetodoCaptura metodoCaptura) {
		log.info("Cadastrando Metodo de Captura: {}", metodoCaptura.toString());
		return this.repository.save(metodoCaptura);
	}

	@Override
	public void apagar(MetodoCaptura metodoCaptura){
		log.info("Apagando Metodo de Captura: {}", metodoCaptura.toString());
		repository.delete(metodoCaptura);
	}

	@Override
	public List<MetodoCaptura> findAll() {
		return repository.findAll();
	}
}