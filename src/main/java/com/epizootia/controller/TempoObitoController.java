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

import com.epizootia.dto.TempoObitoDTO;
import com.epizootia.entities.TempoObito;
import com.epizootia.response.Response;
import com.epizootia.services.TempoObitoService;

@RestController
@RequestMapping("/api/tempoObito")
public class TempoObitoController {

	private static final Logger log = LoggerFactory.getLogger(TempoObitoController.class);

	@Autowired
	private TempoObitoService service;

	/**
	 * 
	 * Consulta todos os Nomes Populares
	 * 
	 * @return List<tempoObitoDTO>
	 */

	@GetMapping
	public ResponseEntity<Response<List<TempoObitoDTO>>> listaTodos() {
		Response<List<TempoObitoDTO>> response = new Response<List<TempoObitoDTO>>();

		List<TempoObitoDTO> tempoObitoDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if (tempoObitoDTOS.isEmpty()) {

			log.error("Não há Tempo de Óbito cadastrados");
			response.getErrors().add("Não há Tempo de Óbito cadastrados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(tempoObitoDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de Tempo de Óbito por id
	 * 
	 * @return List<TempoObitoDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<TempoObitoDTO>> consulta(@PathVariable("id") int id) {

		Response<TempoObitoDTO> response = new Response<TempoObitoDTO>();
		Optional<TempoObito> tempoObito = service.findById(id);

		if (!tempoObito.isPresent()) {
			log.error("Id de Tempo de Óbito não cadastrado na base de dados");
			response.getErrors().add("Id de Tempo de Óbito não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		TempoObitoDTO tempoObitoDTO = converteEntityParaDTO(tempoObito.get());

		response.setData(tempoObitoDTO);

		log.info("Consulta de tempo de óbito {}", tempoObitoDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo animal na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return TempoObito
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<TempoObitoDTO>> cadastrar(@Valid @RequestBody TempoObitoDTO DTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando tempo do obito {}", DTO.toString());

		Response<TempoObitoDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		TempoObito entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta tempo do obito da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<TempoObitoDTO>> apagar(@PathVariable("id") int id) {

		Response<TempoObitoDTO> response = new Response<TempoObitoDTO>();
		Optional<TempoObito> tempoObito = service.findById(id);

		if (!tempoObito.isPresent()) {
			log.error("Id do Tempo do Óbito não cadastrado na base de dados");
			response.getErrors().add("Id do Tempo do Óbito não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		TempoObitoDTO tempoObitoDTO = converteEntityParaDTO(tempoObito.get());

		response.setData(tempoObitoDTO);
		service.apagar(tempoObito.get());
		log.info("Deletando Tempo do Obito {}", tempoObitoDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param tempoObitoDTO
	 * @return Entity
	 */
	public TempoObito converteDTOParaEntity(TempoObitoDTO tempoObitoDTO) {
		TempoObito tempoObito = new TempoObito();
		tempoObito.setId(tempoObitoDTO.getId());
		tempoObito.setTempoObito(tempoObitoDTO.getTempoObito());
		return tempoObito;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param animal
	 * @return DTO
	 */
	public TempoObitoDTO converteEntityParaDTO(TempoObito tempoObito) {
		TempoObitoDTO tempoObitoDTO = new TempoObitoDTO();
		tempoObitoDTO.setId(tempoObito.getId());
		tempoObitoDTO.setTempoObito(tempoObito.getTempoObito());
		return tempoObitoDTO;
	}

	/**
	 * 
	 * Valida se o TempoObtito ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(TempoObitoDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(obt -> result.addError(new ObjectError("Tempo óbito", dTO.getTempoObito() + "já existe")));
	}
}
