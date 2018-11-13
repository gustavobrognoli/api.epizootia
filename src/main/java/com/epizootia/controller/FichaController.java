package com.epizootia.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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

import com.epizootia.dto.AnimalDTO;
import com.epizootia.dto.ClassificacaoFADTO;
import com.epizootia.dto.FichaDTO;
import com.epizootia.dto.LocalidadeDTO;
import com.epizootia.entities.Animal;
import com.epizootia.entities.Ficha;
import com.epizootia.response.Response;
import com.epizootia.services.FichaService;

@RestController
@RequestMapping("/api/ficha")
public class FichaController {

	private static final Logger log = LoggerFactory.getLogger(FichaController.class);

	@Autowired
	private FichaService service;

	/**
	 * 
	 * Consulta todos os fichas
	 * 
	 * @return List<FichaDTO>
	 */
	@GetMapping
	public ResponseEntity<Response<List<FichaDTO>>> listaTodos() {
		Response<List<FichaDTO>> response = new Response<List<FichaDTO>>();

		List<FichaDTO> fichaDTOS = service.findAll().stream().map(this::converteEntityParaDTO).collect(Collectors.toList());

		if (fichaDTOS.isEmpty()) {

			log.error("Não há fichas cadastradas");
			response.getErrors().add("Não há fichas cadastradas");

			return ResponseEntity.badRequest().body(response);
		}
		response.setData(fichaDTOS);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Consulta de ficha por id
	 * 
	 * @return List<FichaDTO>
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<FichaDTO>> consulta(@PathVariable("id") int id) {

		Response<FichaDTO> response = new Response<FichaDTO>();
		Optional<Ficha> ficha = service.findById(id);

		if (!ficha.isPresent()) {

			log.error("Id da Ficha não cadastrado na base de dados");
			response.getErrors().add("Id da Ficha não cadastrado na base de dados");

			return ResponseEntity.badRequest().body(response);
		}

		FichaDTO fichaDTO = converteEntityParaDTO(ficha.get());

		response.setData(fichaDTO);

		log.info("Consulta do ficha {}", fichaDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Cadastra novo ficha na base de dados
	 * 
	 * @param DTO
	 * @param result
	 * @return Ficha
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<FichaDTO>> cadastrar(@Valid @RequestBody FichaDTO DTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando ficha {}", DTO.toString());

		Response<FichaDTO> response = new Response<>();
		validaSeExiste(DTO, result);
		Ficha entity = this.converteDTOParaEntity(DTO);

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
	 * Deleta ficha da base de dados
	 * 
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<FichaDTO>> apagar(@PathVariable("id") int id) {

		Response<FichaDTO> response = new Response<FichaDTO>();
		Optional<Ficha> ficha = service.findById(id);

		if (!ficha.isPresent()) {
			log.error("Id da Ficha não cadastrado na base de dados");
			response.getErrors().add("Id da Ficha não cadastrado na base de dados");
			return ResponseEntity.badRequest().body(response);
		}

		FichaDTO fichaDTO = converteEntityParaDTO(ficha.get());

		response.setData(fichaDTO);
		service.apagar(ficha.get());
		log.info("Deletando ficha {}", fichaDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Converte DTO para Entity
	 * 
	 * @param fichaDTO
	 * @return Entity
	 */
	private Ficha converteDTOParaEntity(FichaDTO fichaDTO) {
		Ficha ficha = new Ficha();
		ficha.setId(fichaDTO.getId());
		ficha.setDataOcorrencia(fichaDTO.getDataOcorrencia());
		
		
/*		AnimalController animalController = new AnimalController();
		if (fichaDTO.getAnimais() == null) {
			ficha.setAnimal(animalController.converteDTOParaEntity(new ArrayList<AnimaisDTO>()));	
		} else {
			ficha.setAnimal(animalController.converteDTOParaEntity(fichaDTO.getAnimais()));	
		}
*/

		ficha.setQuantidade(fichaDTO.getQuantidade());;

		LocalidadeController localidadeController = new LocalidadeController();
		if (fichaDTO.getLocalidade() == null) {
			ficha.setLocalidade(localidadeController.converteDTOParaEntity(new LocalidadeDTO()));			
		} else {
			ficha.setLocalidade(localidadeController.converteDTOParaEntity(fichaDTO.getLocalidade()));
		}
		
		ficha.setMunicipio(fichaDTO.getMunicipio());
		
		ClassificacaoFAController classificacaoFAController = new ClassificacaoFAController();
		if (fichaDTO.getClassificacaoFA() == null) {
			ficha.setClassificacaoFA(classificacaoFAController.converteDTOParaEntity(new ClassificacaoFADTO()));
		} else {
			ficha.setClassificacaoFA(classificacaoFAController.converteDTOParaEntity(fichaDTO.getClassificacaoFA()));
		}	
		
		return ficha;
	}

	/**
	 * 
	 * Converte Entity em DTO
	 * 
	 * @param ficha
	 * @return DTO
	 */
	private FichaDTO converteEntityParaDTO(Ficha ficha) {
		FichaDTO fichaDTO = new FichaDTO();
		fichaDTO.setId(ficha.getId());
		fichaDTO.setDataOcorrencia(ficha.getDataOcorrencia());

/*		
		AnimalController animalController = new AnimalController();
		fichaDTO.setAnimal(animalController.converteEntityParaDTO(ficha.getAnimais()));	

*/		
		fichaDTO.setQuantidade(ficha.getQuantidade());;

		LocalidadeController localidadeController = new LocalidadeController();
		fichaDTO.setLocalidade(localidadeController.converteEntityParaDTO(ficha.getLocalidade()));

		fichaDTO.setMunicipio(ficha.getMunicipio());
		
		return fichaDTO;
	}

	private ArrayList<Animal> converteDTOListParaEntidadeList(ArrayList<AnimalDTO> animaisDTO) {
		ArrayList<Animal> animais = new ArrayList<>();
		
		for(int i = 0; i< animaisDTO.size(); i++) {
			Animal animal = new Animal();
			
			animal.setId(animaisDTO.get(i).getId());

			
			
			animais.add(animal);
		}
		
		return animais;
	}
	
	/**
	 * 
	 * Valida se o Ficha ja existe na base de dados
	 * 
	 * @param DTO
	 * @param result
	 */
	private void validaSeExiste(FichaDTO dTO, BindingResult result) {
		this.service.findById(dTO.getId())
				.ifPresent(ani -> result.addError(new ObjectError("Ficha", dTO.getId() + "já existe")));
	}
}