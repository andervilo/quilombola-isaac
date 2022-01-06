package br.org.quilombola.controller.enumrest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.enums.Regiao;

@RestController
@RequestMapping("/api/v1/regioes")
public class RegiaoRestController {

	@GetMapping("")
	public ResponseEntity<?> index() {
		return ResponseEntity.ok(Regiao.values());
	}
	
}
