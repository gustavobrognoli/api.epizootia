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

import com.epizootia.entities.Apreensao;
import com.epizootia.response.Response;
import com.epizootia.services.ApreensaoService;

@RestController
@RequestMapping("/api/apreensao")
public class ApreensaoController {

	private static final Logger log = LoggerFactory.getLogger(ApreensaoController.class);

	@Autowired
	private ApreensaoService service;

	/**
	 * 
	 * Consulta todas as Apreensões
	 * 
	 * @return List<Especie>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Apreensao>>> listaTodos() {
		Response<List<Apreensao>> response = new Response<List<Apreensao>>();

		List<Apreensao> apreensoes = service.findAll();
		
		if (apreensoes.isEmpty()) {

			log.error("Não há Apreensões cadastradas");
			response.getErrors().add("Não há Apreensões cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(apreensoes);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Apreensões por id
	 * 
	 * @return List<Apreensao>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Apreensao>> consulta(@PathVariable("id") int id) {

		Response<Apreensao> response = new Response<Apreensao>();
		Optional<Apreensao> apreensao = service.findById(id);

		if (!apreensao.isPresent()) {
			log.error("Id de Apreensão do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Apreensão do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(apreensao.get());

		log.info("Consulta de Apreensão do Animal {}", apreensao);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Apreensão do animal na base de dados
	 * 
	 * @param result
	 * @return apreensao
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Apreensao>> cadastrar(@Valid @RequestBody Apreensao apreensao, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Apreensão do animal {}", apreensao.toString());

		Response<Apreensao> response = new Response<>();
		validaSeExiste(apreensao, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(apreensao);
		response.setData(apreensao);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Especie do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Apreensao>> apagar(@PathVariable("id") int id) {

		Response<Apreensao> response = new Response<Apreensao>();
		Optional<Apreensao> apreensao = service.findById(id);

		if (!apreensao.isPresent()) {
			log.error("Id de Apreensao do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Apreensao do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(apreensao.get());
		service.apagar(apreensao.get());
		log.info("Deletando Especie do Animal {}", apreensao);

		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Valida se a Especie do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(Apreensao apreensao, BindingResult result) {
		this.service.findById(apreensao.getId()).
		ifPresent( Apr -> result.addError(new ObjectError("Apreensão ", apreensao.getApreensao() + "do Animal já existe")));
	}

}
