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

import com.epizootia.entities.IsolamentoViral;
import com.epizootia.response.Response;
import com.epizootia.services.IsolamentoViralService;

@RestController
@RequestMapping("/api/isolamentoViral")
public class IsolamentoViralController {
	
	private static final Logger log = LoggerFactory.getLogger(IsolamentoViralController.class);

	@Autowired
	private IsolamentoViralService service;

	/**
	 * 
	 * Consulta todos os Isolamentos Virais
	 * 
	 * @return List<AnormalidadeDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<IsolamentoViral>>> listaTodos() {
		Response<List<IsolamentoViral>> response = new Response<List<IsolamentoViral>>();

		List<IsolamentoViral> isolamentosVirais = service.findAll();

		if (isolamentosVirais.isEmpty()) {

			log.error("Não há Isolamentos Virais cadastradas");
			response.getErrors().add("Não há Isolamentos Virais cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(isolamentosVirais);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de Isolamento Viral por id
	 * 
	 * @return List<IsolamentoViralDTO>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<IsolamentoViral>> consulta(@PathVariable("id") int id) {

		Response<IsolamentoViral> response = new Response<IsolamentoViral>();
		Optional<IsolamentoViral> isolamentoViral = service.findById(id);

		if (!isolamentoViral.isPresent()) {

			log.error("Id do Isolamento Viral não cadastrado na base de dados");
			response.getErrors().add("Id do Isolamento Viral não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(isolamentoViral.get());

		log.info("Consulta do Isolamento Viral {}", isolamentoViral);

		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Cadastra novo Isolamento Viral na base de dados
	 * 
	 * @param DTO
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<IsolamentoViral>> cadastrar(@Valid @RequestBody IsolamentoViral isolamentoViral, BindingResult result) 
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Isolamento Viral {}", isolamentoViral.toString());
		
		Response<IsolamentoViral> response = new Response<>();
		validaSeExiste(isolamentoViral, result);
		
		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.service.persistir(isolamentoViral);
		response.setData(isolamentoViral);
		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Deleta Isolamento Viral da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<IsolamentoViral>> apagar(@PathVariable("id") int id){
		
		Response<IsolamentoViral> response = new Response<IsolamentoViral>();
		Optional<IsolamentoViral> isolamentoViral = service.findById(id);
		
		if (!isolamentoViral.isPresent()) {
			log.error("Id do Isolamento Viral não cadastrado na base de dados");
			response.getErrors().add("Id do Isolamento Viral não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(isolamentoViral.get());
		service.apagar(isolamentoViral.get());
		log.info("Deletando Isolamento Viral {}", isolamentoViral);
		
		return ResponseEntity.ok(response);
	}
	
	/**
	 * 
	 * Valida se a Isolamento Viral ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(IsolamentoViral isolamentoViral, BindingResult result) {
		this.service.findById(isolamentoViral.getId())
			.ifPresent(iso -> result.addError(new ObjectError("Isolamento Viral", isolamentoViral.getId() + "já existe")));
	}
}
