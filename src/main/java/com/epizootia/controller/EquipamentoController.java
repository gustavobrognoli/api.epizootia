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

import com.epizootia.entities.Equipamento;
import com.epizootia.response.Response;
import com.epizootia.services.EquipamentoService;

@RestController
@RequestMapping("/api/equipamento")
public class EquipamentoController {
	private static Logger log = LoggerFactory.getLogger(EquipamentoController.class);

	@Autowired
	private EquipamentoService service;

	/**
	 * 
	 * Consulta todos as equipamentos
	 * 
	 * @return List<Equipamento>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Equipamento>>> listaTodos() {
		Response<List<Equipamento>> response = new Response<List<Equipamento>>();

		List<Equipamento> equipamentos = service.findAll();

		if(equipamentos.isEmpty()) {
		
		log.error("Não há equipamentos cadastradas");
		response.getErrors().add("Não há equipamentos cadastradas");
		
		return ResponseEntity.badRequest().body(response);
	}
		response.setData(equipamentos);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de equipamento atravez do id
	 * 
	 * @return List<Equipamento>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Equipamento>> consulta(@PathVariable("id") int id) {

		Response<Equipamento> response = new Response<Equipamento>();
		Optional<Equipamento> equipamento = service.findById(id);

		if(!equipamento.isPresent()) {
			log.error("Id de Equipamento não cadastrado na base de dados");
			response.getErrors().add("Id de Equipamento não cadastrado na base de dados");
			
			return ResponseEntity.badRequest().body(response);
		}
				
		response.setData(equipamento.get());
		
		log.info("Consulta de equipamento {}", equipamento);
		
		return ResponseEntity.ok(response);
	}
	
	/**
	 * 
	 * Cadastra nova Equipamento na base de dados
	 *
	 * @param result
	 * @return Equipamento
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Equipamento>> cadastrar(@Valid @RequestBody Equipamento equipamento, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando equipamento {}", equipamento.toString());
		
		Response<Equipamento> response = new Response<>();
		validaSeExiste(equipamento, result);
		
		if (result.hasErrors()) {
			log.error("Erro ao validar as informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.service.persistir(equipamento);
		response.setData(equipamento);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * 
	 * Deleta equipamento da base de dados
	 * 
	 */
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Equipamento>> apagar(@PathVariable("id") int id) {
		
		Response<Equipamento> response = new Response<Equipamento>();
		Optional<Equipamento> equipamento = service.findById(id);
		
		if (!equipamento.isPresent()) {
			log.error("Id de equipamento não cadastrando na base de dados");
			response.getErrors().add("Id de equipamento não cadastrando na base de dados");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(equipamento.get());
		service.apagar(equipamento.get());
		log.info("Apagando equipamento {}", equipamento);
		
		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Valida se a Equipamento ja existe na base de dados
	 * 
	 * @param result
	 */
	private void validaSeExiste(Equipamento equipamento, BindingResult result) {
		this.service.findById(equipamento.getId())
			.ifPresent(eqp -> result.addError(new ObjectError("Equipamento", equipamento.getId() + " já existe")));
	}
}	

