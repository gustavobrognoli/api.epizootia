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

import com.epizootia.dto.CorposAguaDTO;
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
	 * @return List<CorposAguaDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<CorposAguaDTO>>> listaTodos() {
		Response<List<CorposAguaDTO>> response = new Response<List<CorposAguaDTO>>();

		List<CorposAguaDTO> corposAguaDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if (corposAguaDTOS.isEmpty()) {

			log.error("Não há situações fundiárias cadastradas");
			response.getErrors().add("Não há situações fundiárias cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(corposAguaDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de corposAgua por id
	 * 
	 * @return List<CorposAguaDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<CorposAguaDTO>> consulta(@PathVariable("id") int id) {

		Response<CorposAguaDTO> response = new Response<CorposAguaDTO>();
		Optional<CorposAgua> corposAgua = service.findById(id);

		if (!corposAgua.isPresent()) {

			log.error("Id da Situação Fundiária não cadastrado na base de dados");
			response.getErrors().add("Id do Situação Fundiária não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		CorposAguaDTO corposAguaDTO = converteEntityParaDTO(corposAgua.get());

		response.setData(corposAguaDTO);

		log.info("Consulta do Situação Fundiária {}", corposAguaDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova corposAgua na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return CorposAgua
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CorposAguaDTO>> cadastrar(@Valid @RequestBody CorposAguaDTO DTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Situação Fundiária {}", DTO.toString());

		Response<CorposAguaDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		CorposAgua entity = this.converteDTOParaEntity(DTO);

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
	public ResponseEntity<Response<CorposAguaDTO>> apagar(@PathVariable("id") int id) {

		Response<CorposAguaDTO> response = new Response<CorposAguaDTO>();
		Optional<CorposAgua> corposAgua = service.findById(id);

		if (!corposAgua.isPresent()) {
			log.error("Id da Situação Fundiária não cadastrado na base de dados");
			response.getErrors().add("Id da Situação Fundiária não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		CorposAguaDTO corposAguaDTO = converteEntityParaDTO(corposAgua.get());

		response.setData(corposAguaDTO);
		service.apagar(corposAgua.get());
		log.info("Deletando Situação Fundiária {}", corposAguaDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param corposAguaDTO
	 * @return Entity
	 */
	public CorposAgua converteDTOParaEntity(CorposAguaDTO corposAguaDTO) {
		CorposAgua corposAgua = new CorposAgua();
		corposAgua.setId(corposAguaDTO.getId());
		corposAgua.setNome(corposAguaDTO.getNome());
		return corposAgua;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param corposAgua
	 * @return DTO
	 */
	public CorposAguaDTO converteEntityParaDTO(CorposAgua corposAgua) {
		CorposAguaDTO corposAguaDTO = new CorposAguaDTO();
		corposAguaDTO.setId(corposAgua.getId());
		corposAguaDTO.setNome(corposAgua.getNome());
		return corposAguaDTO;
	}

	/**
	 * 
	 * Valida se a Situacao Fundiaria ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(CorposAguaDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(cor -> result.addError(new ObjectError("Situacao Fundiaria", dTO.getNome() + "já existe")));
	}
}
