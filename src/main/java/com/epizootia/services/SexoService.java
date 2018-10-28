package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Sexo;

public interface SexoService {

		Optional<Sexo> findById(int id);
		
		Sexo persistir(Sexo sexo);
		
		void apagar(Sexo sexo);
		
		List<Sexo> findAll();
}
