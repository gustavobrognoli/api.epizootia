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

import com.epizootia.dto.ImpactosDTO;
import com.epizootia.entities.Impactos;
import com.epizootia.response.Response;
import com.epizootia.services.ImpactosService;

@RestController
@RequestMapping("/api/impactos")
public class ImpactosController {

	private static final Logger log = LoggerFactory.getLogger(ImpactosController.class);

	@Autowired
	private ImpactosService service;

	/**
	 * 
	 * Consulta todos os animais
	 * 
	 * @return List<ImpactosDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<ImpactosDTO>>> listaTodos() {
		Response<List<ImpactosDTO>> response = new Response<List<ImpactosDTO>>();

		List<ImpactosDTO> impactosDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		if (impactosDTOS.isEmpty()) {

			log.error("Não há impactos cadastrados");
			response.getErrors().add("Não há impactos cadastrados");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(impactosDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de impactos por id
	 * 
	 * @return List<ImpactosDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<ImpactosDTO>> consulta(@PathVariable("id") int id) {

		Response<ImpactosDTO> response = new Response<ImpactosDTO>();
		Optional<Impactos> impactos = service.findById(id);

		if (!impactos.isPresent()) {

			log.error("Id do Impacto não cadastrado na base de dados");
			response.getErrors().add("Id do Impacto não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		ImpactosDTO impactosDTO = converteEntityParaDTO(impactos.get());

		response.setData(impactosDTO);

		log.info("Consulta do impacto {}", impactosDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo impactos na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Impactos
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<ImpactosDTO>> cadastrar(@Valid @RequestBody ImpactosDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando impactos {}", DTO.toString());

		Response<ImpactosDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Impactos entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta impactos da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<ImpactosDTO>> apagar(@PathVariable("id") int id) {

		Response<ImpactosDTO> response = new Response<ImpactosDTO>();
		Optional<Impactos> impactos = service.findById(id);

		if (!impactos.isPresent()) {
			log.error("Id do Impacto não cadastrado na base de dados");
			response.getErrors().add("Id do Impacto não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		ImpactosDTO impactosDTO = converteEntityParaDTO(impactos.get());

		response.setData(impactosDTO);
		service.apagar(impactos.get());
		log.info("Deletando impactos {}", impactosDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param impactosDTO
	 * @return Entity
	 */
	private Impactos converteDTOParaEntity(ImpactosDTO impactosDTO) {
		Impactos impactos = new Impactos();
		impactos.setId(impactosDTO.getId());
		impactos.setAssentamentos(impactosDTO.getAssentamentos());
		impactos.setAlteracaoRio(impactosDTO.getAlteracaoRio());
		impactos.setAvancoAgropecuario(impactosDTO.getAvancoAgropecuario());
		impactos.setDesastres(impactosDTO.getDesastres());
		impactos.setDesmatamento(impactosDTO.getDesmatamento());
		impactos.setPetroleo(impactosDTO.getPetroleo());
		impactos.setObras(impactosDTO.getObras());
		impactos.setAcidentais(impactosDTO.getAcidentais());
		impactos.setImoveis(impactosDTO.getImoveis());
		impactos.setIncendio(impactosDTO.getIncendio());
		impactos.setTurismo(impactosDTO.getTurismo());
		impactos.setUrbanizacao(impactosDTO.getUrbanizacao());
		impactos.setImpactosOutro(impactosDTO.getImpactosOutro());
		impactos.setOutroImpacto(impactosDTO.getOutroImpacto());
		return impactos;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param impactos
	 * @return DTO
	 */
	private ImpactosDTO converteEntityParaDTO(Impactos impactos) {
		ImpactosDTO impactosDTO = new ImpactosDTO();
		impactosDTO.setId(impactosDTO.getId());
		impactosDTO.setAssentamentos(impactos.getAssentamentos());
		impactosDTO.setAlteracaoRio(impactos.getAlteracaoRio());
		impactosDTO.setAvancoAgropecuario(impactos.getAvancoAgropecuario());
		impactosDTO.setDesastres(impactos.getDesastres());
		impactosDTO.setDesmatamento(impactos.getDesmatamento());
		impactosDTO.setPetroleo(impactos.getPetroleo());
		impactosDTO.setObras(impactos.getObras());
		impactosDTO.setAcidentais(impactos.getAcidentais());
		impactosDTO.setImoveis(impactos.getImoveis());
		impactosDTO.setIncendio(impactos.getIncendio());
		impactosDTO.setTurismo(impactos.getTurismo());
		impactosDTO.setUrbanizacao(impactos.getUrbanizacao());
		impactosDTO.setImpactosOutro(impactos.getImpactosOutro());
		impactosDTO.setOutroImpacto(impactos.getOutroImpacto());
		return impactosDTO;	}

	/**
	 * 
	 * Valida se o Impactos ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(ImpactosDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(ani -> result.addError(new ObjectError("Impacto", dTO.getId() + "já existe")));
	}
}
