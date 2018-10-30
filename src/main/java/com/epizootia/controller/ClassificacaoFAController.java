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

import com.epizootia.dto.ClassificacaoFADTO;
import com.epizootia.entities.ClassificacaoFA;
import com.epizootia.response.Response;
import com.epizootia.services.ClassificacaoFAService;

@RestController
@RequestMapping("/api/classificacaoFA")
public class ClassificacaoFAController {

	private static final Logger log = LoggerFactory.getLogger(ClassificacaoFAController.class);

	@Autowired
	private ClassificacaoFAService service;

	/**
	 * 
	 * Consulta todas as ClassificacaoFA
	 * 
	 * @return List<ClassificacaoFADTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<ClassificacaoFADTO>>> listaTodos() {
		Response<List<ClassificacaoFADTO>> response = new Response<List<ClassificacaoFADTO>>();

		List<ClassificacaoFADTO> classificacaoFADTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if (classificacaoFADTOS.isEmpty()) {

			log.error("Não há classificações de Febre Amarela cadastradas");
			response.getErrors().add("Não há classificações de Febre Amarela cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(classificacaoFADTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de classificacaoFA por id
	 * 
	 * @return List<ClassificacaoFADTO>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<ClassificacaoFADTO>> consulta(@PathVariable("id") int id) {

		Response<ClassificacaoFADTO> response = new Response<ClassificacaoFADTO>();
		Optional<ClassificacaoFA> classificacaoFA = service.findById(id);

		if (!classificacaoFA.isPresent()) {

			log.error("Id da ClassificacaoFA não cadastrada na base de dados");
			response.getErrors().add("Id da ClassificacaoFA não cadastrada na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		ClassificacaoFADTO classificacaoFADTO = converteEntityParaDTO(classificacaoFA.get());

		response.setData(classificacaoFADTO);

		log.info("Consulta da ClassificacaoFA {}", classificacaoFADTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova classificacaoFA na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return ClassificacaoFA
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<ClassificacaoFADTO>> cadastrar(@Valid @RequestBody ClassificacaoFADTO DTO, BindingResult result) 
			throws NoSuchAlgorithmException {
		log.info("Cadastrando classificacaoFA {}", DTO.toString());
		
		Response<ClassificacaoFADTO> response = new Response<>();
		validaSeExiste(DTO, result);
		ClassificacaoFA entity = this.converteDTOParaEntity(DTO);
		
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
	 * Deleta classificacaoFA da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<ClassificacaoFADTO>> apagar(@PathVariable("id") int id){
		
		Response<ClassificacaoFADTO> response = new Response<ClassificacaoFADTO>();
		Optional<ClassificacaoFA> classificacaoFA = service.findById(id);
		
		if (!classificacaoFA.isPresent()) {
			log.error("Id da ClassificacaoFA não cadastrado na base de dados");
			response.getErrors().add("Id da ClassificacaoFA não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}
		
		ClassificacaoFADTO classificacaoFADTO = converteEntityParaDTO(classificacaoFA.get());
		
		response.setData(classificacaoFADTO);
		service.apagar(classificacaoFA.get());
		log.info("Deletando classificacaoFA {}", classificacaoFADTO);
		
		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param classificacaoFADTO
	 * @return Entity
	 */
	private ClassificacaoFA converteDTOParaEntity(ClassificacaoFADTO classificacaoFADTO) {
		ClassificacaoFA classificacaoFA = new ClassificacaoFA();
		classificacaoFA.setId(classificacaoFADTO.getId());
		classificacaoFA.setConfirmado(classificacaoFADTO.getConfirmado());
		classificacaoFA.setDescartado(classificacaoFADTO.getDescartado());
		classificacaoFA.setIgnorado(classificacaoFADTO.getIgnorado());
		return classificacaoFA;
	}
	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param classificacao
	 * @return DTO
	 */
	private ClassificacaoFADTO converteEntityParaDTO(ClassificacaoFA classificacaoFA) {
		ClassificacaoFADTO classificacaoFADTO = new ClassificacaoFADTO();
		classificacaoFADTO.setId(classificacaoFA.getId());
		classificacaoFADTO.setConfirmado(classificacaoFA.getConfirmado());
		classificacaoFADTO.setDescartado(classificacaoFA.getDescartado());
		classificacaoFADTO.setIgnorado(classificacaoFA.getIgnorado());
		return classificacaoFADTO;
	}
	
	/**
	 * 
	 * Valida se a ClassificacaoFA ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(ClassificacaoFADTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
			.ifPresent(ano -> result.addError(new ObjectError("ClassificacaoFA", dTO.getId() + "já existe")));
	}
}
