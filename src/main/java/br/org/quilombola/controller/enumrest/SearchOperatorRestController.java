package br.org.quilombola.controller.enumrest;

import java.util.ArrayList;
import java.util.EnumSet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.criteria.OperatorEnum;

@RestController
@RequestMapping("/api/v1/searchoperators")
public class SearchOperatorRestController {

	@GetMapping("")
	public ResponseEntity<?> index() {

		return ResponseEntity.ok(new ArrayList<OperatorEnum>(EnumSet.allOf(OperatorEnum.class)));
	}
	
}
