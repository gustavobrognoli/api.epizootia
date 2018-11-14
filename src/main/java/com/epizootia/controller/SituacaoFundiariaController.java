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

import com.epizootia.entities.SituacaoFundiaria;
import com.epizootia.response.Response;
import com.epizootia.services.SituacaoFundiariaService;

@RestController
@RequestMapping("/api/situacaoFundiaria")
public class SituacaoFundiariaController {

	private static final Logger log = LoggerFactory.getLogger(SituacaoFundiariaController.class);

	@Autowired
	private SituacaoFundiariaService service;

	/**
	 * 
	 * Consulta todos os animais
	 * 
	 * @return List<SituacaoFundiariaDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<SituacaoFundiaria>>> listaTodos() {
		Response<List<SituacaoFundiaria>> response = new Response<List<SituacaoFundiaria>>();

		List<SituacaoFundiaria> situacoesFundiarias = service.findAll();

		if (situacoesFundiarias.isEmpty()) {

			log.error("Não há situações fundiárias cadastradas");
			response.getErrors().add("Não há situações fundiárias cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(situacoesFundiarias);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de situacaoFundiaria por id
	 * 
	 * @return List<SituacaoFundiariaDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<SituacaoFundiaria>> consulta(@PathVariable("id") int id) {

		Response<SituacaoFundiaria> response = new Response<SituacaoFundiaria>();
		Optional<SituacaoFundiaria> situacaoFundiaria = service.findById(id);

		if (!situacaoFundiaria.isPresent()) {

			log.error("Id da Situação Fundiária não cadastrado na base de dados");
			response.getErrors().add("Id do Situação Fundiária não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(situacaoFundiaria.get());

		log.info("Consulta do Situação Fundiária {}", situacaoFundiaria);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova situacaoFundiaria na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return SituacaoFundiaria
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<SituacaoFundiaria>> cadastrar(@Valid @RequestBody SituacaoFundiaria situacaoFundiaria,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Situação Fundiária {}", situacaoFundiaria.toString());

		Response<SituacaoFundiaria> response = new Response<>();
		validaSeExiste(situacaoFundiaria, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(situacaoFundiaria);
		response.setData(situacaoFundiaria);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Situação Fundiária da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<SituacaoFundiaria>> apagar(@PathVariable("id") int id) {

		Response<SituacaoFundiaria> response = new Response<SituacaoFundiaria>();
		Optional<SituacaoFundiaria> situacaoFundiaria = service.findById(id);

		if (!situacaoFundiaria.isPresent()) {
			log.error("Id da Situação Fundiária não cadastrado na base de dados");
			response.getErrors().add("Id da Situação Fundiária não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(situacaoFundiaria.get());
		service.apagar(situacaoFundiaria.get());
		log.info("Deletando Situação Fundiária {}", situacaoFundiaria);

		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Valida se a Situacao Fundiaria ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(SituacaoFundiaria situacaoFundiaria, BindingResult result) {
		this.service.findById(situacaoFundiaria.getId())
				.ifPresent(sit -> result.addError(new ObjectError("situacao Fundiaria", situacaoFundiaria.getId() + "já existe")));
	}
}
