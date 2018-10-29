package com.epizootia.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.epizootia.dto.NomePopularDTO;
import com.epizootia.entities.NomePopular;
import com.epizootia.response.Response;
import com.epizootia.services.NomePopularService;

@RestController
@RequestMapping("/api/nomePopular")
public class NomePopularController {

	private static final Logger log = LoggerFactory.getLogger(NomePopularController.class);

	@Autowired
	private NomePopularService service;

	/**
	 * 
	 * Consulta todos os Nomes Populares
	 * 
	 * @return List<NomePopularDTO>
	 */

	@GetMapping
	public ResponseEntity<Response<List<NomePopularDTO>>> listaTodos() {
		Response<List<NomePopularDTO>> response = new Response<List<NomePopularDTO>>();

		List<NomePopularDTO> nomePopularDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if (nomePopularDTOS.isEmpty()) {

			log.error("Não há nomes populares cadastradas");
			response.getErrors().add("Não há nomes populares cadastradas");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(nomePopularDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de Nomes Popular por id
	 * 
	 * @return List<AnimalDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<NomePopularDTO>> consulta(@PathVariable("id") int id) {

		Response<NomePopularDTO> response = new Response<NomePopularDTO>();
		Optional<NomePopular> nomePopular = service.findById(id);

		if (!nomePopular.isPresent()) {
			log.error("Id do Nome Popular do Animal não cadastrado na base de dados");
			response.getErrors().add("Id do Nome Popular do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		NomePopularDTO nomePopularDTO = converteEntityParaDTO(nomePopular.get());

		response.setData(nomePopularDTO);

		log.info("Consulta do Nome Popular do Animal {}", nomePopularDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo Nome Popular do animal na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return NomePopular
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<NomePopularDTO>> cadastrar(@Valid @RequestBody NomePopularDTO DTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Nome Popular do animal {}", DTO.toString());

		Response<NomePopularDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		NomePopular entity = this.converteDTOParaEntity(DTO);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(entity);
		response.setData(this.converteEntityParaDTO(entity));
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Nomes Popular da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<NomePopularDTO>> apagar(@PathVariable("id") int id) {

		Response<NomePopularDTO> response = new Response<NomePopularDTO>();
		Optional<NomePopular> nomePopular = service.findById(id);

		if (!nomePopular.isPresent()) {
			log.error("Id do Nome Popular do Animal não cadastrado na base de dados");
			response.getErrors().add("Id do Nome Popular do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		NomePopularDTO nomePopularDTO = converteEntityParaDTO(nomePopular.get());

		response.setData(nomePopularDTO);
		service.apagar(nomePopular.get());
		log.info("Deletando nomePopular {}", nomePopularDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param nomePopularDTO
	 * @return Entity
	 */

	private NomePopular converteDTOParaEntity(NomePopularDTO nomePopularDTO) {
		NomePopular nomePopular = new NomePopular();
		nomePopular.setId(nomePopularDTO.getId());
		nomePopular.setNome(nomePopularDTO.getNome());
		return nomePopular;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param nomePopularDTO
	 * @return DTO
	 */

	private NomePopularDTO converteEntityParaDTO(NomePopular nomePopular) {
		NomePopularDTO nomePopularDTO = new NomePopularDTO();
		nomePopularDTO.setId(nomePopular.getId());
		nomePopularDTO.setNome(nomePopular.getNome());
		return nomePopularDTO;
	}

	/**
	 * 
	 * Valida se o Nome Popular do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(NomePopularDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId()).ifPresent(
				nmp -> result.addError(new ObjectError("Nome Popular do Animal", dTO.getNome() + "já existe")));
	}

}