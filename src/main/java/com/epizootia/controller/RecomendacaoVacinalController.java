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

import com.epizootia.dto.RecomendacaoVacinalDTO;
import com.epizootia.entities.RecomendacaoVacinal;
import com.epizootia.response.Response;
import com.epizootia.services.RecomendacaoVacinalService;

@RestController
@RequestMapping("/api/recomendacaoVacinal")
public class RecomendacaoVacinalController {

	private static final Logger log = LoggerFactory.getLogger(RecomendacaoVacinalController.class);

	@Autowired
	private RecomendacaoVacinalService service;

	/**
	 * 
	 * Consulta todas Recomendações Vacinais
	 * 
	 * @return List<RecomendacaoVacinalDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<RecomendacaoVacinalDTO>>> listaTodos() {
		Response<List<RecomendacaoVacinalDTO>> response = new Response<List<RecomendacaoVacinalDTO>>();

		List<RecomendacaoVacinalDTO> recomendacaoVacinalDTOS = service.findAll().stream()
				.map(this::converteEntityParaDTO).collect(Collectors.toList());

		if (recomendacaoVacinalDTOS.isEmpty()) {

			log.error("Não há Recomendações Vacinais cadastrados");
			response.getErrors().add("Não há Recomendações Vacinais cadastrados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(recomendacaoVacinalDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Recomendações Vacinais por id
	 * 
	 * @return List<RecomendacaoVacinalDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<RecomendacaoVacinalDTO>> consulta(@PathVariable("id") int id) {

		Response<RecomendacaoVacinalDTO> response = new Response<RecomendacaoVacinalDTO>();
		Optional<RecomendacaoVacinal> recomendacaoVacinal = service.findById(id);

		if (!recomendacaoVacinal.isPresent()) {
			log.error("Id de Recomendação Vacinal não cadastrado na base de dados");
			response.getErrors().add("Id  de Recomendação Vacinal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		RecomendacaoVacinalDTO recomendacaoVacinalDTO = converteEntityParaDTO(recomendacaoVacinal.get());

		response.setData(recomendacaoVacinalDTO);

		log.info("Consulta de Recomendação Vacinal {}", recomendacaoVacinalDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novaa Recomendação Vacinal na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return RecomendacaoVacinal
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<RecomendacaoVacinalDTO>> cadastrar(@Valid @RequestBody RecomendacaoVacinalDTO DTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Recomendação Vacinal do animal {}", DTO.toString());

		Response<RecomendacaoVacinalDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		RecomendacaoVacinal entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta Recomendação Vacinal do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<RecomendacaoVacinalDTO>> apagar(@PathVariable("id") int id) {

		Response<RecomendacaoVacinalDTO> response = new Response<RecomendacaoVacinalDTO>();
		Optional<RecomendacaoVacinal> recomendacaoVacinal = service.findById(id);

		if (!recomendacaoVacinal.isPresent()) {
			log.error("Id de Recomendação Vacinal não cadastrado na base de dados");
			response.getErrors().add("Id de Recomendação Vacinal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		RecomendacaoVacinalDTO recomendacaoVacinalDTO = converteEntityParaDTO(recomendacaoVacinal.get());

		response.setData(recomendacaoVacinalDTO);
		service.apagar(recomendacaoVacinal.get());
		log.info("Deletando Recomendação Vacinal do Animal {}", recomendacaoVacinalDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param recomendacaoVacinalDTO
	 * @return Entity
	 */

	private RecomendacaoVacinal converteDTOParaEntity(RecomendacaoVacinalDTO recomendacaoVacinalDTO) {
		RecomendacaoVacinal recomendacaoVacinal = new RecomendacaoVacinal();
		recomendacaoVacinal.setId(recomendacaoVacinalDTO.getId());
		recomendacaoVacinal.setRecomendacaoVacinal(recomendacaoVacinalDTO.getRecomendacaoVacinal());
		return recomendacaoVacinal;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param recomendacaoVacinalDTO
	 * @return DTO
	 */

	private RecomendacaoVacinalDTO converteEntityParaDTO(RecomendacaoVacinal recomendacaoVacinal) {
		RecomendacaoVacinalDTO recomendacaoVacinalDTO = new RecomendacaoVacinalDTO();
		recomendacaoVacinalDTO.setId(recomendacaoVacinal.getId());
		recomendacaoVacinalDTO.setRecomendacaoVacinal(recomendacaoVacinal.getRecomendacaoVacinal());
		return recomendacaoVacinalDTO;
	}

	/**
	 * 
	 * Valida se o Recomendação Vacinal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(RecomendacaoVacinalDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId()).ifPresent(rec -> result
				.addError(new ObjectError("Recomendação Vacinal", dTO.getRecomendacaoVacinal() + "já existe")));
	}

}