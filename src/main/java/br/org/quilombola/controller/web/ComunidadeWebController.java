package br.org.quilombola.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.quilombola.arquitetura.enums.Estado;

@Controller
@RequestMapping("/comunidades")
public class ComunidadeWebController {

	@GetMapping("")
	public String index(Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		model.addAttribute("namePage", "tiposdocumentos");
		System.out.println(Estado.getEstadoByCodigoIbge(15));
		return "comunidades/list";
	}
	
	@GetMapping("/novo")
	public String create(Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		model.addAttribute("id", new Integer(0));
		return "comunidades/create";
	}
	
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Long id, Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		model.addAttribute("id", id);
		return "comunidades/create";
	}
	
	@GetMapping("/exportar")
	public String exportar(Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		return "comunidades/export";
	}
}
