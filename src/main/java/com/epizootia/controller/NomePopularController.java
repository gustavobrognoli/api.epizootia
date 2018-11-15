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

import com.epizootia.entities.NomePopular;
import com.epizootia.response.Response;
import com.epizootia.services.NomePopularService;

@RestController
@RequestMapping("/api/nomePopular")
public class NomePopularController {

	private static final Logger log = LoggerFactory.getLogger(NomePopularController.class);

	@Autowired
	private NomePopularService service;

	/**
	 * 
	 * Consulta todos os Nomes Populares
	 * 
	 * @return List<NomePopular>
	 */

	@GetMapping
	public ResponseEntity<Response<List<NomePopular>>> listaTodos() {
		Response<List<NomePopular>> response = new Response<List<NomePopular>>();

		List<NomePopular> nomesPopulares = service.findAll();

		if (nomesPopulares.isEmpty()) {

			log.error("Não há nomes populares cadastradas");
			response.getErrors().add("Não há nomes populares cadastradas");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(nomesPopulares);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de Nomes Popular por id
	 * 
	 * @return List<Animal>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<NomePopular>> consulta(@PathVariable("id") int id) {

		Response<NomePopular> response = new Response<NomePopular>();
		Optional<NomePopular> nomePopular = service.findById(id);

		if (!nomePopular.isPresent()) {
			log.error("Id do Nome Popular do Animal não cadastrado na base de dados");
			response.getErrors().add("Id do Nome Popular do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(nomePopular.get());

		log.info("Consulta do Nome Popular do Animal {}", nomePopular);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo Nome Popular do animal na base de dados

	 * @param result
	 * @return NomePopular
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<NomePopular>> cadastrar(@Valid @RequestBody NomePopular nomePopular,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Nome Popular do animal {}", nomePopular.toString());

		Response<NomePopular> response = new Response<>();
		validaSeExiste(nomePopular, result);
		
		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(nomePopular);
		response.setData(nomePopular);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Nomes Popular da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<NomePopular>> apagar(@PathVariable("id") int id) {

		Response<NomePopular> response = new Response<NomePopular>();
		Optional<NomePopular> nomePopular = service.findById(id);

		if (!nomePopular.isPresent()) {
			log.error("Id do Nome Popular do Animal não cadastrado na base de dados");
			response.getErrors().add("Id do Nome Popular do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(nomePopular.get());
		service.apagar(nomePopular.get());
		log.info("Deletando nomePopular {}", nomePopular);

		return ResponseEntity.ok(response);
	}


	/**
	 * 
	 * Valida se o Nome Popular do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(NomePopular nomePopular, BindingResult result) {
		this.service.findById(nomePopular.getId()).ifPresent(
				nmp -> result.addError(new ObjectError("Nome Popular do Animal", nomePopular.getNome() + "já existe")));
	}

}