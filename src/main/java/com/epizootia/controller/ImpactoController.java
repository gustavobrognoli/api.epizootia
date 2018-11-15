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

import com.epizootia.entities.Impacto;
import com.epizootia.response.Response;
import com.epizootia.services.ImpactoService;

@RestController
@RequestMapping("/api/impacto")
public class ImpactoController {

	private static final Logger log = LoggerFactory.getLogger(ImpactoController.class);

	@Autowired
	private ImpactoService service;

	/**
	 * 
	 * Consulta todos os animais
	 * 
	 * @return List<Impacto>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Impacto>>> listaTodos() {
		Response<List<Impacto>> response = new Response<List<Impacto>>();

		List<Impacto> impactos = service.findAll();

		if (impactos.isEmpty()) {

			log.error("Não há impactos cadastrados");
			response.getErrors().add("Não há impactos cadastrados");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(impactos);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de impactos por id
	 * 
	 * @return List<ImpactosDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Impacto>> consulta(@PathVariable("id") int id) {

		Response<Impacto> response = new Response<Impacto>();
		Optional<Impacto> impacto = service.findById(id);

		if (!impacto.isPresent()) {

			log.error("Id do Impacto não cadastrado na base de dados");
			response.getErrors().add("Id do Impacto não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(impacto.get());

		log.info("Consulta do impacto {}", impacto);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo impactos na base de dados
	 * 
	 * @param result
	 * @return Impacto
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Impacto>> cadastrar(@Valid @RequestBody Impacto impacto, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando impactos {}", impacto.toString());

		Response<Impacto> response = new Response<>();
		validaSeExiste(impacto, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(impacto);
		response.setData(impacto);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta impactos da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Impacto>> apagar(@PathVariable("id") int id) {

		Response<Impacto> response = new Response<Impacto>();
		Optional<Impacto> impacto = service.findById(id);

		if (!impacto.isPresent()) {
			log.error("Id do Impacto não cadastrado na base de dados");
			response.getErrors().add("Id do Impacto não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(impacto.get());
		service.apagar(impacto.get());
		log.info("Deletando impactos {}", impacto);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Valida se o Impacto ja existe na base de dados
	 * 
	 * @param result
	 */
	private void validaSeExiste(Impacto impacto, BindingResult result) {
		this.service.findById(impacto.getId())
				.ifPresent(ani -> result.addError(new ObjectError("Impacto", impacto.getId() + "já existe")));
	}
}
