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

import com.epizootia.entities.Idade;
import com.epizootia.response.Response;
import com.epizootia.services.IdadeService;

@RestController
@RequestMapping("/api/idade")
public class IdadeController {

	private static Logger log = LoggerFactory.getLogger(IdadeController.class);

	@Autowired
	private IdadeService service;

	/**
	 * 
	 * Consulta todas as idades
	 * 
	 * @return List<Idade>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Idade>>> listaTodos() {
		Response<List<Idade>> response = new Response<List<Idade>>();

		List<Idade> idades = service.findAll();

		if (idades.isEmpty()) {

			log.error("Não há idades cadastradas");
			response.getErrors().add("Não há idades cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(idades);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de idade atravez do id
	 * 
	 * @return List<Idade>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Idade>> consulta(@PathVariable("id") int id) {

		Response<Idade> response = new Response<Idade>();
		Optional<Idade> idade = service.findById(id);

		if (!idade.isPresent()) {
			log.error("Id de Idade não cadastrado na base de dados");
			response.getErrors().add("Id de Idade não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(idade.get());

		log.info("Consulta de idade {}", idade);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Idade na base de dados
	 * 
	 * @param result
	 * @return Idade
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Idade>> cadastrar(@Valid @RequestBody Idade idade, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando idade {}", idade.toString());

		Response<Idade> response = new Response<>();
		validaSeExiste(idade, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar as informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(idade);
		response.setData(idade);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta idade da base de dados
	 * 
	 */

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Idade>> apagar(@PathVariable("id") int id) {

		Response<Idade> response = new Response<Idade>();
		Optional<Idade> idade = service.findById(id);

		if (!idade.isPresent()) {
			log.error("Id de idade não cadastrando na base de dados");
			response.getErrors().add("Id de idade não cadastrando na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(idade.get());
		service.apagar(idade.get());
		log.info("Apagando idade {}", idade);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Valida se a Idade ja existe na base de dados
	 * 
	 * @param result
	 */
	private void validaSeExiste(Idade idade, BindingResult result) {
		this.service.findById(idade.getId())
				.ifPresent(ida -> result.addError(new ObjectError("Idade", idade.getIdade() + " já existe")));
	}
}
