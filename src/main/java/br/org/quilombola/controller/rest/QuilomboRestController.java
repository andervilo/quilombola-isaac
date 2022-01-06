package br.org.quilombola.controller.rest;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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
import br.org.quilombola.model.entity.Quilombo;
import br.org.quilombola.model.service.QuilomboService;

@RestController
@RequestMapping("/api/v1/quilombos")
public class QuilomboRestController extends AbstractRestController<Quilombo, QuilomboService> {
	
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
}