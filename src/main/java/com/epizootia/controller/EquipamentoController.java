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

import com.epizootia.dto.EquipamentoDTO;
import com.epizootia.entities.Equipamento;
import com.epizootia.response.Response;
import com.epizootia.services.EquipamentoService;

@RestController
@RequestMapping("/api/equipamento")
public class EquipamentoController {
	private static Logger log = LoggerFactory.getLogger(EquipamentoController.class);

	@Autowired
	private EquipamentoService service;

	/**
	 * 
	 * Consulta todos as equipamentos
	 * 
	 * @return List<EquipamentoDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<EquipamentoDTO>>> listaTodos() {
		Response<List<EquipamentoDTO>> response = new Response<List<EquipamentoDTO>>();

		List<EquipamentoDTO> equipamentoDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if(equipamentoDTOS.isEmpty()) {
		
		log.error("Não há equipamentos cadastradas");
		response.getErrors().add("Não há equipamentos cadastradas");
		
		return ResponseEntity.badRequest().body(response);
	}
		response.setData(equipamentoDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de equipamento atravez do id
	 * 
	 * @return List<EquipamentoDTO>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<EquipamentoDTO>> consulta(@PathVariable("id") int id) {

		Response<EquipamentoDTO> response = new Response<EquipamentoDTO>();
		Optional<Equipamento> equipamento = service.findById(id);

		if(!equipamento.isPresent()) {
			log.error("Id de Equipamento não cadastrado na base de dados");
			response.getErrors().add("Id de Equipamento não cadastrado na base de dados");
			
			return ResponseEntity.badRequest().body(response);
		}
		
		EquipamentoDTO equipamentoDTO = converteEntityParaDTO(equipamento.get());
				
		response.setData(equipamentoDTO);
		
		log.info("Consulta de equipamento {}", equipamentoDTO);
		
		return ResponseEntity.ok(response);
	}
	
	/**
	 * 
	 * Cadastra nova Equipamento na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Equipamento
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<EquipamentoDTO>> cadastrar(@Valid @RequestBody EquipamentoDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando equipamento {}", DTO.toString());
		
		Response<EquipamentoDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Equipamento entity = this.converteDTOParaEntity(DTO);
		
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
	 * Deleta equipamento da base de dados
	 * 
	 */
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<EquipamentoDTO>> apagar(@PathVariable("id") int id) {
		
		Response<EquipamentoDTO> response = new Response<EquipamentoDTO>();
		Optional<Equipamento> equipamento = service.findById(id);
		
		if (!equipamento.isPresent()) {
			log.error("Id de equipamento não cadastrando na base de dados");
			response.getErrors().add("Id de equipamento não cadastrando na base de dados");
			return ResponseEntity.badRequest().body(response);
		}
		
		EquipamentoDTO equipamentoDTO = converteEntityParaDTO(equipamento.get());
		
		response.setData(equipamentoDTO);
		service.apagar(equipamento.get());
		log.info("Apagando equipamento {}", equipamentoDTO);
		
		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param equipamentoDTO
	 * @return Entity
	 */
	public Equipamento converteDTOParaEntity(EquipamentoDTO equipamentoDTO) {
		Equipamento equipamento = new Equipamento();
		equipamento.setId(equipamentoDTO.getId());
		equipamento.setPuca(equipamentoDTO.isPuca());
		equipamento.setPuca(equipamentoDTO.isCastro());
		equipamento.setPuca(equipamentoDTO.isShanonn());
		equipamento.setPuca(equipamentoDTO.isCdc());
		return equipamento;
	}
	/**
	 * 
	 * Converte Entity para DTO
	 * 
	 * @param equipamento
	 * @return DTO
	 */
	public EquipamentoDTO converteEntityParaDTO(Equipamento equipamento) {
		EquipamentoDTO equipamentoDTO = new EquipamentoDTO();
		equipamentoDTO.setId(equipamento.getId());
		equipamentoDTO.setPuca(equipamento.isPuca());
		equipamentoDTO.setPuca(equipamento.isCastro());
		equipamentoDTO.setPuca(equipamento.isShanonn());
		equipamentoDTO.setPuca(equipamento.isCdc());
		return equipamentoDTO;
	}
	/**
	 * 
	 * Valida se a Equipamento ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(EquipamentoDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
			.ifPresent(ida -> result.addError(new ObjectError("Equipamento", dTO.getId() + " já existe")));
	}
}	

