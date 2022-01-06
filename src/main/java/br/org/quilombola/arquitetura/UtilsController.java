package br.org.quilombola.arquitetura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.org.quilombola.arquitetura.security.model.entity.Usuario;
import br.org.quilombola.arquitetura.security.model.repository.UsuarioRepository;



@Controller

public class UtilsController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/403")
	public String erro403(Model model) {
		
		return "403";
	}
	
	@GetMapping("/404")
	public String erro404(Model model) {
		
		return "404";
	}
	
	@GetMapping("/500")
	public String erro500(Model model) {
		
		return "500";
	}
	
}
