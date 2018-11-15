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

import com.epizootia.entities.CorposAgua;
import com.epizootia.response.Response;
import com.epizootia.services.CorposAguaService;

@RestController
@RequestMapping("/api/corposAgua")
public class CorposAguaController {

	private static final Logger log = LoggerFactory.getLogger(CorposAguaController.class);

	@Autowired
	private CorposAguaService service;

	/**
	 * 
	 * Consulta todos os animais
	 * 
	 * @return List<CorposAgua>
	 */
	@GetMapping
	public ResponseEntity<Response<List<CorposAgua>>> listaTodos() {
		Response<List<CorposAgua>> response = new Response<List<CorposAgua>>();

		List<CorposAgua> corposAguas = service.findAll();

		if (corposAguas.isEmpty()) {

			log.error("Não há situações fundiárias cadastradas");
			response.getErrors().add("Não há situações fundiárias cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(corposAguas);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de corposAgua por id
	 * 
	 * @return List<CorposAguaDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<CorposAgua>> consulta(@PathVariable("id") int id) {

		Response<CorposAgua> response = new Response<CorposAgua>();
		Optional<CorposAgua> corposAgua = service.findById(id);

		if (!corposAgua.isPresent()) {

			log.error("Id do Corpo d`Água não cadastrado na base de dados");
			response.getErrors().add("Id do Corpo d`Água não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(corposAgua.get());

		log.info("Consulta do Corpo d`Água {}", corposAgua);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo corposAgua na base de dados
	 * 
	 * @param result
	 * @return CorposAgua
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CorposAgua>> cadastrar(@Valid @RequestBody CorposAgua corposAgua,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Situação Fundiária {}", corposAgua.toString());

		Response<CorposAgua> response = new Response<>();
		validaSeExiste(corposAgua, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(corposAgua);
		response.setData(corposAgua);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Situação Fundiária da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<CorposAgua>> apagar(@PathVariable("id") int id) {

		Response<CorposAgua> response = new Response<CorposAgua>();
		Optional<CorposAgua> corposAgua = service.findById(id);

		if (!corposAgua.isPresent()) {
			log.error("Id do Corpo d`Água não cadastrado na base de dados");
			response.getErrors().add("Id do Corpo d`Água não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(corposAgua.get());
		service.apagar(corposAgua.get());
		log.info("Deletando Corpo d`Água {}", corposAgua);

		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Valida se a Situacao Fundiaria ja existe na base de dados
	 * 
	 * @param result
	 */
	private void validaSeExiste(CorposAgua corposAgua, BindingResult result) {
		this.service.findById(corposAgua.getId())
				.ifPresent(cor -> result.addError(new ObjectError("Situacao Fundiaria", corposAgua.getNome() + "já existe")));
	}
}
