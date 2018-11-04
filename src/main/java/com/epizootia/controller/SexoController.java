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

import com.epizootia.dto.SexoDTO;
import com.epizootia.entities.Sexo;
import com.epizootia.response.Response;
import com.epizootia.services.SexoService;

@RestController
@RequestMapping("/api/sexo")
public class SexoController {

	private static final Logger log = LoggerFactory.getLogger(SexoController.class);

	@Autowired
	private SexoService service;

	/**
	 * 
	 * Consulta todas os Sexos
	 * 
	 * @return List<SexoDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<SexoDTO>>> listaTodos() {
		Response<List<SexoDTO>> response = new Response<List<SexoDTO>>();

		List<SexoDTO> sexoDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if (sexoDTOS.isEmpty()) {

			log.error("Não há sexos cadastrados");
			response.getErrors().add("Não há sexos cadastrados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(sexoDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas os Sexos por id
	 * 
	 * @return List<SexoDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<SexoDTO>> consulta(@PathVariable("id") int id) {

		Response<SexoDTO> response = new Response<SexoDTO>();
		Optional<Sexo> sexo = service.findById(id);

		if (!sexo.isPresent()) {
			log.error("Id de Sexo do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Sexo do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		SexoDTO sexoDTO = converteEntityParaDTO(sexo.get());

		response.setData(sexoDTO);

		log.info("Consulta de Sexo do Animal {}", sexoDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo Sexo do animal na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Sexo
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<SexoDTO>> cadastrar(@Valid @RequestBody SexoDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Sexo do animal {}", DTO.toString());

		Response<SexoDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Sexo entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta Sexo do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<SexoDTO>> apagar(@PathVariable("id") int id) {

		Response<SexoDTO> response = new Response<SexoDTO>();
		Optional<Sexo> sexo = service.findById(id);

		if (!sexo.isPresent()) {
			log.error("Id de Sexo do Animal não cadastrado na base de dados");
			response.getErrors().add("Id de Sexo do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		SexoDTO sexoDTO = converteEntityParaDTO(sexo.get());

		response.setData(sexoDTO);
		service.apagar(sexo.get());
		log.info("Deletando Sexo do Animal {}", sexoDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param sexoDTO
	 * @return Entity
	 */

	public Sexo converteDTOParaEntity(SexoDTO sexoDTO) {
		Sexo sexo = new Sexo();
		sexo.setId(sexoDTO.getId());
		sexo.setSexo(sexoDTO.getSexo());
		return sexo;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param sexoDTO
	 * @return DTO
	 */

	public SexoDTO converteEntityParaDTO(Sexo sexo) {
		SexoDTO sexoDTO = new SexoDTO();
		sexoDTO.setId(sexo.getId());
		sexoDTO.setSexo(sexo.getSexo());
		return sexoDTO;
	}

	/**
	 * 
	 * Valida se o Sexo do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(SexoDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(sex -> result.addError(new ObjectError("Sexo do Animal", dTO.getSexo() + "já existe")));
	}

}
