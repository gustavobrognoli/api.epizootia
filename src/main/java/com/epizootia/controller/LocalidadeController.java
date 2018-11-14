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

import com.epizootia.entities.Localidade;
import com.epizootia.response.Response;
import com.epizootia.services.LocalidadeService;

@RestController
@RequestMapping("/api/localidade")
public class LocalidadeController {

	private static final Logger log = LoggerFactory.getLogger(LocalidadeController.class);

	@Autowired
	private LocalidadeService service;

	/**
	 * 
	 * Consulta todos os animais
	 * 
	 * @return List<Localidade>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Localidade>>> listaTodos() {
		Response<List<Localidade>> response = new Response<List<Localidade>>();

		List<Localidade> localidades = service.findAll();

		if (localidades.isEmpty()) {

			log.error("Não há Localidades cadastradas");
			response.getErrors().add("Não há Localidades cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(localidades);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de localidade por id
	 * 
	 * @return List<LocalidadeDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Localidade>> consulta(@PathVariable("id") int id) {

		Response<Localidade> response = new Response<Localidade>();
		Optional<Localidade> localidade = service.findById(id);

		if (!localidade.isPresent()) {

			log.error("Id da Localidade não cadastrado na base de dados");
			response.getErrors().add("Id da Localidade não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(localidade.get());

		log.info("Consulta do localidade {}", localidade);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo localidade na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Localidade
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Localidade>> cadastrar(@Valid @RequestBody Localidade localidade, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando localidade {}", localidade.toString());

		Response<Localidade> response = new Response<>();
		validaSeExiste(localidade, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(localidade);
		response.setData(localidade);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta localidade da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Localidade>> apagar(@PathVariable("id") int id) {

		Response<Localidade> response = new Response<Localidade>();
		Optional<Localidade> localidade = service.findById(id);

		if (!localidade.isPresent()) {
			log.error("Id da Localidade não cadastrado na base de dados");
			response.getErrors().add("Id da Localidade não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}


		response.setData(localidade.get());
		service.apagar(localidade.get());
		log.info("Deletando localidade {}", localidade);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Valida se a Localidade ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(Localidade localidade, BindingResult result) {
		this.service.findById(localidade.getId())
				.ifPresent(loc -> result.addError(new ObjectError("Localidade", localidade.getLogradouro() + "já existe")));
	}
}