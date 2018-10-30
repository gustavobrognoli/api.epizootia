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

import com.epizootia.dto.VisceraDTO;
import com.epizootia.entities.Viscera;
import com.epizootia.response.Response;
import com.epizootia.services.VisceraService;

@RestController
@RequestMapping("/api/viscera")
public class VisceraController {

	private static final Logger log = LoggerFactory.getLogger(VisceraController.class);

	@Autowired
	private VisceraService service;

	/**
	 * 
	 * Consulta todas as visceras
	 * 
	 * @return List<VisceraDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<VisceraDTO>>> listaTodos() {
		Response<List<VisceraDTO>> response = new Response<List<VisceraDTO>>();

		List<VisceraDTO> visceraDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if (visceraDTOS.isEmpty()) {

			log.error("Não há visceras cadastradas");
			response.getErrors().add("Não há visceras cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(visceraDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de viscera por id
	 * 
	 * @return List<VisceraDTO>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<VisceraDTO>> consulta(@PathVariable("id") int id) {

		Response<VisceraDTO> response = new Response<VisceraDTO>();
		Optional<Viscera> viscera = service.findById(id);

		if (!viscera.isPresent()) {

			log.error("Id da Viscera não cadastrada na base de dados");
			response.getErrors().add("Id da Viscera não cadastrada na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		VisceraDTO visceraDTO = converteEntityParaDTO(viscera.get());

		response.setData(visceraDTO);

		log.info("Consulta da Viscera {}", visceraDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova viscera na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Viscera
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<VisceraDTO>> cadastrar(@Valid @RequestBody VisceraDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando viscera {}", DTO.toString());

		Response<VisceraDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Viscera entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta viscera da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<VisceraDTO>> apagar(@PathVariable("id") int id) {

		Response<VisceraDTO> response = new Response<VisceraDTO>();
		Optional<Viscera> viscera = service.findById(id);

		if (!viscera.isPresent()) {
			log.error("Id da Viscera não cadastrado na base de dados");
			response.getErrors().add("Id da Viscera não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		VisceraDTO visceraDTO = converteEntityParaDTO(viscera.get());

		response.setData(visceraDTO);
		service.apagar(viscera.get());
		log.info("Deletando viscera {}", visceraDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param visceraDTO
	 * @return Entity
	 */
	private Viscera converteDTOParaEntity(VisceraDTO visceraDTO) {
		Viscera viscera = new Viscera();
		viscera.setId(visceraDTO.getId());
		viscera.setFigado(visceraDTO.getFigado());
		viscera.setRim(visceraDTO.getRim());
		viscera.setCerebro(visceraDTO.getCerebro());
		viscera.setBaco(visceraDTO.getBaco());
		viscera.setPulmao(visceraDTO.getPulmao());
		viscera.setCoracao(visceraDTO.getCoracao());
		viscera.setSangue(visceraDTO.getSangue());
		viscera.setSoro(visceraDTO.getSoro());
		viscera.setVisceraMotivo(visceraDTO.getVisceraMotivo());
		return viscera;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param anormalidade
	 * @return DTO
	 */
	private VisceraDTO converteEntityParaDTO(Viscera viscera) {
		VisceraDTO visceraDTO = new VisceraDTO();
		visceraDTO.setId(viscera.getId());
		visceraDTO.setFigado(viscera.getFigado());
		visceraDTO.setRim(viscera.getRim());
		visceraDTO.setCerebro(viscera.getCerebro());
		visceraDTO.setBaco(viscera.getBaco());
		visceraDTO.setPulmao(viscera.getPulmao());
		visceraDTO.setCoracao(viscera.getCoracao());
		visceraDTO.setSangue(viscera.getSangue());
		visceraDTO.setSoro(viscera.getSoro());
		visceraDTO.setVisceraMotivo(viscera.getVisceraMotivo());
		return visceraDTO;
	}

	/**
	 * 
	 * Valida se a Anormalidade ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(VisceraDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(visc -> result.addError(new ObjectError("Viscera", dTO.getId() + "já existe")));
	}
}
