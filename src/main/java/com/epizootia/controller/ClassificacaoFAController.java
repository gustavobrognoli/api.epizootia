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

import com.epizootia.entities.ClassificacaoFA;
import com.epizootia.response.Response;
import com.epizootia.services.ClassificacaoFAService;

@RestController
@RequestMapping("/api/classificacaoFA")
public class ClassificacaoFAController {

	private static final Logger log = LoggerFactory.getLogger(ClassificacaoFAController.class);

	@Autowired
	private ClassificacaoFAService service;

	/**
	 * 
	 * Consulta todas as ClassificacaoFA
	 * 
	 * @return List<ClassificacaoFADTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<ClassificacaoFA>>> listaTodos() {
		Response<List<ClassificacaoFA>> response = new Response<List<ClassificacaoFA>>();

		List<ClassificacaoFA> classificacoesFA = service.findAll();

		if (classificacoesFA.isEmpty()) {

			log.error("Não há classificações de Febre Amarela cadastradas");
			response.getErrors().add("Não há classificações de Febre Amarela cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(classificacoesFA);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de classificacaoFA por id
	 * 
	 * @return List<ClassificacaoFA>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<ClassificacaoFA>> consulta(@PathVariable("id") int id) {

		Response<ClassificacaoFA> response = new Response<ClassificacaoFA>();
		Optional<ClassificacaoFA> classificacaoFA = service.findById(id);

		if (!classificacaoFA.isPresent()) {

			log.error("Id da ClassificacaoFA não cadastrada na base de dados");
			response.getErrors().add("Id da ClassificacaoFA não cadastrada na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(classificacaoFA.get());

		log.info("Consulta da ClassificacaoFA {}", classificacaoFA);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova classificacaoFA na base de dados
	 * 
	 * @param result
	 * @return ClassificacaoFA
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<ClassificacaoFA>> cadastrar(@Valid @RequestBody ClassificacaoFA classificacaoFA,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando classificacaoFA {}", classificacaoFA.toString());

		Response<ClassificacaoFA> response = new Response<>();
		validaSeExiste(classificacaoFA, result);
		
		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(classificacaoFA);
		response.setData(classificacaoFA);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta classificacaoFA da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<ClassificacaoFA>> apagar(@PathVariable("id") int id) {

		Response<ClassificacaoFA> response = new Response<ClassificacaoFA>();
		Optional<ClassificacaoFA> classificacaoFA = service.findById(id);

		if (!classificacaoFA.isPresent()) {
			log.error("Id da ClassificacaoFA não cadastrado na base de dados");
			response.getErrors().add("Id da ClassificacaoFA não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(classificacaoFA.get());
		service.apagar(classificacaoFA.get());
		log.info("Deletando classificacaoFA {}", classificacaoFA);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Valida se a ClassificacaoFA ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(ClassificacaoFA classificacaoFA, BindingResult result) {
		this.service.findById(classificacaoFA.getId())
				.ifPresent(ano -> result.addError(new ObjectError("ClassificacaoFA", classificacaoFA.getClassificacao() + "já existe")));
	}

}
