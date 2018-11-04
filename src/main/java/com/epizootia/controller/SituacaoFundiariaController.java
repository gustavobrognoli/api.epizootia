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

import com.epizootia.dto.SituacaoFundiariaDTO;
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
	public ResponseEntity<Response<List<SituacaoFundiariaDTO>>> listaTodos() {
		Response<List<SituacaoFundiariaDTO>> response = new Response<List<SituacaoFundiariaDTO>>();

		List<SituacaoFundiariaDTO> situacaoFundiariaDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if (situacaoFundiariaDTOS.isEmpty()) {

			log.error("Não há situações fundiárias cadastradas");
			response.getErrors().add("Não há situações fundiárias cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(situacaoFundiariaDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de situacaoFundiaria por id
	 * 
	 * @return List<SituacaoFundiariaDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<SituacaoFundiariaDTO>> consulta(@PathVariable("id") int id) {

		Response<SituacaoFundiariaDTO> response = new Response<SituacaoFundiariaDTO>();
		Optional<SituacaoFundiaria> situacaoFundiaria = service.findById(id);

		if (!situacaoFundiaria.isPresent()) {

			log.error("Id da Situação Fundiária não cadastrado na base de dados");
			response.getErrors().add("Id do Situação Fundiária não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		SituacaoFundiariaDTO situacaoFundiariaDTO = converteEntityParaDTO(situacaoFundiaria.get());

		response.setData(situacaoFundiariaDTO);

		log.info("Consulta do Situação Fundiária {}", situacaoFundiariaDTO);

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
	public ResponseEntity<Response<SituacaoFundiariaDTO>> cadastrar(@Valid @RequestBody SituacaoFundiariaDTO DTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Situação Fundiária {}", DTO.toString());

		Response<SituacaoFundiariaDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		SituacaoFundiaria entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta Situação Fundiária da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<SituacaoFundiariaDTO>> apagar(@PathVariable("id") int id) {

		Response<SituacaoFundiariaDTO> response = new Response<SituacaoFundiariaDTO>();
		Optional<SituacaoFundiaria> situacaoFundiaria = service.findById(id);

		if (!situacaoFundiaria.isPresent()) {
			log.error("Id da Situação Fundiária não cadastrado na base de dados");
			response.getErrors().add("Id da Situação Fundiária não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		SituacaoFundiariaDTO situacaoFundiariaDTO = converteEntityParaDTO(situacaoFundiaria.get());

		response.setData(situacaoFundiariaDTO);
		service.apagar(situacaoFundiaria.get());
		log.info("Deletando Situação Fundiária {}", situacaoFundiariaDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param situacaoFundiariaDTO
	 * @return Entity
	 */
	public SituacaoFundiaria converteDTOParaEntity(SituacaoFundiariaDTO situacaoFundiariaDTO) {
		SituacaoFundiaria situacaoFundiaria = new SituacaoFundiaria();
		situacaoFundiaria.setId(situacaoFundiariaDTO.getId());
		situacaoFundiaria.setAssentamento(situacaoFundiariaDTO.getAssentamento());
		situacaoFundiaria.setRural(situacaoFundiariaDTO.getRural());
		situacaoFundiaria.setParticular(situacaoFundiariaDTO.getParticular());
		situacaoFundiaria.setGoverno(situacaoFundiariaDTO.getGoverno());
		situacaoFundiaria.setIndigina(situacaoFundiariaDTO.getIndigina());
		situacaoFundiaria.setQuilombola(situacaoFundiariaDTO.getQuilombola());
		situacaoFundiaria.setUnidadeConservacao(situacaoFundiariaDTO.getUnidadeConservacao());
		situacaoFundiaria.setNovaUnidadeConservacao(situacaoFundiariaDTO.getNovaUnidadeConservacao());
		situacaoFundiaria.setOutra(situacaoFundiariaDTO.getOutra());
		situacaoFundiaria.setOutraDescricao(situacaoFundiariaDTO.getOutraDescricao());
		return situacaoFundiaria;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param situacaoFundiaria
	 * @return DTO
	 */
	public SituacaoFundiariaDTO converteEntityParaDTO(SituacaoFundiaria situacaoFundiaria) {
		SituacaoFundiariaDTO situacaoFundiariaDTO = new SituacaoFundiariaDTO();
		situacaoFundiariaDTO.setId(situacaoFundiaria.getId());
		situacaoFundiariaDTO.setAssentamento(situacaoFundiaria.getAssentamento());
		situacaoFundiariaDTO.setRural(situacaoFundiaria.getRural());
		situacaoFundiariaDTO.setParticular(situacaoFundiaria.getParticular());
		situacaoFundiariaDTO.setGoverno(situacaoFundiaria.getGoverno());
		situacaoFundiariaDTO.setIndigina(situacaoFundiaria.getIndigina());
		situacaoFundiariaDTO.setQuilombola(situacaoFundiaria.getQuilombola());
		situacaoFundiariaDTO.setUnidadeConservacao(situacaoFundiaria.getUnidadeConservacao());
		situacaoFundiariaDTO.setNovaUnidadeConservacao(situacaoFundiaria.getNovaUnidadeConservacao());
		situacaoFundiariaDTO.setOutra(situacaoFundiaria.getOutra());
		situacaoFundiariaDTO.setOutraDescricao(situacaoFundiaria.getOutraDescricao());
		return situacaoFundiariaDTO;
	}

	/**
	 * 
	 * Valida se a Situacao Fundiaria ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(SituacaoFundiariaDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(sit -> result.addError(new ObjectError("situacao Fundiaria", dTO.getId() + "já existe")));
	}
}
