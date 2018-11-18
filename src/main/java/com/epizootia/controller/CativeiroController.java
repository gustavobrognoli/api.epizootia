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

import com.epizootia.entities.Cativeiro;
import com.epizootia.response.Response;
import com.epizootia.services.CativeiroService;

@RestController
@RequestMapping("/api/cativeiro")
public class CativeiroController {

	private static final Logger log = LoggerFactory.getLogger(EspecieController.class);

	@Autowired
	private CativeiroService service;

	/**
	 * 
	 * Consulta os Cativeiros
	 * 
	 * @return List<Cativeiro>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Cativeiro>>> listaTodos() {
		Response<List<Cativeiro>> response = new Response<List<Cativeiro>>();

		List<Cativeiro> cativeiros = service.findAll();
		
		if (cativeiros.isEmpty()) {

			log.error("Não há cativeiros cadastrados");
			response.getErrors().add("Não há cativeiros cadastrados");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(cativeiros);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Especies por id
	 * 
	 * @return List<Especie>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Cativeiro>> consulta(@PathVariable("id") int id) {

		Response<Cativeiro> response = new Response<Cativeiro>();
		Optional<Cativeiro> cativeiro = service.findById(id);

		if (!cativeiro.isPresent()) {
			log.error("Id de Cativeiro do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Cativeiro do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(cativeiro.get());

		log.info("Consulta de Cativeiro do Animal {}", cativeiro);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Cativeiro do animal na base de dados
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Cativeiro>> cadastrar(@Valid @RequestBody Cativeiro cativeiro, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Especie do animal {}", cativeiro.toString());

		Response<Cativeiro> response = new Response<>();
		validaSeExiste(cativeiro, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(cativeiro);
		response.setData(cativeiro);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Especie do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Cativeiro>> apagar(@PathVariable("id") int id) {

		Response<Cativeiro> response = new Response<Cativeiro>();
		Optional<Cativeiro> cativeiro = service.findById(id);

		if (!cativeiro.isPresent()) {
			log.error("Id de Especie do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Especie do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(cativeiro.get());
		service.apagar(cativeiro.get());
		log.info("Deletando Especie do Animal {}", cativeiro);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Valida se a Especie do Animal ja existe na base de dados
	 * 
	 * @param Especie
	 * @param result
	 */
	private void validaSeExiste(Cativeiro cativeiro, BindingResult result) {
		this.service.findById(cativeiro.getId()).
		ifPresent( esp -> result.addError(new ObjectError("Especie do Animal", cativeiro.getCativeiro() + "já existe")));
	}

}
