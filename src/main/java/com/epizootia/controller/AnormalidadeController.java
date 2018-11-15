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

import com.epizootia.entities.Anormalidade;
import com.epizootia.response.Response;
import com.epizootia.services.AnormalidadeService;

@RestController
@RequestMapping("/api/anormalidade")
public class AnormalidadeController {

	private static final Logger log = LoggerFactory.getLogger(AnormalidadeController.class);

	@Autowired
	private AnormalidadeService service;

	/**
	 * 
	 * Consulta todas as Anormalidades
	 * 
	 * @return List<AnormalidadeDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Anormalidade>>> listaTodos() {
		Response<List<Anormalidade>> response = new Response<List<Anormalidade>>();

		List<Anormalidade> anormalidades = service.findAll();
		
		if (anormalidades.isEmpty()) {

			log.error("Não há Anormalidades cadastradas");
			response.getErrors().add("Não há Anormalidades cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(anormalidades);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Anormalidades por id
	 * 
	 * @return List<Especie>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Anormalidade>> consulta(@PathVariable("id") int id) {

		Response<Anormalidade> response = new Response<Anormalidade>();
		Optional<Anormalidade> anormalidade = service.findById(id);

		if (!anormalidade.isPresent()) {
			log.error("Id de Anormalidade do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Anormalidade do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(anormalidade.get());

		log.info("Consulta de Anormalidade do Animal {}", anormalidade);

		return ResponseEntity.ok(response);
	}

	
//	Criar GET para pegar os selecionados
	
	
	/**
	 * 
	 * Cadastra nova Anormalidade do animal na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Anormalidade
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Anormalidade>> cadastrar(@Valid @RequestBody Anormalidade anormalidade, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Anormalidade do animal {}", anormalidade.toString());

		Response<Anormalidade> response = new Response<>();
		validaSeExiste(anormalidade, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(anormalidade);
		response.setData(anormalidade);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Anormalidade do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Anormalidade>> apagar(@PathVariable("id") int id) {

		Response<Anormalidade> response = new Response<Anormalidade>();
		Optional<Anormalidade> anormalidade = service.findById(id);

		if (!anormalidade.isPresent()) {
			log.error("Id de Anormalidade do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Anormalidade do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(anormalidade.get());
		service.apagar(anormalidade.get());
		log.info("Deletando Anormalidade do Animal {}", anormalidade);

		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Valida se a Anormalidade do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(Anormalidade anormalidade, BindingResult result) {
		this.service.findById(anormalidade.getId()).
		ifPresent( esp -> result.addError(new ObjectError("Anormalidade do Animal", anormalidade.getSintoma() + "já existe")));
	}

}
