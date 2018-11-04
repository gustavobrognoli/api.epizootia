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

import com.epizootia.dto.UnidadeConservacaoDTO;
import com.epizootia.entities.UnidadeConservacao;
import com.epizootia.response.Response;
import com.epizootia.services.UnidadeConservacaoService;

@RestController
@RequestMapping("/api/unidadeConservacao")
public class UnidadeConservacaoController {

	private static final Logger log = LoggerFactory.getLogger(UnidadeConservacaoController.class);

	@Autowired
	private UnidadeConservacaoService service;

	/**
	 * 
	 * Consulta todos os animais
	 * 
	 * @return List<UnidadeConservacaoDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<UnidadeConservacaoDTO>>> listaTodos() {
		Response<List<UnidadeConservacaoDTO>> response = new Response<List<UnidadeConservacaoDTO>>();

		List<UnidadeConservacaoDTO> unidadeConservacaoDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if (unidadeConservacaoDTOS.isEmpty()) {

			log.error("Não há unidades de conservação cadastradas");
			response.getErrors().add("Não há unidades de conservação cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(unidadeConservacaoDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de unidade de conservação por id
	 * 
	 * @return List<UnidadeConservacaoDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<UnidadeConservacaoDTO>> consulta(@PathVariable("id") int id) {

		Response<UnidadeConservacaoDTO> response = new Response<UnidadeConservacaoDTO>();
		Optional<UnidadeConservacao> unidadeConservacao = service.findById(id);

		if (!unidadeConservacao.isPresent()) {

			log.error("Id da Unidade de Conservação não cadastrado na base de dados");
			response.getErrors().add("Id da Unidade de Conservação não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		UnidadeConservacaoDTO unidadeConservacaoDTO = converteEntityParaDTO(unidadeConservacao.get());

		response.setData(unidadeConservacaoDTO);

		log.info("Consulta do Unidade de Conservação {}", unidadeConservacaoDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Unidade de Conservação na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return UnidadeConservacao
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<UnidadeConservacaoDTO>> cadastrar(@Valid @RequestBody UnidadeConservacaoDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Unidade de Conservação {}", DTO.toString());

		Response<UnidadeConservacaoDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		UnidadeConservacao entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta Unidade de Conservação da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<UnidadeConservacaoDTO>> apagar(@PathVariable("id") int id) {

		Response<UnidadeConservacaoDTO> response = new Response<UnidadeConservacaoDTO>();
		Optional<UnidadeConservacao> unidadeConservacao = service.findById(id);

		if (!unidadeConservacao.isPresent()) {
			log.error("Id da Unidade de Conservação não cadastrado na base de dados");
			response.getErrors().add("Id da Unidade de Conservação não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		UnidadeConservacaoDTO unidadeConservacaoDTO = converteEntityParaDTO(unidadeConservacao.get());

		response.setData(unidadeConservacaoDTO);
		service.apagar(unidadeConservacao.get());
		log.info("Deletando Unidade de Conservação {}", unidadeConservacaoDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param unidadeConservacaoDTO
	 * @return Entity
	 */
	public UnidadeConservacao converteDTOParaEntity(UnidadeConservacaoDTO unidadeConservacaoDTO) {
		UnidadeConservacao unidadeConservacao = new UnidadeConservacao();
		unidadeConservacao.setId(unidadeConservacaoDTO.getId());
		unidadeConservacao.setUnidadeConservacao(unidadeConservacaoDTO.getUnidadeConservacao());

		return unidadeConservacao;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param unidadeConservacao
	 * @return DTO
	 */
	public UnidadeConservacaoDTO converteEntityParaDTO(UnidadeConservacao unidadeConservacao) {
		UnidadeConservacaoDTO unidadeConservacaoDTO = new UnidadeConservacaoDTO();
		unidadeConservacaoDTO.setId(unidadeConservacao.getId());
		unidadeConservacaoDTO.setUnidadeConservacao(unidadeConservacao.getUnidadeConservacao());
		return unidadeConservacaoDTO;
	}

	/**
	 * 
	 * Valida se o UnidadeConservacao ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(UnidadeConservacaoDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(ani -> result.addError(new ObjectError("Unidade de Conservação", dTO.getUnidadeConservacao() + "já existe")));
	}
}
