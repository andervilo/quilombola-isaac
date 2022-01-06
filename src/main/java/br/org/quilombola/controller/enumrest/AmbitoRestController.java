package br.org.quilombola.controller.enumrest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.enums.AmbitoProcessoEnum;

@RestController
@RequestMapping("/api/v1/ambito-processo")
public class AmbitoRestController{

	@GetMapping("")
	public ResponseEntity<?> index(){	    
		return ResponseEntity.ok(AmbitoProcessoEnum.values()) ;
	}
}
