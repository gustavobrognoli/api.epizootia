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

import com.epizootia.entities.RecomendacaoVacinal;
import com.epizootia.response.Response;
import com.epizootia.services.RecomendacaoVacinalService;

@RestController
@RequestMapping("/api/recomendacaoVacinal")
public class RecomendacaoVacinalController {

	private static final Logger log = LoggerFactory.getLogger(RecomendacaoVacinalController.class);

	@Autowired
	private RecomendacaoVacinalService service;

	/**
	 * 
	 * Consulta todas Recomendações Vacinais
	 * 
	 * @return List<RecomendacaoVacinal>
	 */
	@GetMapping
	public ResponseEntity<Response<List<RecomendacaoVacinal>>> listaTodos() {
		Response<List<RecomendacaoVacinal>> response = new Response<List<RecomendacaoVacinal>>();

		List<RecomendacaoVacinal> recomendacoesVacinais = service.findAll();

		if (recomendacoesVacinais.isEmpty()) {

			log.error("Não há Recomendações Vacinais cadastrados");
			response.getErrors().add("Não há Recomendações Vacinais cadastrados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(recomendacoesVacinais);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Recomendações Vacinais por id
	 * 
	 * @return List<RecomendacaoVacinal>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<RecomendacaoVacinal>> consulta(@PathVariable("id") int id) {

		Response<RecomendacaoVacinal> response = new Response<RecomendacaoVacinal>();
		Optional<RecomendacaoVacinal> recomendacaoVacinal = service.findById(id);

		if (!recomendacaoVacinal.isPresent()) {
			log.error("Id de Recomendação Vacinal não cadastrado na base de dados");
			response.getErrors().add("Id  de Recomendação Vacinal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(recomendacaoVacinal.get());

		log.info("Consulta de Recomendação Vacinal {}", recomendacaoVacinal);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Recomendação Vacinal na base de dados
	 * 
	 * @param result
	 * @return RecomendacaoVacinal
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<RecomendacaoVacinal>> cadastrar(@Valid @RequestBody RecomendacaoVacinal recomendacaoVacinal,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Recomendação Vacinal do animal {}", recomendacaoVacinal.toString());

		Response<RecomendacaoVacinal> response = new Response<>();
		validaSeExiste(recomendacaoVacinal, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(recomendacaoVacinal);
		response.setData(recomendacaoVacinal);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Recomendação Vacinal do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<RecomendacaoVacinal>> apagar(@PathVariable("id") int id) {

		Response<RecomendacaoVacinal> response = new Response<RecomendacaoVacinal>();
		Optional<RecomendacaoVacinal> recomendacaoVacinal = service.findById(id);

		if (!recomendacaoVacinal.isPresent()) {
			log.error("Id de Recomendação Vacinal não cadastrado na base de dados");
			response.getErrors().add("Id de Recomendação Vacinal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

	

		response.setData(recomendacaoVacinal.get());
		service.apagar(recomendacaoVacinal.get());
		log.info("Deletando Recomendação Vacinal do Animal {}", recomendacaoVacinal);

		return ResponseEntity.ok(response);
	}


	/**
	 * 
	 * Valida se o Recomendação Vacinal ja existe na base de dados
	 * 
	 * @param result
	 */
	private void validaSeExiste(RecomendacaoVacinal recomendacaoVacinal, BindingResult result) {
		this.service.findById(recomendacaoVacinal.getId()).ifPresent(rec -> result
				.addError(new ObjectError("Recomendação Vacinal", recomendacaoVacinal.getRecomendacaoVacinal() + "já existe")));
	}

}