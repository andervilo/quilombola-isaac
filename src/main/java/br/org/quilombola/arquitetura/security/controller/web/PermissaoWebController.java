package br.org.quilombola.arquitetura.security.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permissoes")
public class PermissaoWebController {

	@GetMapping("")
	public String index(Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		
		model.addAttribute("namePage", "tiposdocumentos");
		return "permissoes/list";
	}
	
}
