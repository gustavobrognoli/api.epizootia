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

import com.epizootia.dto.IsolamentoViralDTO;
import com.epizootia.entities.IsolamentoViral;
import com.epizootia.response.Response;
import com.epizootia.services.IsolamentoViralService;

@RestController
@RequestMapping("/api/isolamentoViral")
public class IsolamentoViralController {
	
	private static final Logger log = LoggerFactory.getLogger(IsolamentoViralController.class);

	@Autowired
	private IsolamentoViralService service;

	/**
	 * 
	 * Consulta todos os Isolamentos Virais
	 * 
	 * @return List<AnormalidadeDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<IsolamentoViralDTO>>> listaTodos() {
		Response<List<IsolamentoViralDTO>> response = new Response<List<IsolamentoViralDTO>>();

		List<IsolamentoViralDTO> isolamentoViralDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if (isolamentoViralDTOS.isEmpty()) {

			log.error("Não há Isolamentos Virais cadastradas");
			response.getErrors().add("Não há Isolamentos Virais cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(isolamentoViralDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de Isolamento Viral por id
	 * 
	 * @return List<IsolamentoViralDTO>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<IsolamentoViralDTO>> consulta(@PathVariable("id") int id) {

		Response<IsolamentoViralDTO> response = new Response<IsolamentoViralDTO>();
		Optional<IsolamentoViral> isolamentoViral = service.findById(id);

		if (!isolamentoViral.isPresent()) {

			log.error("Id do Isolamento Viral não cadastrado na base de dados");
			response.getErrors().add("Id do Isolamento Viral não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		IsolamentoViralDTO isolamentoViralDTO = converteEntityParaDTO(isolamentoViral.get());

		response.setData(isolamentoViralDTO);

		log.info("Consulta do Isolamento Viral {}", isolamentoViralDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo Isolamento Viral na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Isolamento Viral
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<IsolamentoViralDTO>> cadastrar(@Valid @RequestBody IsolamentoViralDTO DTO, BindingResult result) 
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Isolamento Viral {}", DTO.toString());
		
		Response<IsolamentoViralDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		IsolamentoViral entity = this.converteDTOParaEntity(DTO);
		
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
	 * Deleta Isolamento Viral da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<IsolamentoViralDTO>> apagar(@PathVariable("id") int id){
		
		Response<IsolamentoViralDTO> response = new Response<IsolamentoViralDTO>();
		Optional<IsolamentoViral> isolamentoViral = service.findById(id);
		
		if (!isolamentoViral.isPresent()) {
			log.error("Id do Isolamento Viral não cadastrado na base de dados");
			response.getErrors().add("Id do Isolamento Viral não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}
		
		IsolamentoViralDTO isolamentoViralDTO = converteEntityParaDTO(isolamentoViral.get());
		
		response.setData(isolamentoViralDTO);
		service.apagar(isolamentoViral.get());
		log.info("Deletando Isolamento Viral {}", isolamentoViralDTO);
		
		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param isolamentoViralDTO
	 * @return Entity
	 */
	private IsolamentoViral converteDTOParaEntity(IsolamentoViralDTO isolamentoViralDTO) {
		IsolamentoViral isolamentoViral = new IsolamentoViral();
		isolamentoViral.setId(isolamentoViralDTO.getId());
		isolamentoViral.setIsolamentoViral(isolamentoViralDTO.getIsolamentoViral());
		isolamentoViral.setResultado(isolamentoViralDTO.getResultado());
		isolamentoViral.setHaemagogus(isolamentoViralDTO.getHaemagogus());
		isolamentoViral.setSabethes(isolamentoViralDTO.getSabethes());
		isolamentoViral.setAegypti(isolamentoViralDTO.getAegypti());
		isolamentoViral.setAnopheles(isolamentoViralDTO.getAnopheles());
		isolamentoViral.setAlbopictus(isolamentoViralDTO.getAlbopictus());
		return isolamentoViral;
	}
	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param Isolamento Viral
	 * @return DTO
	 */
	private IsolamentoViralDTO converteEntityParaDTO(IsolamentoViral isolamentoViral) {
		IsolamentoViralDTO isolamentoViralDTO = new IsolamentoViralDTO();
		isolamentoViralDTO.setId(isolamentoViral.getId());
		isolamentoViralDTO.setIsolamentoViral(isolamentoViral.getIsolamentoViral());
		isolamentoViralDTO.setResultado(isolamentoViral.getResultado());
		isolamentoViralDTO.setHaemagogus(isolamentoViralDTO.getHaemagogus());
		isolamentoViralDTO.setSabethes(isolamentoViralDTO.getSabethes());
		isolamentoViralDTO.setAegypti(isolamentoViralDTO.getAegypti());
		isolamentoViralDTO.setAnopheles(isolamentoViralDTO.getAnopheles());
		isolamentoViralDTO.setAlbopictus(isolamentoViralDTO.getAlbopictus());
		return isolamentoViralDTO;
	}
	
	/**
	 * 
	 * Valida se a Isolamento Viral ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(IsolamentoViralDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
			.ifPresent(ano -> result.addError(new ObjectError("Isolamento Viral", dTO.getId() + "já existe")));
	}
}
