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

import com.epizootia.entities.Genero;
import com.epizootia.response.Response;
import com.epizootia.services.GeneroService;

@RestController
@RequestMapping("/api/genero")
public class GeneroController {

	private static final Logger log = LoggerFactory.getLogger(GeneroController.class);

	@Autowired
	private GeneroService service;

	/**
	 * 
	 * Consulta todas os Generos
	 * 
	 * @return List<Genero>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Genero>>> listaTodos() {
		Response<List<Genero>> response = new Response<List<Genero>>();

		List<Genero> generos = service.findAll();

		if (generos.isEmpty()) {

			log.error("Não há especies cadastradas");
			response.getErrors().add("Não há especies cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(generos);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Especies por id
	 * 
	 * @return List<Genero>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Genero>> consulta(@PathVariable("id") int id) {

		Response<Genero> response = new Response<Genero>();
		Optional<Genero> genero = service.findById(id);

		if (!genero.isPresent()) {
			log.error("Id de Especie do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Especie do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(genero.get());

		log.info("Consulta de Especie do Animal {}", genero);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Genero do animal na base de dados
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Genero>> cadastrar(@Valid @RequestBody Genero genero, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Especie do animal {}", genero.toString());

		Response<Genero> response = new Response<>();
		validaSeExiste(genero, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(genero);
		response.setData(genero);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Especie do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Genero>> apagar(@PathVariable("id") int id) {

		Response<Genero> response = new Response<Genero>();
		Optional<Genero> genero = service.findById(id);

		if (!genero.isPresent()) {
			log.error("Id de Especie do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Especie do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(genero.get());
		service.apagar(genero.get());
		log.info("Deletando Especie do Animal {}", genero);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Valida se a Especie do Animal ja existe na base de dados
	 * 
	 * @param Especie
	 * @param result
	 */
	private void validaSeExiste(Genero genero, BindingResult result) {
		this.service.findById(genero.getId()).ifPresent(
				esp -> result.addError(new ObjectError("Genero do Animal", genero.getGenero() + "já existe")));
	}

}
