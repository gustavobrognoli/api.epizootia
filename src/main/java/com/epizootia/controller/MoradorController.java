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

import com.epizootia.entities.Morador;
import com.epizootia.response.Response;
import com.epizootia.services.MoradorService;

@RestController
@RequestMapping("/api/morador")
public class MoradorController {

	private static final Logger log = LoggerFactory.getLogger(MoradorController.class);

	@Autowired
	private MoradorService service;

	/**
	 * 
	 * Consulta todas os Moradores
	 * 
	 * @return List<MoradorDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Morador>>> listaTodos() {
		Response<List<Morador>> response = new Response<List<Morador>>();

		List<Morador> moradores = service.findAll();

		if (moradores.isEmpty()) {

			log.error("Não há Moradores cadastrados");
			response.getErrors().add("Não há Moradores cadastrados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(moradores);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todos os Moradores por id
	 * 
	 * @return List<MoradorDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Morador>> consulta(@PathVariable("id") int id) {

		Response<Morador> response = new Response<Morador>();
		Optional<Morador> morador = service.findById(id);

		if (!morador.isPresent()) {
			log.error("Id de Morador não cadastrado na base de dados");
			response.getErrors().add("Id de Morador não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(morador.get());

		log.info("Consulta de Morador {}", morador);

		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{telefone}")
	public ResponseEntity<Response<Morador>> consultaTelefone(@PathVariable("telefone") String telefone) {

		Response<Morador> response = new Response<Morador>();
		Optional<Morador> morador = service.findTelefone(telefone);

		if (!morador.isPresent()) {
			log.error("Telefone de Morador não cadastrado na base de dados");
			response.getErrors().add("Telefone de Morador não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(morador.get());

		log.info("Consulta de Morador {}", morador);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo Morador na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Morador
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Morador>> cadastrar(@Valid @RequestBody Morador morador, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Morador {}", morador.toString());

		Response<Morador> response = new Response<>();
		validaSeExiste(morador, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(morador);
		response.setData(morador);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Morador da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Morador>> apagar(@PathVariable("id") int id) {

		Response<Morador> response = new Response<Morador>();
		Optional<Morador> morador = service.findById(id);

		if (!morador.isPresent()) {
			log.error("Id de Morador do Animal não cadastrado na base de dados");
			response.getErrors().add("Id de Morador do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(morador.get());
		service.apagar(morador.get());
		log.info("Deletando Morador{}", morador);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Valida se o Morador ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(Morador morador, BindingResult result) {
		this.service.findById(morador.getId())
				.ifPresent(sex -> result.addError(new ObjectError("Morador do Animal", morador.getMorador() + "já existe")));
	}

}
