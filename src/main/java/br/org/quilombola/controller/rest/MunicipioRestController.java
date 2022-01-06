package br.org.quilombola.controller.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.arquitetura.criteria.BodyFIlterAndSort;
import br.org.quilombola.arquitetura.criteria.DirectionEnum;
import br.org.quilombola.arquitetura.criteria.Filter;
import br.org.quilombola.arquitetura.criteria.Sorting;
import br.org.quilombola.model.dto.MunicipioDTO;
import br.org.quilombola.model.entity.Comunidade;
import br.org.quilombola.model.entity.Municipio;
import br.org.quilombola.model.entity.MunicipioVW;
import br.org.quilombola.model.repository.MunicipioVWRepository;
import br.org.quilombola.model.service.MunicipioService;

@RestController
@RequestMapping("/api/v1/municipios")
public class MunicipioRestController extends AbstractRestController<Municipio, MunicipioService>{

	@Autowired
	private MunicipioVWRepository municipioVW;
	
	@SuppressWarnings("unchecked")
	@PostMapping(value= {"/criteria"})
		public ResponseEntity<?> getByCriteria(@Nullable Pageable pageable, @Nullable @RequestBody BodyFIlterAndSort bodyFIlter) throws JsonParseException, JsonMappingException, IOException{

		Pageable pageable2 = null;
		if(bodyFIlter != null) {	
			if(bodyFIlter.getSize() == 0) {
				bodyFIlter.setSize(5);
			}
			
			if(bodyFIlter.getFilters() == null) {
				bodyFIlter.setFilters(new ArrayList<>());
			}
			
			pageable2 = PageRequest.of(bodyFIlter.getPage(), bodyFIlter.getSize(), Direction.valueOf(bodyFIlter.getSorting().getDirection().name()) , bodyFIlter.getSorting().getField());
			
			if(bodyFIlter.getSorting() == null ) {
				bodyFIlter.setSorting(new Sorting("id", DirectionEnum.ASC));
			}
			
			return ResponseEntity.ok().body(this.getService().findByCriteria(pageable2, bodyFIlter.getSorting(), bodyFIlter.getFilters()));
		}

		return ResponseEntity.ok().body(this.getService().findByCriteria(pageable, new Sorting("id", DirectionEnum.ASC), new ArrayList<Filter>()));
	}
	
	@GetMapping("/{id}/comunidades")
	public ResponseEntity<?> getComunidadesByMunicipiosId(@PathVariable Long id){
		Municipio municipio = this.getService().findById(id);
		List<Comunidade> comunidades = new ArrayList<>();
		
		municipio.getComunidades().forEach(comunidade -> {
			comunidade.setMunicipios(new ArrayList<Municipio>());
			comunidades.add(comunidade);
		});
		
		return ResponseEntity.ok().body(comunidades);
	}
	
	@GetMapping("/toComboList/{buscaMunicipio}")
	public ResponseEntity<?> toComboList(@PathVariable String buscaMunicipio){
			
		return ResponseEntity.ok().body(this.getService().getMunicipiosPorNome(buscaMunicipio));
	} 

}
