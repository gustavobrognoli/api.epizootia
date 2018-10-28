package com.epizootia.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.epizootia.dto.AutorDTO;
import com.epizootia.entities.Autor;
import com.epizootia.response.Response;
import com.epizootia.services.AutorService;

@RestController
@RequestMapping("/api/autor")
public class AutorController {

	private static final Logger log = LoggerFactory.getLogger(AutorController.class);

	@Autowired
	private AutorService service;

	/**
	 * 
	 * Consulta todos os autores
	 * 
	 * @return List<AutorDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<AutorDTO>>> listaTodos() {
		Response<List<AutorDTO>> response = new Response<List<AutorDTO>>();

		List<AutorDTO> autorDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());

		response.setData(autorDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de autor atravez do código
	 * 
	 * @param codigo
	 * @return Autor ResponseEntity.ok
	 */
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Response<AutorDTO>> consulta(@PathVariable("codigo") String codigo) {

		Response<AutorDTO> response = new Response<AutorDTO>();
		Optional<Autor> autor = service.findByCodigo(codigo);

		if (!autor.isPresent()) {
			log.error("Código de autor não cadastrado na base de dados");
			response.getErrors().add("Código de autor não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		AutorDTO autorDTO = converteEntityParaDTO(autor.get());

		response.setData(autorDTO);

		log.info("Consulta do autor {}", autorDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo autor na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Autor
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<AutorDTO>> cadatrar(@Valid @RequestBody AutorDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando autor {}", DTO.toString());

		Response<AutorDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Autor entity = this.converteDTOParaEntity(DTO);

		if (result.hasErrors()) {
			log.error("Erro ao validar informalções: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(entity);
		response.setData(this.converteEntityParaDTO(entity));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<AutorDTO>> apagar(@PathVariable("id") String id) {

		Response<AutorDTO> response = new Response<AutorDTO>();
		Optional<Autor> autor = service.findById(id);

		if (!autor.isPresent()) {
			log.error("Código de autor não cadastrado na base de dados");
			response.getErrors().add("Código de autor não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		AutorDTO autorDTO = converteEntityParaDTO(autor.get());
		
		response.setData(autorDTO);
		service.apagar(autor.get());
		log.info("Apagando autor {}", autorDTO);

		return ResponseEntity.ok(response);
	}


	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param autorDTO
	 * @return Entity
	 */
	private Autor converteDTOParaEntity(AutorDTO autorDTO) {
		Autor autor = new Autor();
		autor.setId(autorDTO.getId());
		autor.setCodigo(autorDTO.getCodigo());
		autor.setNome(autorDTO.getNome());
		autor.setSobrenome(autorDTO.getSobrenome());
		return autor;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param autor
	 * @return DTO
	 */
	private AutorDTO converteEntityParaDTO(Autor autor) {
		AutorDTO autorDTO = new AutorDTO();
		autorDTO.setCodigo(autor.getCodigo());
		autorDTO.setNome(autor.getNome());
		autorDTO.setSobrenome(autor.getSobrenome());
		autorDTO.setId(autor.getId());
		return autorDTO;
	}

	/**
	 * 
	 * Valida se o Autor ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(AutorDTO dTO, BindingResult result) {
		this.service.findByCodigo(dTO.getCodigo())
				.ifPresent(aut -> result.addError(new ObjectError("autor", dTO.getNome() + " já existe")));

	}
}