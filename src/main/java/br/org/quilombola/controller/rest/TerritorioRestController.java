package br.org.quilombola.controller.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.arquitetura.criteria.BodyFIlterAndSort;
import br.org.quilombola.arquitetura.criteria.DirectionEnum;
import br.org.quilombola.arquitetura.criteria.Filter;
import br.org.quilombola.arquitetura.criteria.Sorting;
import br.org.quilombola.model.entity.Municipio;
import br.org.quilombola.model.entity.Territorio;
import br.org.quilombola.model.service.MunicipioService;
import br.org.quilombola.model.service.TerritorioService;

@RestController
@RequestMapping("/api/v1/territorios")
public class TerritorioRestController extends AbstractRestController<Territorio, TerritorioService> {
	@Autowired
	private MunicipioService municipioService;

	@Autowired
	private TerritorioService territorioService;
	
	@PostMapping(value={"/search/{ufCod}"})
	public ResponseEntity<?> getTerritorioByUfAndSearch_hotsite(@Nullable @PathVariable Integer ufCod, @Nullable @RequestBody Map<String, String> payload){
		List<Territorio> territorios = new ArrayList<Territorio>();
		String textoBusca = null;		
		
		if(ufCod != null) {
			if(payload != null && payload.containsKey("busca")) {				
				if(!StringUtils.isEmpty(payload.containsKey("busca"))) {
					textoBusca = payload.get("busca");
					territorios = territorioService.getTerritoriosByUfCodeAndSearchText(ufCod.intValue(), textoBusca);
				}			
			}else{
				territorios = territorioService.getTerritoriosByUfCodeAndSearchText(ufCod.intValue(), null);
			}
		}else if(ufCod == null && payload != null && payload.containsKey("busca") && !StringUtils.isEmpty(payload.containsKey("busca"))){
			textoBusca = payload.get("busca");
			territorios = territorioService.getTerritoriosByNome(textoBusca);
		}		
		
		return ResponseEntity.ok().body(territorios);
	}

	@PostMapping(value={"/search"})
	public ResponseEntity<?> getTerritorioBySearch_hotsite(@Nullable @RequestBody Map<String, String> payload){
		List<Territorio> territorios = new ArrayList<Territorio>();
		String textoBusca = null;

		if(payload != null && payload.containsKey("busca") && !StringUtils.isEmpty(payload.containsKey("busca"))){
			textoBusca = payload.get("busca");
			territorios = territorioService.getTerritoriosByNome(textoBusca);
		}

		return ResponseEntity.ok().body(territorios);
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/criteria" })
	public ResponseEntity<?> getByCriteria(@Nullable Pageable pageable,
			@Nullable @RequestBody BodyFIlterAndSort bodyFIlter)
			throws JsonParseException, JsonMappingException, IOException {

		Pageable pageable2 = null;
		if (bodyFIlter != null) {
			if (bodyFIlter.getSize() == 0) {
				bodyFIlter.setSize(5);
			}

			if (bodyFIlter.getFilters() == null) {
				bodyFIlter.setFilters(new ArrayList<>());
			}

			pageable2 = PageRequest.of(bodyFIlter.getPage(), bodyFIlter.getSize(),
					Direction.valueOf(bodyFIlter.getSorting().getDirection().name()),
					bodyFIlter.getSorting().getField());

			if (bodyFIlter.getSorting() == null) {
				bodyFIlter.setSorting(new Sorting("id", DirectionEnum.ASC));
			}

			return ResponseEntity.ok().body(
					this.getService().findByCriteria(pageable2, bodyFIlter.getSorting(), bodyFIlter.getFilters()));
		}

		return ResponseEntity.ok().body(this.getService().findByCriteria(pageable, new Sorting("id", DirectionEnum.ASC),
				new ArrayList<Filter>()));
	}

	// TODO desvincular-territorio
	@PutMapping("/desvincular-territorio")
	public ResponseEntity<?> desvincularTerritorio(@RequestBody Map<String, Long> payload) {
		Municipio municipio = municipioService.findById(payload.get("municipioId"));
		Territorio territorio = territorioService.findById(payload.get("territorioId"));

		if (municipio == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Município não encontrado!");
		}
		if (territorio == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Território não encontrado!");
		}
		System.out.println(municipio);
		if (!territorio.getMunicipioList().contains(municipio)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Município não está vinculado ao Território informado!!");
		}

		municipio.removeTerritorio(territorio);
		municipioService.getRepository().save(municipio);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Município desvinculado com sucesso!");
		return ResponseEntity.ok().body(response);
	}

	@PutMapping("/vincular-territorio")
	public ResponseEntity<?> vincularTerritorio(@RequestBody Map<String, Long> payload) {
		Municipio municipio = municipioService.findById(payload.get("municipioId"));
		Territorio territorio = territorioService.findById(payload.get("territorioId"));

		if (municipio == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Município não encontrado!");
		}
		if (territorio == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Território não encontrado!");
		}

		if (territorio.getMunicipioList().contains(municipio)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Município já está vinculado ao Território informado!!");
		}

		municipio.addTerritorio(territorio);
		municipioService.getRepository().save(municipio);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Município vinculado com sucesso!");
		return ResponseEntity.ok().body(response);
	}

}
