package br.org.quilombola.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tiposconcessoes")
public class TIpoConcessaoWebController {

	@GetMapping("")
	public String index(Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		model.addAttribute("namePage", "tiposdocumentos");
		return "tipo_concessao/list";
	}
	
	@GetMapping("/novo")
	public String create(Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		model.addAttribute("id", new Integer(0));
		return "tipo_concessao/create";
	}
	
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Long id, Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		model.addAttribute("id", id);
		return "tipo_concessao/create";
	}
	

}
