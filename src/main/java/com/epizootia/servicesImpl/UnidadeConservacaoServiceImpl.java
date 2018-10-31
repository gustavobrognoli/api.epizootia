package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.UnidadeConservacao;
import com.epizootia.repositories.UnidadeConservacaoRepository;
import com.epizootia.services.UnidadeConservacaoService;

@Service
public class UnidadeConservacaoServiceImpl implements UnidadeConservacaoService {

	private static final Logger log = LoggerFactory.getLogger(UnidadeConservacaoServiceImpl.class);

	@Autowired
	private UnidadeConservacaoRepository repository;

	@Override
	public Optional<UnidadeConservacao> findById(int id) {
		log.info("Buscando UnidadeConservacao pelo código {}", id);
		Optional<UnidadeConservacao> unidadeConservacao = repository.findById(id);
		return unidadeConservacao;
	}

	@Override
	public UnidadeConservacao persistir(UnidadeConservacao unidadeConservacao) {
		log.info("Cadastrando UnidadeConservacao: {}", unidadeConservacao.toString());
		return this.repository.save(unidadeConservacao);
	}

	@Override
	public void apagar(UnidadeConservacao unidadeConservacao) {
		log.info("Apagando UnidadeConservacao: {}", unidadeConservacao.toString());
		repository.delete(unidadeConservacao);
	}

	@Override
	public List<UnidadeConservacao> findAll() {
		return repository.findAll();
	}

}
