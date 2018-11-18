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

import com.epizootia.entities.VidaLivre;
import com.epizootia.response.Response;
import com.epizootia.services.VidaLivreService;

@RestController
@RequestMapping("/api/vidaLivre")
public class VidaLivreController {

	private static final Logger log = LoggerFactory.getLogger(VidaLivreController.class);

	@Autowired
	private VidaLivreService service;

	/**
	 * 
	 * Consulta todas as Especies
	 * 
	 * @return List<VidaLivre>
	 */
	@GetMapping
	public ResponseEntity<Response<List<VidaLivre>>> listaTodos() {
		Response<List<VidaLivre>> response = new Response<List<VidaLivre>>();

		List<VidaLivre> vidasLivres = service.findAll();
		
		if (vidasLivres.isEmpty()) {

			log.error("Não há especies cadastradas");
			response.getErrors().add("Não há especies cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(vidasLivres);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta a VidaLivre por id
	 * 
	 * @return List<VidaLivre>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<VidaLivre>> consulta(@PathVariable("id") int id) {

		Response<VidaLivre> response = new Response<VidaLivre>();
		Optional<VidaLivre> vidaLivre = service.findById(id);

		if (!vidaLivre.isPresent()) {
			log.error("Id de Vida Livre do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Vida Livre do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(vidaLivre.get());

		log.info("Consulta de Vida Livre do Animal {}", vidaLivre);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Vida Livre do animal na base de dados
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<VidaLivre>> cadastrar(@Valid @RequestBody VidaLivre vidaLivre, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Especie do animal {}", vidaLivre.toString());

		Response<VidaLivre> response = new Response<>();
		validaSeExiste(vidaLivre, result);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(vidaLivre);
		response.setData(vidaLivre);
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Especie do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<VidaLivre>> apagar(@PathVariable("id") int id) {

		Response<VidaLivre> response = new Response<VidaLivre>();
		Optional<VidaLivre> vidaLivre = service.findById(id);

		if (!vidaLivre.isPresent()) {
			log.error("Id de Vida Livre do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Vida Livre do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(vidaLivre.get());
		service.apagar(vidaLivre.get());
		log.info("Deletando Vida Livre do Animal {}", vidaLivre);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Valida se a Vida Livre do Animal ja existe na base de dados
	 * 
	 * @param Especie
	 * @param result
	 */
	private void validaSeExiste(VidaLivre vidaLivre, BindingResult result) {
		this.service.findById(vidaLivre.getId()).
		ifPresent( esp -> result.addError(new ObjectError("Especie do Animal", vidaLivre.getVidaLivre() + "já existe")));
	}

}
