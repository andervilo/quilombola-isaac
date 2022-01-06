package br.org.quilombola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.criteria.OperatorEnum;

@RestController
@RequestMapping("/api/v1/helpers")
public class HelpersRest {
	
	@GetMapping("/filter/operators")
	public ResponseEntity<?> index(){	    
		return ResponseEntity.ok(OperatorEnum.values()) ;
	}
}
