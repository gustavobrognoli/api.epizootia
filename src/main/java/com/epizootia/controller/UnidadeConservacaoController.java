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

import com.epizootia.entities.UnidadeConservacao;
import com.epizootia.response.Response;
import com.epizootia.services.UnidadeConservacaoService;

@RestController
@RequestMapping("/api/unidadeConservacao")
public class UnidadeConservacaoController {

	private static final Logger log = LoggerFactory.getLogger(UnidadeConservacaoController.class);

	@Autowired
	private UnidadeConservacaoService service;

	/**
	 * 
	 * Consulta todos os animais
	 * 
	 * @return List<UnidadeConservacaoDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<UnidadeConservacao>>> listaTodos() {
		Response<List<UnidadeConservacao>> response = new Response<List<UnidadeConservacao>>();

		List<UnidadeConservacao> unidadesConservacao = service.findAll();

		if (unidadesConservacao.isEmpty()) {

			log.error("Não há unidades de conservação cadastradas");
			response.getErrors().add("Não há unidades de conservação cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(unidadesConservacao);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de unidades de conservação por id
	 * 
	 * @return List<UnidadeConservacaoDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<UnidadeConservacao>> consulta(@PathVariable("id") int id) {

		Response<UnidadeConservacao> response = new Response<UnidadeConservacao>();
		Optional<UnidadeConservacao> unidadeConservacao = service.findById(id);

		if (!unidadeConservacao.isPresent()) {

			log.error("Id da Unidade de Conservação não cadastrado na base de dados");
			response.getErrors().add("Id da Unidade de Conservação não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(unidadeConservacao.get());

		log.info("Consulta do Unidade de Conservação {}", unidadeConservacao);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Unidade de Conservação na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return UnidadeConservacao
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<UnidadeConservacao>> cadastrar(@Valid @RequestBody UnidadeConservacao unidadeConservacao, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Unidade de Conservação {}", unidadeConservacao.toString());

		Response<UnidadeConservacao> response = new Response<>();
		validaSeExiste(unidadeConservacao, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(unidadeConservacao);
		response.setData(unidadeConservacao);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Unidade de Conservação da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<UnidadeConservacao>> apagar(@PathVariable("id") int id) {

		Response<UnidadeConservacao> response = new Response<UnidadeConservacao>();
		Optional<UnidadeConservacao> unidadeConservacao = service.findById(id);

		if (!unidadeConservacao.isPresent()) {
			log.error("Id da Unidade de Conservação não cadastrado na base de dados");
			response.getErrors().add("Id da Unidade de Conservação não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(unidadeConservacao.get());
		service.apagar(unidadeConservacao.get());
		log.info("Deletando Unidade de Conservação {}", unidadeConservacao);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Valida se o UnidadeConservacao ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(UnidadeConservacao unidadeConservacao, BindingResult result) {
		this.service.findById(unidadeConservacao.getId())
				.ifPresent(ani -> result.addError(new ObjectError("Unidade de Conservação", unidadeConservacao.getNome() + "já existe")));
	}
}
