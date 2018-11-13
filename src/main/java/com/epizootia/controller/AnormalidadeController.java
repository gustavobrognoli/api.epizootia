package com.epizootia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epizootia.services.AnormalidadeService;

@RestController
@RequestMapping("/api/anormalidade")
public class AnormalidadeController {

	private static final Logger log = LoggerFactory.getLogger(AnormalidadeController.class);

	@Autowired
	private AnormalidadeService service;

//	@GetMapping
//	public ResponseEntity<Response<CaracteristicasDTO>> consulta(@PathVariable("id") int id) {
//				Response<CaracteristicasDTO> response = new Response<CaracteristicasDTO>();
//				Optional<ArrayList<anoraalidade>> anormalidadeLIsta = service.findAll;
//	}
	
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<Response<CaracteristicasDTO>> consulta(@PathVariable("id") int id) {
//				Response<CaracteristicasDTO> response = new Response<CaracteristicasDTO>();
//				Optional<ArrayList<anoraalidade>> anormalidadeLIsta = service.findByALLId(id);(Criar esse select ver autor)
//	}
	
	}
