package com.epizootia.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

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

import com.epizootia.entities.Caracteristica;
import com.epizootia.response.Response;
import com.epizootia.services.CaracteristicaService;

@RestController
@RequestMapping("/api/caracteristica")
public class CaracteristicaController {

	private static final Logger log = LoggerFactory.getLogger(CaracteristicaController.class);

	@Autowired
	private CaracteristicaService service;

	/**
	 * 
	 * Consulta todas as Caracteristica
	 * 
	 * @return List<CaracteristicaDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Caracteristica>>> listaTodos() {
		Response<List<Caracteristica>> response = new Response<List<Caracteristica>>();

		List<Caracteristica> caracteristicas = service.findAll();
		
		if (caracteristicas.isEmpty()) {

			log.error("Não há caracteristica cadastradas");
			response.getErrors().add("Não há caracteristica cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(caracteristicas);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Caracteristica por id
	 * 
	 * @return List<CaracteristicaDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Caracteristica>> consulta(@PathVariable("id") int id) {

		Response<Caracteristica> response = new Response<Caracteristica>();
		Optional<Caracteristica> caracteristica = service.findById(id);

		if (!caracteristica.isPresent()) {
			log.error("Id de Caracteristica não cadastrado na base de dados");
			response.getErrors().add("Id  de Caracteristica não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(caracteristica.get());

		log.info("Consulta de Caracteristica do Animal {}", caracteristica);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Caracteristica do animal na base de dados
	 * 
	 * @param result
	 * @return Caracteristica
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Caracteristica>> cadastrar(@Valid @RequestBody Caracteristica caracteristica, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Caracteristica do animal {}", caracteristica.toString());

		Response<Caracteristica> response = new Response<>();
		validaSeExiste(caracteristica, result);
	

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(caracteristica);
		response.setData(caracteristica);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Caracteristica do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Caracteristica>> apagar(@PathVariable("id") int id) {

		Response<Caracteristica> response = new Response<Caracteristica>();
		Optional<Caracteristica> caracteristica = service.findById(id);

		if (!caracteristica.isPresent()) {
			log.error("Id de Caracteristica do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Caracteristica do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(caracteristica.get());
		service.apagar(caracteristica.get());
		log.info("Deletando Caracteristica do Animal {}", caracteristica);

		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Valida se o Nome Popular do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(Caracteristica caracteristica, BindingResult result) {
		this.service.findById(caracteristica.getId()).
		ifPresent( car -> result.addError(new ObjectError("Caracteristica do Animal", caracteristica.getId() + "já existe")));
	}

}
