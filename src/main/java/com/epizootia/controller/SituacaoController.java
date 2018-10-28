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

import com.epizootia.dto.SituacaoDTO;
import com.epizootia.entities.Situacao;
import com.epizootia.response.Response;
import com.epizootia.services.SituacaoService;

@RestController
@RequestMapping("/api/situacao")
public class SituacaoController {

	private static final Logger log = LoggerFactory.getLogger(SituacaoController.class);

	@Autowired
	private SituacaoService service;

	/**
	 * 
	 * Consulta todas as Situações
	 * 
	 * @return List<SituacaoDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<SituacaoDTO>>> listaTodos() {
		Response<List<SituacaoDTO>> response = new Response<List<SituacaoDTO>>();

		List<SituacaoDTO> situacaoDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());
			
		if (situacaoDTOS.isEmpty()) {
			
			log.error("Não há situações cadastradas");
			response.getErrors().add("Não há situações cadastradas");
	}
		response.setData(situacaoDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Situações por id
	 * 
	 * @return List<SituacaoDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<SituacaoDTO>> consulta(@PathVariable("id") int id) {

		Response<SituacaoDTO> response = new Response<SituacaoDTO>();
		Optional<Situacao> situacao = service.findById(id);

		if (!situacao.isPresent()) {
			log.error("Id de Situacao do Animal não cadastrado na base de dados");
			response.getErrors().add("Id de Situacao do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		SituacaoDTO situacaoDTO = converteEntityParaDTO(situacao.get());

		response.setData(situacaoDTO);

		log.info("Consulta de Situação do Animal {}", situacaoDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Situação do animal na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Situação
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<SituacaoDTO>> cadastrar(@Valid @RequestBody SituacaoDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Situação do animal {}", DTO.toString());

		Response<SituacaoDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Situacao entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta Situação do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<SituacaoDTO>> apagar(@PathVariable("id") int id) {

		Response<SituacaoDTO> response = new Response<SituacaoDTO>();
		Optional<Situacao> situacao = service.findById(id);

		if (!situacao.isPresent()) {
			log.error("Id de Situação do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Situação do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		SituacaoDTO situacaoDTO = converteEntityParaDTO(situacao.get());

		response.setData(situacaoDTO);
		service.apagar(situacao.get());
		log.info("Deletando Situacao do Animal {}", situacaoDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param situacaoDTO
	 * @return Entity
	 */

	private Situacao converteDTOParaEntity(SituacaoDTO situacaoDTO) {
		Situacao situacao = new Situacao();
		situacao.setId(situacaoDTO.getId());
		situacao.setSituacao(situacao.getSituacao());
		return situacao;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param situacaoDTO
	 * @return DTO
	 */

	private SituacaoDTO converteEntityParaDTO(Situacao situacao) {
		SituacaoDTO situacaoDTO = new SituacaoDTO();
		situacaoDTO.setId(situacao.getId());
		situacaoDTO.setSituacao(situacao.getSituacao());
		return situacaoDTO;
	}

	/**
	 * 
	 * Valida se o Situacao do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(SituacaoDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId()).ifPresent(
				esp -> result.addError(new ObjectError("Situacao do Animal", dTO.getSituacao() + "já existe")));
	}

}
