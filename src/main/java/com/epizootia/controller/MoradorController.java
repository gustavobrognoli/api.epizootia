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

import com.epizootia.dto.MoradorDTO;
import com.epizootia.entities.Morador;
import com.epizootia.response.Response;
import com.epizootia.services.MoradorService;

@RestController
@RequestMapping("/api/morador")
public class MoradorController {

	private static final Logger log = LoggerFactory.getLogger(MoradorController.class);

	@Autowired
	private MoradorService service;

	/**
	 * 
	 * Consulta todas os Moradores
	 * 
	 * @return List<MoradorDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<MoradorDTO>>> listaTodos() {
		Response<List<MoradorDTO>> response = new Response<List<MoradorDTO>>();

		List<MoradorDTO> moradorDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if (moradorDTOS.isEmpty()) {

			log.error("Não há Moradores cadastrados");
			response.getErrors().add("Não há Moradores cadastrados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(moradorDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todos os Moradores por id
	 * 
	 * @return List<MoradorDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<MoradorDTO>> consulta(@PathVariable("id") int id) {

		Response<MoradorDTO> response = new Response<MoradorDTO>();
		Optional<Morador> morador = service.findById(id);

		if (!morador.isPresent()) {
			log.error("Id de Morador não cadastrado na base de dados");
			response.getErrors().add("Id de Morador não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		MoradorDTO moradorDTO = converteEntityParaDTO(morador.get());

		response.setData(moradorDTO);

		log.info("Consulta de Morador {}", moradorDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo Morador na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Morador
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<MoradorDTO>> cadastrar(@Valid @RequestBody MoradorDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Morador {}", DTO.toString());

		Response<MoradorDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Morador entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta Morador da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<MoradorDTO>> apagar(@PathVariable("id") int id) {

		Response<MoradorDTO> response = new Response<MoradorDTO>();
		Optional<Morador> morador = service.findById(id);

		if (!morador.isPresent()) {
			log.error("Id de Morador do Animal não cadastrado na base de dados");
			response.getErrors().add("Id de Morador do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		MoradorDTO moradorDTO = converteEntityParaDTO(morador.get());

		response.setData(moradorDTO);
		service.apagar(morador.get());
		log.info("Deletando Morador{}", moradorDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param moradorDTO
	 * @return Entity
	 */

	private Morador converteDTOParaEntity(MoradorDTO moradorDTO) {
		Morador morador = new Morador();
		morador.setId(moradorDTO.getId());
		morador.setMorador(moradorDTO.getMorador());
		morador.setTelefone(moradorDTO.getTelefone());
		return morador;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param moradorDTO
	 * @return DTO
	 */

	private MoradorDTO converteEntityParaDTO(Morador morador) {
		MoradorDTO moradorDTO = new MoradorDTO();
		moradorDTO.setId(morador.getId());
		moradorDTO.setMorador(morador.getMorador());
		moradorDTO.setTelefone(morador.getTelefone());
		return moradorDTO;
	}

	/**
	 * 
	 * Valida se o Morador ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(MoradorDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(sex -> result.addError(new ObjectError("Morador do Animal", dTO.getMorador() + "já existe")));
	}

}
