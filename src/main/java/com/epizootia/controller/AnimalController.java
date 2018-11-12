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

import com.epizootia.dto.AnimalDTO;
import com.epizootia.dto.EspecieDTO;
import com.epizootia.dto.IdadeDTO;
import com.epizootia.dto.NomePopularDTO;
import com.epizootia.dto.SexoDTO;
import com.epizootia.dto.SituacaoDTO;
import com.epizootia.dto.TempoObitoDTO;
import com.epizootia.dto.VisceraDTO;
import com.epizootia.entities.Animal;
import com.epizootia.response.Response;
import com.epizootia.services.AnimalService;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {

	private static final Logger log = LoggerFactory.getLogger(AnimalController.class);

	@Autowired
	private AnimalService service;

	/**
	 * 
	 * Consulta todos os animais
	 * 
	 * @return List<AnimalDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<AnimalDTO>>> listaTodos() {
		Response<List<AnimalDTO>> response = new Response<List<AnimalDTO>>();

		List<AnimalDTO> animalDTOS = service.findAll().stream().map(this::converteEntityParaDTO).collect(Collectors.toList());

		if (animalDTOS.isEmpty()) {

			log.error("Não há animais cadastrados");
			response.getErrors().add("Não há animais cadastrados");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(animalDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de animal por id
	 * 
	 * @return List<AnimalDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<AnimalDTO>> consulta(@PathVariable("id") int id) {

		Response<AnimalDTO> response = new Response<AnimalDTO>();
		Optional<Animal> animal = service.findById(id);

		if (!animal.isPresent()) {

			log.error("Id do Animal não cadastrado na base de dados");
			response.getErrors().add("Id do Animal não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		AnimalDTO animalDTO = converteEntityParaDTO(animal.get());

		response.setData(animalDTO);

		log.info("Consulta do animal {}", animalDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo animal na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Animal
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<AnimalDTO>> cadastrar(@Valid @RequestBody AnimalDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando animal {}", DTO.toString());

		Response<AnimalDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Animal entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta animal da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<AnimalDTO>> apagar(@PathVariable("id") int id) {

		Response<AnimalDTO> response = new Response<AnimalDTO>();
		Optional<Animal> animal = service.findById(id);

		if (!animal.isPresent()) {
			log.error("Id do Animal não cadastrado na base de dados");
			response.getErrors().add("Id do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		AnimalDTO animalDTO = converteEntityParaDTO(animal.get());

		response.setData(animalDTO);
		service.apagar(animal.get());
		log.info("Deletando animal {}", animalDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param animalDTO
	 * @return Entity
	 */
	public Animal converteDTOParaEntity(AnimalDTO animalDTO) {
		Animal animal = new Animal();
		animal.setId(animalDTO.getId());
		
		
		NomePopularController nomePopularController = new NomePopularController();
		if (animalDTO.getNomePopular() == null) {
			animal.setNomePopular(nomePopularController.converteDTOParaEntity(new NomePopularDTO()));
		} else {			
			animal.setNomePopular(nomePopularController.converteDTOParaEntity(animalDTO.getNomePopular()));	
		}
		
		
		EspecieController especieController = new EspecieController(); 
		if (animalDTO.getEspecie() == null) {
			animal.setEspecie(especieController.converteDTOParaEntity(new EspecieDTO()));
		} else {
			animal.setEspecie(especieController.converteDTOParaEntity(animalDTO.getEspecie()));
		}
		
		
		SituacaoController situacaoController = new SituacaoController();
		if (animalDTO.getSituacao() == null) {
			animal.setSituacao(situacaoController.converteDTOParaEntity(new SituacaoDTO()));
		} else {	
			animal.setSituacao(situacaoController.converteDTOParaEntity(animalDTO.getSituacao()));	
		}
			
	
/*		
		AnormalidadeController anormalidadeController = new AnormalidadeController();
		if (animalDTO.getAnormalidades() == null){
			animal.setAnormalidades(anormalidadeController.converteDTOParaEntity(new ArrayList<AnormalidadeDTO>()));
		} else {
			animal.setAnormalidades(anormalidadeController.converteDTOParaEntity(animalDTO.getAnormalidades()));
		}
		
*/
			
		SexoController sexoController = new SexoController();
		if (animalDTO.getSexo() == null) {
 			animal.setSexo(sexoController.converteDTOParaEntity(new SexoDTO()));
		} else { 
			animal.setSexo(sexoController.converteDTOParaEntity(animalDTO.getSexo()));
		}
		
		
		IdadeController idadeController = new IdadeController();
		if (animalDTO.getIdade() == null) {
			animal.setIdade(idadeController.converteDTOParaEntity(new IdadeDTO()));
		} else {
			animal.setIdade(idadeController.converteDTOParaEntity(animalDTO.getIdade()));
		}
		
		
		animal.setApreensao(animalDTO.getApreensao());
		animal.setVidaLivre(animalDTO.getVidaLivre());
		animal.setCativeiro(animalDTO.getCativeiro());
		
		
		TempoObitoController tempoObitoController = new TempoObitoController();
		if (animalDTO.getTempoObito() == null) {
			animal.setTempoObito(tempoObitoController.converteDTOParaEntity(new TempoObitoDTO()));
		} else {
			animal.setTempoObito(tempoObitoController.converteDTOParaEntity(animalDTO.getTempoObito()));
		}
		
	
		VisceraController visceraController = new VisceraController();
		if	(animalDTO.getVisceras() == null) {
			animal.setVisceras(visceraController.converteDTOParaEntity(new VisceraDTO()));
		} else {
			animal.setVisceras(visceraController.converteDTOParaEntity(animalDTO.getVisceras()));			
		}	
		
		return animal;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param animal
	 * @return DTO
	 */
	public AnimalDTO converteEntityParaDTO(Animal animal) {
		AnimalDTO animalDTO = new AnimalDTO();
		animalDTO.setId(animal.getId());
		
		NomePopularController nomePopularController = new NomePopularController();
		animalDTO.setNomePopular(nomePopularController.converteEntityParaDTO(animal.getNomePopular()));	
		
		EspecieController especieController = new EspecieController(); 
		animalDTO.setEspecie(especieController.converteEntityParaDTO(animal.getEspecie()));
		
		SituacaoController situacaoController = new SituacaoController();
		animalDTO.setSituacao(situacaoController.converteEntityParaDTO(animal.getSituacao()));
		
/*		AnormalidadeController anormalidadeController = new AnormalidadeController();
		animalDTO.setAnormalidades(anormalidadeController.converteEntityParaDTO(animal.getAnormalidades()));*/
		
		SexoController sexoController = new SexoController();
		animalDTO.setSexo(sexoController.converteEntityParaDTO(animal.getSexo()));
		
		IdadeController idadeController = new IdadeController();
		animalDTO.setIdade(idadeController.converteEntityParaDTO(animal.getIdade()));
		
		animalDTO.setApreensao(animal.getApreensao());
		animalDTO.setVidaLivre(animal.getVidaLivre());
		animalDTO.setCativeiro(animal.getCativeiro());
		
		TempoObitoController tempoObitoController = new TempoObitoController();
		animalDTO.setTempoObito(tempoObitoController.converteEntityParaDTO(animal.getTempoObito()));
		
		VisceraController visceraController = new VisceraController();
		animalDTO.setVisceras(visceraController.converteEntityParaDTO(animal.getVisceras()));		
		
		return animalDTO;
	}

	/**
	 * 
	 * Valida se o Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(AnimalDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(ani -> result.addError(new ObjectError("Animal", dTO.getNomePopular() + "já existe")));
	}
}