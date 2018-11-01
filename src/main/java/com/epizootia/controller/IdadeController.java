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

import com.epizootia.dto.IdadeDTO;
import com.epizootia.entities.Idade;
import com.epizootia.response.Response;
import com.epizootia.services.IdadeService;

public class IdadeController {

	private static Logger log = LoggerFactory.getLogger(IdadeController.class);

	@Autowired
	private IdadeService service;

	/**
	 * 
	 * Consulta todos as idades
	 * 
	 * @return List<IdadeDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<IdadeDTO>>> listaTodos() {
		Response<List<IdadeDTO>> response = new Response<List<IdadeDTO>>();

		List<IdadeDTO> idadeDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if (idadeDTOS.isEmpty()) {

			log.error("Não há idades cadastradas");
			response.getErrors().add("Não há idades cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(idadeDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de idade atravez do id
	 * 
	 * @return List<IdadeDTO>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<IdadeDTO>> consulta(@PathVariable("id") int id) {

		Response<IdadeDTO> response = new Response<IdadeDTO>();
		Optional<Idade> idade = service.findById(id);

		if (!idade.isPresent()) {
			log.error("Id de Idade não cadastrado na base de dados");
			response.getErrors().add("Id de Idade não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		IdadeDTO idadeDTO = converteEntityParaDTO(idade.get());

		response.setData(idadeDTO);

		log.info("Consulta de idade {}", idadeDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Idade na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Idade
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<IdadeDTO>> cadastrar(@Valid @RequestBody IdadeDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando idade {}", DTO.toString());

		Response<IdadeDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Idade entity = this.converteDTOParaEntity(DTO);

		if (result.hasErrors()) {
			log.error("Erro ao validar as informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(entity);
		response.setData(this.converteEntityParaDTO(entity));
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta idade da base de dados
	 * 
	 */

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<IdadeDTO>> apagar(@PathVariable("id") int id) {

		Response<IdadeDTO> response = new Response<IdadeDTO>();
		Optional<Idade> idade = service.findById(id);

		if (!idade.isPresent()) {
			log.error("Id de idade não cadastrando na base de dados");
			response.getErrors().add("Id de idade não cadastrando na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		IdadeDTO idadeDTO = converteEntityParaDTO(idade.get());

		response.setData(idadeDTO);
		service.apagar(idade.get());
		log.info("Apagando idade {}", idadeDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param idadeDTO
	 * @return Entity
	 */
	private Idade converteDTOParaEntity(IdadeDTO idadeDTO) {
		Idade idade = new Idade();
		idade.setId(idadeDTO.getId());
		idade.setIdade(idadeDTO.getIdade());
		return idade;
	}

	/**
	 * 
	 * Converte Entity para DTO
	 * 
	 * @param idade
	 * @return DTO
	 */
	private IdadeDTO converteEntityParaDTO(Idade idade) {
		IdadeDTO idadeDTO = new IdadeDTO();
		idadeDTO.setId(idade.getId());
		idadeDTO.setIdade(idade.getIdade());
		return idadeDTO;
	}

	/**
	 * 
	 * Valida se a Idade ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(IdadeDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(ida -> result.addError(new ObjectError("Idade", dTO.getIdade() + " já existe")));
	}
}
