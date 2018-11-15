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

import com.epizootia.entities.RegistroEntomologico;
import com.epizootia.response.Response;
import com.epizootia.services.RegistroEntomologicoService;

@RestController
@RequestMapping("/api/registroEntomologico")
public class RegistroEntomologicoController {

	private static final Logger log = LoggerFactory.getLogger(RegistroEntomologicoController.class);

	@Autowired
	private RegistroEntomologicoService service;

	/**
	 * 
	 * Consulta todos os Registros Entomologicos
	 * 
	 * @return List<RegistroEntomologico>
	 */
	@GetMapping
	public ResponseEntity<Response<List<RegistroEntomologico>>> listaTodos() {
		Response<List<RegistroEntomologico>> response = new Response<List<RegistroEntomologico>>();

		List<RegistroEntomologico> registrosEntomologicos = service.findAll();

		if (registrosEntomologicos.isEmpty()) {

			log.error("Não há Registros Entomologicos cadastrados");
			response.getErrors().add("Não há Registros Entomologicos cadastrados");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(registrosEntomologicos);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de registroEntomologico por id
	 * 
	 * @return List<RegistroEntomologico>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<RegistroEntomologico>> consulta(@PathVariable("id") int id) {

		Response<RegistroEntomologico> response = new Response<RegistroEntomologico>();
		Optional<RegistroEntomologico> registroEntomologico = service.findById(id);

		if (!registroEntomologico.isPresent()) {

			log.error("Id do Registro Entomologico não cadastrado na base de dados");
			response.getErrors().add("Id do RegistroEntomologico não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(registroEntomologico.get());

		log.info("Consulta do Registro Entomologico {}", registroEntomologico);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo Registro Entomologico na base de dados
	 * 
	 * @param result
	 * @return RegistroEntomologico
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<RegistroEntomologico>> cadastrar(@Valid @RequestBody RegistroEntomologico registroEntomologico, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Registro Entomologico {}", registroEntomologico.toString());

		Response<RegistroEntomologico> response = new Response<>();
		validaSeExiste(registroEntomologico, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(registroEntomologico);
		response.setData(registroEntomologico);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Registro Entomologico da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<RegistroEntomologico>> apagar(@PathVariable("id") int id) {

		Response<RegistroEntomologico> response = new Response<RegistroEntomologico>();
		Optional<RegistroEntomologico> registroEntomologico = service.findById(id);

		if (!registroEntomologico.isPresent()) {
			log.error("Id do Registro Entomologico não cadastrado na base de dados");
			response.getErrors().add("Id do Registro Entomologico não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(registroEntomologico.get());
		service.apagar(registroEntomologico.get());
		log.info("Deletando Registro Entomologico {}", registroEntomologico);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Valida se o Registro Entomologico ja existe na base de dados
	 * 
	 * @param result
	 */
	private void validaSeExiste(RegistroEntomologico registroEntomologico, BindingResult result) {
		this.service.findById(registroEntomologico.getId())
				.ifPresent(ent -> result.addError(new ObjectError("Registro Entomologico", registroEntomologico.getId() + "já existe")));
	}
}