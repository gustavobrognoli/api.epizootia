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

import com.epizootia.entities.Situacao;
import com.epizootia.response.Response;
import com.epizootia.services.SituacaoService;

@RestController
@RequestMapping("/api/situacao")
public class SituacaoController {

	private static final Logger log = LoggerFactory.getLogger(SituacaoController.class);

	@Autowired
	private SituacaoService service;

	/**
	 * 
	 * Consulta todas as Situações
	 * 
	 * @return List<SituacaoDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Situacao>>> listaTodos() {
		Response<List<Situacao>> response = new Response<List<Situacao>>();

		List<Situacao> situacoes = service.findAll();
			
		if (situacoes.isEmpty()) {
			
			log.error("Não há situações cadastradas");
			response.getErrors().add("Não há situações cadastradas");
	}
		response.setData(situacoes);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Situações por id
	 * 
	 * @return List<Situacao>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Situacao>> consulta(@PathVariable("id") int id) {

		Response<Situacao> response = new Response<Situacao>();
		Optional<Situacao> situacao = service.findById(id);

		if (!situacao.isPresent()) {
			log.error("Id de Situacao do Animal não cadastrado na base de dados");
			response.getErrors().add("Id de Situacao do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(situacao.get());

		log.info("Consulta de Situação do Animal {}", situacao);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Situação do animal na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Situação
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Situacao>> cadastrar(@Valid @RequestBody Situacao situacao, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Situação do animal {}", situacao.toString());

		Response<Situacao> response = new Response<>();
		validaSeExiste(situacao, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(situacao);
		response.setData(situacao);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Situação do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Situacao>> apagar(@PathVariable("id") int id) {

		Response<Situacao> response = new Response<Situacao>();
		Optional<Situacao> situacao = service.findById(id);

		if (!situacao.isPresent()) {
			log.error("Id de Situação do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Situação do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(situacao.get());
		service.apagar(situacao.get());
		log.info("Deletando Situacao do Animal {}", situacao);

		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Valida se o Situacao do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(Situacao situacao, BindingResult result) {
		this.service.findById(situacao.getId()).ifPresent(
				esp -> result.addError(new ObjectError("Situacao do Animal", situacao.getSituacao() + "já existe")));
	}

}
