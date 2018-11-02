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

import com.epizootia.dto.CaracteristicasDTO;
import com.epizootia.entities.Caracteristicas;
import com.epizootia.response.Response;
import com.epizootia.services.CaracteristicasService;

@RestController
@RequestMapping("/api/caracteristicas")
public class CaracteristicasController {

	private static final Logger log = LoggerFactory.getLogger(CaracteristicasController.class);

	@Autowired
	private CaracteristicasService service;

	/**
	 * 
	 * Consulta todas as Caracteristicas
	 * 
	 * @return List<CaracteristicasDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<CaracteristicasDTO>>> listaTodos() {
		Response<List<CaracteristicasDTO>> response = new Response<List<CaracteristicasDTO>>();

		List<CaracteristicasDTO> caracteristicasDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());
		
		if (caracteristicasDTOS.isEmpty()) {

			log.error("Não há caracteristicas cadastradas");
			response.getErrors().add("Não há caracteristicas cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(caracteristicasDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Caracteristicas por id
	 * 
	 * @return List<CaracteristicasDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<CaracteristicasDTO>> consulta(@PathVariable("id") int id) {

		Response<CaracteristicasDTO> response = new Response<CaracteristicasDTO>();
		Optional<Caracteristicas> caracteristicas = service.findById(id);

		if (!caracteristicas.isPresent()) {
			log.error("Id de Caracteristicas não cadastrado na base de dados");
			response.getErrors().add("Id  de Caracteristicas não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		CaracteristicasDTO caracteristicasDTO = converteEntityParaDTO(caracteristicas.get());

		response.setData(caracteristicasDTO);

		log.info("Consulta de Caracteristicas do Animal {}", caracteristicasDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Caracteristicas do animal na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Caracteristicas
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CaracteristicasDTO>> cadastrar(@Valid @RequestBody CaracteristicasDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Caracteristicas do animal {}", DTO.toString());

		Response<CaracteristicasDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Caracteristicas entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta Caracteristicas do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<CaracteristicasDTO>> apagar(@PathVariable("id") int id) {

		Response<CaracteristicasDTO> response = new Response<CaracteristicasDTO>();
		Optional<Caracteristicas> caracteristicas = service.findById(id);

		if (!caracteristicas.isPresent()) {
			log.error("Id de Caracteristicas do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Caracteristicas do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		CaracteristicasDTO caracteristicasDTO = converteEntityParaDTO(caracteristicas.get());

		response.setData(caracteristicasDTO);
		service.apagar(caracteristicas.get());
		log.info("Deletando Caracteristicas do Animal {}", caracteristicasDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param caracteristicasDTO
	 * @return Entity
	 */

	public Caracteristicas converteDTOParaEntity(CaracteristicasDTO caracteristicasDTO) {
		Caracteristicas caracteristicas = new Caracteristicas();
		caracteristicas.setId(caracteristicasDTO.getId());
		caracteristicas.setNatural(caracteristicasDTO.getNatural());
		caracteristicas.setDomicilio(caracteristicasDTO.getDomicilio());
		caracteristicas.setRural(caracteristicasDTO.getRural());
		caracteristicas.setUrbano(caracteristicasDTO.getUrbano());
		return caracteristicas;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param caracteristicasDTO
	 * @return DTO
	 */

	public CaracteristicasDTO converteEntityParaDTO(Caracteristicas caracteristicas) {
		CaracteristicasDTO caracteristicasDTO = new CaracteristicasDTO();
		caracteristicasDTO.setId(caracteristicas.getId());
		caracteristicasDTO.setNatural(caracteristicas.getNatural());
		caracteristicasDTO.setDomicilio(caracteristicas.getDomicilio());
		caracteristicasDTO.setRural(caracteristicas.getRural());
		caracteristicasDTO.setUrbano(caracteristicas.getUrbano());
		return caracteristicasDTO;
	}

	/**
	 * 
	 * Valida se o Nome Popular do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(CaracteristicasDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId()).
		ifPresent( car -> result.addError(new ObjectError("Caracteristicas do Animal", dTO.getId() + "já existe")));
	}

}
