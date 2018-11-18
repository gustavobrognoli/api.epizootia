package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.VidaLivre;

public interface VidaLivreService {

	Optional<VidaLivre> findById(int id);
	
	VidaLivre persistir(VidaLivre vidaLivre);
	
	void apagar(VidaLivre vidaLivre);
	
	List<VidaLivre> findAll();
}

