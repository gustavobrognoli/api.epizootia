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

import com.epizootia.dto.AnormalidadeDTO;
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
	public ResponseEntity<Response<List<AnormalidadeDTO>>> listaTodos() {
		Response<List<AnormalidadeDTO>> response = new Response<List<AnormalidadeDTO>>();

		List<AnormalidadeDTO> anormalidadeDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());
		
		if (anormalidadeDTOS.isEmpty()) {

			log.error("Não há Anormalidades cadastradas");
			response.getErrors().add("Não há Anormalidades cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(anormalidadeDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Anormalidades por id
	 * 
	 * @return List<EspecieDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<AnormalidadeDTO>> consulta(@PathVariable("id") int id) {

		Response<AnormalidadeDTO> response = new Response<AnormalidadeDTO>();
		Optional<Anormalidade> anormalidade = service.findById(id);

		if (!anormalidade.isPresent()) {
			log.error("Id de Anormalidade do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Anormalidade do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		AnormalidadeDTO anormalidadeDTO = converteEntityParaDTO(anormalidade.get());

		response.setData(anormalidadeDTO);

		log.info("Consulta de Anormalidade do Animal {}", anormalidadeDTO);

		return ResponseEntity.ok(response);
	}

	
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<Response<CaracteristicasDTO>> consulta(@PathVariable("id") int id) {
//				Response<CaracteristicasDTO> response = new Response<CaracteristicasDTO>();
//				Optional<ArrayList<anoraalidade>> anormalidadeLIsta = service.findByAllId(id);(Criar esse select ver autor)
//	}
	
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
	public ResponseEntity<Response<AnormalidadeDTO>> cadastrar(@Valid @RequestBody AnormalidadeDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Anormalidade do animal {}", DTO.toString());

		Response<AnormalidadeDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Anormalidade entity = this.converteDTOParaEntity(DTO);

		if (result.hasErrors()) {
			log.error("Erro ao validar informações: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.persistir(entity);
		response.setData(this.converteEntityParaDTO(entity));
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Deleta Anormalidade do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<AnormalidadeDTO>> apagar(@PathVariable("id") int id) {

		Response<AnormalidadeDTO> response = new Response<AnormalidadeDTO>();
		Optional<Anormalidade> anormalidade = service.findById(id);

		if (!anormalidade.isPresent()) {
			log.error("Id de Anormalidade do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Anormalidade do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		AnormalidadeDTO anormalidadeDTO = converteEntityParaDTO(anormalidade.get());

		response.setData(anormalidadeDTO);
		service.apagar(anormalidade.get());
		log.info("Deletando Anormalidade do Animal {}", anormalidadeDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param anormalidadeDTO
	 * @return Entity
	 */

	public Anormalidade converteDTOParaEntity(AnormalidadeDTO anormalidadeDTO) {
		Anormalidade anormalidade = new Anormalidade();
		anormalidade.setId(anormalidadeDTO.getId());
		anormalidade.setSintoma(anormalidadeDTO.getSintoma());
		return anormalidade;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param anormalidadeDTO
	 * @return DTO
	 */

	public AnormalidadeDTO converteEntityParaDTO(Anormalidade anormalidade) {
		AnormalidadeDTO anormalidadeDTO = new AnormalidadeDTO();
		anormalidadeDTO.setId(anormalidade.getId());
		anormalidadeDTO.setSintoma(anormalidade.getSintoma());
		return anormalidadeDTO;
	}

	/**
	 * 
	 * Valida se a Anormalidade do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(AnormalidadeDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId()).
		ifPresent( esp -> result.addError(new ObjectError("Anormalidade do Animal", dTO.getSintoma() + "já existe")));
	}

}
