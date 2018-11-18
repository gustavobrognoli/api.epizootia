package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.VidaLivre;
import com.epizootia.repositories.VidaLivreRepository;
import com.epizootia.services.VidaLivreService;

@Service
public class VidaLivreServiceImpl implements VidaLivreService {

	private static final Logger log = LoggerFactory.getLogger(VidaLivreServiceImpl.class);

	@Autowired
	private VidaLivreRepository repository;

	@Override
	public Optional<VidaLivre> findById(int id) {
		log.info("Buscando Vida Livre do Animal pelo código {}", id);
		Optional<VidaLivre> vidaLivre = repository.findById(id);
		return vidaLivre;
	}

	@Override
	public VidaLivre persistir(VidaLivre vidaLivre) {
		log.info("Cadastrando Vida Livre do Animal: {}", vidaLivre.toString());
		return this.repository.save(vidaLivre);
	}

	@Override
	public void apagar(VidaLivre vidaLivre) {
		log.info("Apagando Vida Livre do Animal: {}", vidaLivre.toString());
		repository.delete(vidaLivre);
	}

	@Override
	public List<VidaLivre> findAll() {
		return repository.findAll();
	}
}