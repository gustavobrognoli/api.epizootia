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

import com.epizootia.dto.LocalidadeDTO;
import com.epizootia.entities.Localidade;
import com.epizootia.response.Response;
import com.epizootia.services.LocalidadeService;

@RestController
@RequestMapping("/api/localidade")
public class LocalidadeController {

	private static final Logger log = LoggerFactory.getLogger(LocalidadeController.class);

	@Autowired
	private LocalidadeService service;

	/**
	 * 
	 * Consulta todos os animais
	 * 
	 * @return List<LocalidadeDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<LocalidadeDTO>>> listaTodos() {
		Response<List<LocalidadeDTO>> response = new Response<List<LocalidadeDTO>>();

		List<LocalidadeDTO> localidadeDTOS = service.findAll().stream().map(this::converteEntityParaDTO).collect(Collectors.toList());

		if (localidadeDTOS.isEmpty()) {

			log.error("Não há animais cadastrados");
			response.getErrors().add("Não há animais cadastrados");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(localidadeDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de localidade por id
	 * 
	 * @return List<LocalidadeDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<LocalidadeDTO>> consulta(@PathVariable("id") int id) {

		Response<LocalidadeDTO> response = new Response<LocalidadeDTO>();
		Optional<Localidade> localidade = service.findById(id);

		if (!localidade.isPresent()) {

			log.error("Id do Localidade não cadastrado na base de dados");
			response.getErrors().add("Id do Localidade não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		LocalidadeDTO localidadeDTO = converteEntityParaDTO(localidade.get());

		response.setData(localidadeDTO);

		log.info("Consulta do localidade {}", localidadeDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo localidade na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Localidade
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<LocalidadeDTO>> cadastrar(@Valid @RequestBody LocalidadeDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando localidade {}", DTO.toString());

		Response<LocalidadeDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Localidade entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta localidade da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<LocalidadeDTO>> apagar(@PathVariable("id") int id) {

		Response<LocalidadeDTO> response = new Response<LocalidadeDTO>();
		Optional<Localidade> localidade = service.findById(id);

		if (!localidade.isPresent()) {
			log.error("Id do Localidade não cadastrado na base de dados");
			response.getErrors().add("Id do Localidade não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		LocalidadeDTO localidadeDTO = converteEntityParaDTO(localidade.get());

		response.setData(localidadeDTO);
		service.apagar(localidade.get());
		log.info("Deletando localidade {}", localidadeDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param localidadeDTO
	 * @return Entity
	 */
	public Localidade converteDTOParaEntity(LocalidadeDTO localidadeDTO) {
		Localidade localidade = new Localidade();
		
		localidade.setId(localidadeDTO.getId());
		
		MoradorController moradorController = new MoradorController();
		localidade.setMorador(moradorController.converteDTOParaEntity(localidadeDTO.getMorador()));	
		
		localidade.setCep(localidadeDTO.getCep());
		localidade.setBairro(localidadeDTO.getBairro());
		localidade.setLogradouro(localidadeDTO.getLogradouro());
		localidade.setPontoReferencia(localidadeDTO.getPontoReferencia());
		localidade.setLatitude(localidadeDTO.getLatitude());
		localidade.setLongitude(localidadeDTO.getLongitude());
		
		ImpactosController impactosController = new ImpactosController();
		localidade.setImpactos(impactosController.converteDTOParaEntity(localidadeDTO.getImpactos()));
		
		CaracteristicasController caracteristicasController = new CaracteristicasController();
		localidade.setCaracteristicas(caracteristicasController.converteDTOParaEntity(localidadeDTO.getCaracteristicas()));
		
		CorposAguaController corposAguaController = new CorposAguaController();
		localidade.setCorposAgua(corposAguaController.converteDTOParaEntity(localidadeDTO.getCorposAgua()));
		
		SituacaoFundiariaController situacaoFundiariaController = new SituacaoFundiariaController();
		localidade.setSituacaoFundiaria(situacaoFundiariaController.converteDTOParaEntity(localidadeDTO.getSituacaoFundiaria()));	
		
		RegistroEntomologicoController registroEntomologicoController = new RegistroEntomologicoController();
		localidade.setRegistroEntomologico(registroEntomologicoController.converteDTOParaEntity(localidadeDTO.getRegistroEntomologico()));
		
		localidade.setDescricao(localidadeDTO.getDescricao());
		
		return localidade;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param localidade
	 * @return DTO
	 */
	public LocalidadeDTO converteEntityParaDTO(Localidade localidade) {
		LocalidadeDTO localidadeDTO = new LocalidadeDTO();
		
		localidadeDTO.setId(localidade.getId());
		
		MoradorController moradorController = new MoradorController();
		localidadeDTO.setMorador(moradorController.converteEntityParaDTO(localidade.getMorador()));	
		
		localidadeDTO.setCep(localidade.getCep());
		localidadeDTO.setBairro(localidade.getBairro());
		localidadeDTO.setLogradouro(localidade.getLogradouro());
		localidadeDTO.setPontoReferencia(localidade.getPontoReferencia());
		localidadeDTO.setLatitude(localidade.getLatitude());
		localidadeDTO.setLongitude(localidade.getLongitude());
		
		ImpactosController impactosController = new ImpactosController();
		localidadeDTO.setImpactos(impactosController.converteEntityParaDTO(localidade.getImpactos()));
		
		CaracteristicasController caracteristicasController = new CaracteristicasController();
		localidadeDTO.setCaracteristicas(caracteristicasController.converteEntityParaDTO(localidade.getCaracteristicas()));
		
		CorposAguaController corposAguaController = new CorposAguaController();
		localidadeDTO.setCorposAgua(corposAguaController.converteEntityParaDTO(localidade.getCorposAgua()));
		
		SituacaoFundiariaController situacaoFundiariaController = new SituacaoFundiariaController();
		localidadeDTO.setSituacaoFundiaria(situacaoFundiariaController.converteEntityParaDTO(localidade.getSituacaoFundiaria()));	
		
		RegistroEntomologicoController registroEntomologicoController = new RegistroEntomologicoController();
		localidadeDTO.setRegistroEntomologico(registroEntomologicoController.converteEntityParaDTO(localidade.getRegistroEntomologico()));
		
		localidadeDTO.setDescricao(localidadeDTO.getDescricao());
		
		return localidadeDTO;
	}

	/**
	 * 
	 * Valida se o Localidade ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(LocalidadeDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(ani -> result.addError(new ObjectError("Localidade", dTO.getLogradouro() + "já existe")));
	}
}