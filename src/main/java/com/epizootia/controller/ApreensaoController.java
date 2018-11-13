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

import com.epizootia.dto.ApreensaoDTO;
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
	 * @return List<EspecieDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<ApreensaoDTO>>> listaTodos() {
		Response<List<ApreensaoDTO>> response = new Response<List<ApreensaoDTO>>();

		List<ApreensaoDTO> apreensaoDTOS = service.findAll().stream().map(this::converteEntityParaDTO)
				.collect(Collectors.toList());
		
		if (apreensaoDTOS.isEmpty()) {

			log.error("Não há Apreensões cadastradas");
			response.getErrors().add("Não há Apreensões cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(apreensaoDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta todas as Apreensões por id
	 * 
	 * @return List<ApreensaoDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<ApreensaoDTO>> consulta(@PathVariable("id") int id) {

		Response<ApreensaoDTO> response = new Response<ApreensaoDTO>();
		Optional<Apreensao> apreensao = service.findById(id);

		if (!apreensao.isPresent()) {
			log.error("Id de Apreensão do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Apreensão do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		ApreensaoDTO apreensaoDTO = converteEntityParaDTO(apreensao.get());

		response.setData(apreensaoDTO);

		log.info("Consulta de Apreensão do Animal {}", apreensaoDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra nova Apreensão do animal na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return apreensao
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<ApreensaoDTO>> cadastrar(@Valid @RequestBody ApreensaoDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Apreensão do animal {}", DTO.toString());

		Response<ApreensaoDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Apreensao entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta Especie do animal da base de dados
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<ApreensaoDTO>> apagar(@PathVariable("id") int id) {

		Response<ApreensaoDTO> response = new Response<ApreensaoDTO>();
		Optional<Apreensao> apreensao = service.findById(id);

		if (!apreensao.isPresent()) {
			log.error("Id de Apreensao do Animal não cadastrado na base de dados");
			response.getErrors().add("Id  de Apreensao do Animal não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		ApreensaoDTO apreensaoDTO = converteEntityParaDTO(apreensao.get());

		response.setData(apreensaoDTO);
		service.apagar(apreensao.get());
		log.info("Deletando Especie do Animal {}", apreensaoDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param especieDTO
	 * @return Entity
	 */

	public Apreensao converteDTOParaEntity(ApreensaoDTO apreensaoDTO) {
		Apreensao apreensao = new Apreensao();
		apreensao.setId(apreensaoDTO.getId());
		apreensao.setApreensao(apreensaoDTO.getApreensao());
		return apreensao;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param apreensaoDTO
	 * @return DTO
	 */

	public ApreensaoDTO converteEntityParaDTO(Apreensao apreensao) {
		ApreensaoDTO apreensaoDTO = new ApreensaoDTO();
		apreensaoDTO.setId(apreensao.getId());
		apreensaoDTO.setApreensao(apreensao.getApreensao());
		return apreensaoDTO;
	}

	/**
	 * 
	 * Valida se a Especie do Animal ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(ApreensaoDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId()).
		ifPresent( Apr -> result.addError(new ObjectError("Apreensão do Animal", dTO.getApreensao() + "já existe")));
	}

}
