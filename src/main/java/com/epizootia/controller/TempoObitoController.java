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

import com.epizootia.entities.TempoObito;
import com.epizootia.response.Response;
import com.epizootia.services.TempoObitoService;

@RestController
@RequestMapping("/api/tempoObito")
public class TempoObitoController {

	private static final Logger log = LoggerFactory.getLogger(TempoObitoController.class);

	@Autowired
	private TempoObitoService service;

	/**
	 * 
	 * Consulta todos os Nomes Populares
	 * 
	 * @return List<tempoObitoDTO>
	 */

	@GetMapping
	public ResponseEntity<Response<List<TempoObito>>> listaTodos() {
		Response<List<TempoObito>> response = new Response<List<TempoObito>>();

		List<TempoObito> temposObitos = service.findAll();

		if (temposObitos.isEmpty()) {

			log.error("Não há Tempo de Óbito cadastrados");
			response.getErrors().add("Não há Tempo de Óbito cadastrados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(temposObitos);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de Tempo de Óbito por id
	 * 
	 * @return List<TempoObitoDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<TempoObito>> consulta(@PathVariable("id") int id) {

		Response<TempoObito> response = new Response<TempoObito>();
		Optional<TempoObito> tempoObito = service.findById(id);

		if (!tempoObito.isPresent()) {
			log.error("Id de Tempo de Óbito não cadastrado na base de dados");
			response.getErrors().add("Id de Tempo de Óbito não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(tempoObito.get());

		log.info("Consulta de tempo de óbito {}", tempoObito);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo animal na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return TempoObito
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<TempoObito>> cadastrar(@Valid @RequestBody TempoObito tempoObito, BindingResult result) 
			throws NoSuchAlgorithmException {
		log.info("Cadastrando tempo do obito {}", tempoObito.toString());

		Response<TempoObito> response = new Response<>();
		validaSeExiste(tempoObito, result);
		

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(tempoObito);
		response.setData(tempoObito);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta tempo do obito da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<TempoObito>> apagar(@PathVariable("id") int id) {

		Response<TempoObito> response = new Response<TempoObito>();
		Optional<TempoObito> tempoObito = service.findById(id);

		if (!tempoObito.isPresent()) {
			log.error("Id do Tempo do Óbito não cadastrado na base de dados");
			response.getErrors().add("Id do Tempo do Óbito não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(tempoObito.get());
		service.apagar(tempoObito.get());
		log.info("Deletando Tempo do Obito {}", tempoObito);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Valida se o TempoObtito ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(TempoObito tempoObito, BindingResult result) {
		this.service.findById(tempoObito.getId())
				.ifPresent(obt -> result.addError(new ObjectError("Tempo óbito", tempoObito.getTempoObito() + "já existe")));
	}
}
