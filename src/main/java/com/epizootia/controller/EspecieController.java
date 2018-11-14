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

import com.epizootia.entities.Especie;
import com.epizootia.response.Response;
import com.epizootia.services.EspecieService;

@RestController
@RequestMapping("/api/especie")
public class EspecieController {

	private static final Logger log = LoggerFactory.getLogger(EspecieController.class);

	@Autowired
	private EspecieService service;

	/**
	 * 
	 * Consulta todas as Especies
	 * 
	 * @return List<Especie>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Especie>>> listaTodos() {
		Response<List<Especie>> response = new Response<List<Especie>>();

		List<Especie> especies = service.findAll();
		
		if (especies.isEmpty()) {

			log.error("Não há especies cadastradas");
			response.getErrors().add("Não há especies cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(especies);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Especies por id
	 * 
	 * @return List<Especie>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Especie>> consulta(@PathVariable("id") int id) {

		Response<Especie> response = new Response<Especie>();
		Optional<Especie> especie = service.findById(id);

		if (!especie.isPresent()) {
			log.error("Id de Especie do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Especie do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(especie.get());

		log.info("Consulta de Especie do Animal {}", especie);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Especie do animal na base de dados
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Especie>> cadastrar(@Valid @RequestBody Especie especie, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Especie do animal {}", especie.toString());

		Response<Especie> response = new Response<>();
		validaSeExiste(especie, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(especie);
		response.setData(especie);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Especie do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Especie>> apagar(@PathVariable("id") int id) {

		Response<Especie> response = new Response<Especie>();
		Optional<Especie> especie = service.findById(id);

		if (!especie.isPresent()) {
			log.error("Id de Especie do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Especie do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(especie.get());
		service.apagar(especie.get());
		log.info("Deletando Especie do Animal {}", especie);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Valida se a Especie do Animal ja existe na base de dados
	 * 
	 * @param Especie
	 * @param result
	 */
	private void validaSeExiste(Especie especie, BindingResult result) {
		this.service.findById(especie.getId()).
		ifPresent( esp -> result.addError(new ObjectError("Especie do Animal", especie.getEspecie() + "já existe")));
	}

}
