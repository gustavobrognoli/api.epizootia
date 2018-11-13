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

import com.epizootia.dto.EspecieDTO;
import com.epizootia.entities.Especie;
import com.epizootia.response.Response;
import com.epizootia.services.EspecieService;

@RestController
@RequestMapping("/api/especie")
public class EspecieController {

	private static final Logger log = LoggerFactory.getLogger(EspecieController.class);

	@Autowired
	private EspecieService service;

	/**
	 * 
	 * Consulta todas as Especies
	 * 
	 * @return List<EspecieDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<EspecieDTO>>> listaTodos() {
		Response<List<EspecieDTO>> response = new Response<List<EspecieDTO>>();

		List<EspecieDTO> especieDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());
		
		if (especieDTOS.isEmpty()) {

			log.error("Não há especies cadastradas");
			response.getErrors().add("Não há especies cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(especieDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Especies por id
	 * 
	 * @return List<EspecieDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<EspecieDTO>> consulta(@PathVariable("id") int id) {

		Response<EspecieDTO> response = new Response<EspecieDTO>();
		Optional<Especie> especie = service.findById(id);

		if (!especie.isPresent()) {
			log.error("Id de Especie do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Especie do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		EspecieDTO especieDTO = converteEntityParaDTO(especie.get());

		response.setData(especieDTO);

		log.info("Consulta de Especie do Animal {}", especieDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Especie do animal na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Especie
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<EspecieDTO>> cadastrar(@Valid @RequestBody EspecieDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Especie do animal {}", DTO.toString());

		Response<EspecieDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Especie entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta Especie do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<EspecieDTO>> apagar(@PathVariable("id") int id) {

		Response<EspecieDTO> response = new Response<EspecieDTO>();
		Optional<Especie> especie = service.findById(id);

		if (!especie.isPresent()) {
			log.error("Id de Especie do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Especie do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		EspecieDTO especieDTO = converteEntityParaDTO(especie.get());

		response.setData(especieDTO);
		service.apagar(especie.get());
		log.info("Deletando Especie do Animal {}", especieDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param especieDTO
	 * @return Entity
	 */

	public Especie converteDTOParaEntity(EspecieDTO especieDTO) {
		Especie especie = new Especie();
		especie.setId(especieDTO.getId());
		especie.setEspecie(especieDTO.getEspecie());
		return especie;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param especieDTO
	 * @return DTO
	 */

	public EspecieDTO converteEntityParaDTO(Especie especie) {
		EspecieDTO especieDTO = new EspecieDTO();
		especieDTO.setId(especie.getId());
		especieDTO.setEspecie(especie.getEspecie());
		return especieDTO;
	}

	/**
	 * 
	 * Valida se a Especie do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(EspecieDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId()).
		ifPresent( esp -> result.addError(new ObjectError("Especie do Animal", dTO.getEspecie() + "já existe")));
	}

}
