package br.org.quilombola.controller.rest.hotsite;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hot-site")
public class HotSiteRestController {
	
	@Autowired
	private HotSiteService service;
	
	@PostMapping("/create-user/{nome}/{email}")
	public ResponseEntity<?> createUser(@PathVariable("nome") Optional<String> nome, 
            @PathVariable("email") Optional<String> email){
		
		if(!nome.isPresent() && !email.isPresent()) {
			return ResponseEntity.badRequest().body("");
		}
		
		service.createSimpleUser(nome.get(), email.get());
		
		return ResponseEntity.ok("Usuário cadastrado com sucesso! Verifique a caixa de entrada do e-mail informado para prossegui!");
	}
	
	@PostMapping("/reset-pass/{email}")
	public ResponseEntity<?> resetPass(@PathVariable("email") Optional<String> email){
		
		if(!email.isPresent()) {
			return ResponseEntity.badRequest().body("");
		}
		
		service.resetPassword(email.get());
		
		return ResponseEntity.ok("Sua senha foi resetada. Verifique seu E-mail!");
	}

}
