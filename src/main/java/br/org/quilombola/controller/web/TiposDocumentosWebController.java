package br.org.quilombola.controller.web;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.quilombola.model.service.TipoDocumentoService;

@Controller
@RequestMapping("/tiposdocumentos")
public class TiposDocumentosWebController {
	
	@Autowired
	private TipoDocumentoService tpdService;

	@GetMapping("")
	public String documentoIndex(Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		model.addAttribute("namePage", "tiposdocumentos");
		return "tiposdocumentos/list";
	}
	
	@GetMapping("/novo")
	public String documentoCreate(HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		return "tiposdocumentos/create";
	}
	
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Long id, Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		model.addAttribute("id", id);
		return "tiposdocumentos/create";
	}
}
