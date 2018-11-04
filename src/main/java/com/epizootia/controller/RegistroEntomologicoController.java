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

import com.epizootia.dto.RegistroEntomologicoDTO;
import com.epizootia.entities.RegistroEntomologico;
import com.epizootia.response.Response;
import com.epizootia.services.RegistroEntomologicoService;

@RestController
@RequestMapping("/api/registroEntomologico")
public class RegistroEntomologicoController {

	private static final Logger log = LoggerFactory.getLogger(RegistroEntomologicoController.class);

	@Autowired
	private RegistroEntomologicoService service;

	/**
	 * 
	 * Consulta todos os Registros Entomologicos
	 * 
	 * @return List<RegistroEntomologicoDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<RegistroEntomologicoDTO>>> listaTodos() {
		Response<List<RegistroEntomologicoDTO>> response = new Response<List<RegistroEntomologicoDTO>>();

		List<RegistroEntomologicoDTO> registroEntomologicoDTOS = service.findAll().stream().map(this::converteEntityParaDTO).collect(Collectors.toList());

		if (registroEntomologicoDTOS.isEmpty()) {

			log.error("Não há Registros Entomologicos cadastrados");
			response.getErrors().add("Não há Registros Entomologicos cadastrados");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(registroEntomologicoDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de registroEntomologico por id
	 * 
	 * @return List<RegistroEntomologicoDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<RegistroEntomologicoDTO>> consulta(@PathVariable("id") int id) {

		Response<RegistroEntomologicoDTO> response = new Response<RegistroEntomologicoDTO>();
		Optional<RegistroEntomologico> registroEntomologico = service.findById(id);

		if (!registroEntomologico.isPresent()) {

			log.error("Id do Registro Entomologico não cadastrado na base de dados");
			response.getErrors().add("Id do RegistroEntomologico não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		RegistroEntomologicoDTO registroEntomologicoDTO = converteEntityParaDTO(registroEntomologico.get());

		response.setData(registroEntomologicoDTO);

		log.info("Consulta do Registro Entomologico {}", registroEntomologicoDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo Registro Entomologico na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return RegistroEntomologico
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<RegistroEntomologicoDTO>> cadastrar(@Valid @RequestBody RegistroEntomologicoDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Registro Entomologico {}", DTO.toString());

		Response<RegistroEntomologicoDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		RegistroEntomologico entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta Registro Entomologico da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<RegistroEntomologicoDTO>> apagar(@PathVariable("id") int id) {

		Response<RegistroEntomologicoDTO> response = new Response<RegistroEntomologicoDTO>();
		Optional<RegistroEntomologico> registroEntomologico = service.findById(id);

		if (!registroEntomologico.isPresent()) {
			log.error("Id do Registro Entomologico não cadastrado na base de dados");
			response.getErrors().add("Id do Registro Entomologico não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		RegistroEntomologicoDTO registroEntomologicoDTO = converteEntityParaDTO(registroEntomologico.get());

		response.setData(registroEntomologicoDTO);
		service.apagar(registroEntomologico.get());
		log.info("Deletando Registro Entomologico {}", registroEntomologicoDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param registroEntomologicoDTO
	 * @return Entity
	 */
	private RegistroEntomologico converteDTOParaEntity(RegistroEntomologicoDTO registroEntomologicoDTO) {
		RegistroEntomologico registroEntomologico = new RegistroEntomologico();
		registroEntomologico.setId(registroEntomologicoDTO.getId());
		registroEntomologico.setDataRegistro(registroEntomologico.getDataRegistro());
		registroEntomologico.setDataUltimoRegistro(registroEntomologico.getDataUltimoRegistro());
		
		MetodoCapturaController metodoCapturaController = new MetodoCapturaController();
		registroEntomologico.setMetodoCaptura(metodoCapturaController.converteDTOParaEntity(registroEntomologicoDTO.getMetodoCaptura()));
		
		EquipamentoController equipamentoController = new EquipamentoController();
		registroEntomologico.setEquipamento(equipamentoController.converteDTOParaEntity(registroEntomologicoDTO.getEquipamento()));
		
		IsolamentoViralController isolamentoViralController = new IsolamentoViralController();
		registroEntomologico.setIsolamentoViral(isolamentoViralController.converteDTOParaEntity(registroEntomologicoDTO.getIsolamentoViral()));
		
		RecomendacaoVacinalController recomendacaoVacinalController = new RecomendacaoVacinalController();
		registroEntomologico.setRecomendacaoVacinal(recomendacaoVacinalController.converteDTOParaEntity(registroEntomologicoDTO.getRecomendacaoVacinal()));
		
		registroEntomologico.setCoberturaVacinal(registroEntomologicoDTO.getCoberturaVacinal());
		registroEntomologico.setImoveisVisitados300m(registroEntomologicoDTO.getImoveisVisitados300m());
		registroEntomologico.setDosesAplicadas300m(registroEntomologicoDTO.getDosesAplicadas300m());
		registroEntomologico.setFocosAedes300m(registroEntomologicoDTO.getFocosAedes300m());
		return registroEntomologico;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param registroEntomologico
	 * @return DTO
	 */
	private RegistroEntomologicoDTO converteEntityParaDTO(RegistroEntomologico registroEntomologico) {
		RegistroEntomologicoDTO registroEntomologicoDTO = new RegistroEntomologicoDTO();
		registroEntomologicoDTO.setId(registroEntomologico.getId());
		registroEntomologicoDTO.setDataRegistro(registroEntomologico.getDataRegistro());
		registroEntomologicoDTO.setDataUltimoRegistro(registroEntomologico.getDataUltimoRegistro());
		
		MetodoCapturaController metodoCapturaController = new MetodoCapturaController();
		registroEntomologicoDTO.setMetodoCaptura(metodoCapturaController.converteEntityParaDTO(registroEntomologico.getMetodoCaptura()));
		
		EquipamentoController equipamentoController = new EquipamentoController();
		registroEntomologicoDTO.setEquipamento(equipamentoController.converteEntityParaDTO(registroEntomologico.getEquipamento()));
		
		IsolamentoViralController isolamentoViralController = new IsolamentoViralController();
		registroEntomologicoDTO.setIsolamentoViral(isolamentoViralController.converteEntityParaDTO(registroEntomologico.getIsolamentoViral()));
		
		RecomendacaoVacinalController recomendacaoVacinalController = new RecomendacaoVacinalController();
		registroEntomologicoDTO.setRecomendacaoVacinal(recomendacaoVacinalController.converteEntityParaDTO(registroEntomologico.getRecomendacaoVacinal()));
		
		registroEntomologicoDTO.setCoberturaVacinal(registroEntomologico.getCoberturaVacinal());
		registroEntomologicoDTO.setImoveisVisitados300m(registroEntomologico.getImoveisVisitados300m());
		registroEntomologicoDTO.setDosesAplicadas300m(registroEntomologico.getDosesAplicadas300m());
		registroEntomologicoDTO.setFocosAedes300m(registroEntomologico.getFocosAedes300m());
		return registroEntomologicoDTO;
	}

	/**
	 * 
	 * Valida se o Registro Entomologico ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(RegistroEntomologicoDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(ent -> result.addError(new ObjectError("Registro Entomologico", dTO.getId() + "já existe")));
	}
}