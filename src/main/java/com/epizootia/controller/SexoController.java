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

import com.epizootia.entities.Sexo;
import com.epizootia.response.Response;
import com.epizootia.services.SexoService;

@RestController
@RequestMapping("/api/sexo")
public class SexoController {

	private static final Logger log = LoggerFactory.getLogger(SexoController.class);

	@Autowired
	private SexoService service;

	/**
	 * 
	 * Consulta todas os Sexos
	 * 
	 * @return List<Sexo>
	 */
	@GetMapping
	public ResponseEntity<Response<List<Sexo>>> listaTodos() {
		Response<List<Sexo>> response = new Response<List<Sexo>>();

		List<Sexo> sexos = service.findAll();

		if (sexos.isEmpty()) {

			log.error("Não há sexos cadastrados");
			response.getErrors().add("Não há sexos cadastrados");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(sexos);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas os Sexos por id
	 * 
	 * @return List<SexoDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Sexo>> consulta(@PathVariable("id") int id) {

		Response<Sexo> response = new Response<Sexo>();
		Optional<Sexo> sexo = service.findById(id);

		if (!sexo.isPresent()) {
			log.error("Id de Sexo do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Sexo do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(sexo.get());

		log.info("Consulta de Sexo do Animal {}", sexo);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo Sexo do animal na base de dados
	 * 
	 * @param result
	 * @return Sexo
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Sexo>> cadastrar(@Valid @RequestBody Sexo sexo, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Sexo do animal {}", sexo.toString());

		Response<Sexo> response = new Response<>();
		validaSeExiste(sexo, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(sexo);
		response.setData(sexo);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Sexo do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Sexo>> apagar(@PathVariable("id") int id) {

		Response<Sexo> response = new Response<Sexo>();
		Optional<Sexo> sexo = service.findById(id);

		if (!sexo.isPresent()) {
			log.error("Id de Sexo do Animal não cadastrado na base de dados");
			response.getErrors().add("Id de Sexo do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(sexo.get());
		service.apagar(sexo.get());
		log.info("Deletando Sexo do Animal {}", sexo);

		return ResponseEntity.ok(response);
	}
	/**
	 * 
	 * Valida se o Sexo do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(Sexo sexo, BindingResult result) {
		this.service.findById(sexo.getId())
				.ifPresent(sex -> result.addError(new ObjectError("Sexo do Animal", sexo.getSexo() + "já existe")));
	}

}
