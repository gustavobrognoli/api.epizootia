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

import com.epizootia.entities.Animal;
import com.epizootia.response.Response;
import com.epizootia.services.AnimalService;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {

	private static final Logger log = LoggerFactory.getLogger(AnimalController.class);

	@Autowired
	private AnimalService service;

	/**
	 * 
	 * Consulta todos os animais
	 * 
	 * @return List<Animal>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Animal>>> listaTodos() {
		Response<List<Animal>> response = new Response<List<Animal>>();

		List<Animal> animais = service.findAll();
		if (animais.isEmpty()) {

			log.error("Não há animais cadastrados");
			response.getErrors().add("Não há animais cadastrados");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(animais);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas os Animais por Ficha
	 * 
	* @return List<Animal>
	 */
		
	@GetMapping(value = "/ficha/{id}")
	public ResponseEntity<Response<List<Animal>>> listaAnimaisFicha(@PathVariable("id") int id) {
		Response<List<Animal>> response = new Response<List<Animal>>();

		List<Animal> animais = service.findAllByFicha(id);

		response.setData(animais);

		return ResponseEntity.ok(response);
	}
	
	/**
	 * 
	 * Consulta de animal por id
	 * 
	 * @return List<AnimalDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Animal>> consulta(@PathVariable("id") int id) {

		Response<Animal> response = new Response<Animal>();
		Optional<Animal> animal = service.findById(id);

		if (!animal.isPresent()) {

			log.error("Id do Animal não cadastrado na base de dados");
			response.getErrors().add("Id do Animal não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(animal.get());

		log.info("Consulta do animal {}", animal);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo animal na base de dados
	 * 
	 * @param result
	 * @return Animal
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Animal>> cadastrar(@Valid @RequestBody Animal animal, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando animal {}", animal.toString());

		Response<Animal> response = new Response<>();
		validaSeExiste(animal, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(animal);
		response.setData(animal);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta animal da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Animal>> apagar(@PathVariable("id") int id) {

		Response<Animal> response = new Response<Animal>();
		Optional<Animal> animal = service.findById(id);

		if (!animal.isPresent()) {
			log.error("Id do Animal não cadastrado na base de dados");
			response.getErrors().add("Id do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(animal.get());
		service.apagar(animal.get());
		log.info("Deletando animal {}", animal);

		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Valida se o Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(Animal animal, BindingResult result) {
		this.service.findById(animal.getId())
				.ifPresent(ani -> result.addError(new ObjectError("Animal", animal.getNomePopular() + "já existe")));
	}
}