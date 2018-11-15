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
	 * @return List<Viscera>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Viscera>>> listaTodos() {
		Response<List<Viscera>> response = new Response<List<Viscera>>();

		List<Viscera> visceras = service.findAll();

		if (visceras.isEmpty()) {

			log.error("Não há visceras cadastradas");
			response.getErrors().add("Não há visceras cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(visceras);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de viscera por id
	 * 
	 * @return List<Viscera>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Viscera>> consulta(@PathVariable("id") int id) {

		Response<Viscera> response = new Response<Viscera>();
		Optional<Viscera> viscera = service.findById(id);

		if (!viscera.isPresent()) {

			log.error("Id da Viscera não cadastrada na base de dados");
			response.getErrors().add("Id da Viscera não cadastrada na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(viscera.get());

		log.info("Consulta da Viscera {}", viscera);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova viscera na base de dados
	 * 
	 * @param result
	 * @return Viscera
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Viscera>> cadastrar(@Valid @RequestBody Viscera viscera, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando viscera {}", viscera.toString());

		Response<Viscera> response = new Response<>();
		validaSeExiste(viscera, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(viscera);
		response.setData(viscera);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta viscera da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Viscera>> apagar(@PathVariable("id") int id) {

		Response<Viscera> response = new Response<Viscera>();
		Optional<Viscera> viscera = service.findById(id);

		if (!viscera.isPresent()) {
			log.error("Id da Viscera não cadastrado na base de dados");
			response.getErrors().add("Id da Viscera não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(viscera.get());
		service.apagar(viscera.get());
		log.info("Deletando viscera {}", viscera);

		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Valida se a Anormalidade ja existe na base de dados
	 * 
	 * @param result
	 */
	private void validaSeExiste(Viscera viscera, BindingResult result) {
		this.service.findById(viscera.getId())
				.ifPresent(visc -> result.addError(new ObjectError("Viscera", viscera.getId() + "já existe")));
	}
}
