package com.epizootia.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epizootia.entities.Ficha;
import com.epizootia.response.Response;
import com.epizootia.services.FichaService;

@RestController
@RequestMapping("/api/ficha")
public class FichaController {

	private static final Logger log = LoggerFactory.getLogger(FichaController.class);

	@Autowired
	private FichaService service;

	/**
	 * 
	 * Consulta todos os fichas
	 * 
	 * @return List<Ficha>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Ficha>>> listaTodos() {
		Response<List<Ficha>> response = new Response<List<Ficha>>();

		List<Ficha> fichas = service.findAll();

		if (fichas.isEmpty()) {

			log.error("Não há fichas cadastradas");
			response.getErrors().add("Não há fichas cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(fichas);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de ficha por id
	 * 
	 * @return List<Ficha>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Ficha>> consulta(@PathVariable("id") int id) {

		Response<Ficha> response = new Response<Ficha>();
		Optional<Ficha> ficha = service.findById(id);

		if (!ficha.isPresent()) {

			log.error("Id da Ficha não cadastrado na base de dados");
			response.getErrors().add("Id da Ficha não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(ficha.get());

		log.info("Consulta do ficha {}", ficha);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo ficha na base de dados
	 * 
	 * @param result
	 * @return Ficha
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Ficha>> cadastrar(@Valid @RequestBody Ficha ficha, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando ficha {}", ficha.toString());

		Response<Ficha> response = new Response<>();
		validaSeExiste(ficha, result);
		
		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(ficha);
		response.setData(ficha);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta ficha da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Ficha>> apagar(@PathVariable("id") int id) {

		Response<Ficha> response = new Response<Ficha>();
		Optional<Ficha> ficha = service.findById(id);

		if (!ficha.isPresent()) {
			log.error("Id da Ficha não cadastrado na base de dados");
			response.getErrors().add("Id da Ficha não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(ficha.get());
		service.apagar(ficha.get());
		log.info("Deletando ficha {}", ficha);

		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Valida se o Ficha ja existe na base de dados
	 * @param result
	 */
	private void validaSeExiste(Ficha ficha, BindingResult result) {
		this.service.findById(ficha.getId())
				.ifPresent(fic -> result.addError(new ObjectError("Ficha", ficha.getId() + "já existe")));
	}
}