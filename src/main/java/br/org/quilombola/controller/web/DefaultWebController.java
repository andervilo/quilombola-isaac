package br.org.quilombola.controller.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.quilombola.arquitetura.security.model.CustomUserDetails;
import br.org.quilombola.model.service.ComunidadeService;
import br.org.quilombola.model.service.QuilomboService;
import br.org.quilombola.model.service.TerritorioService;

@Controller
@RequestMapping("/")
public class DefaultWebController {
	@Autowired
	private ComunidadeService comunidadeService;
	
	@Autowired
	private TerritorioService territorioService;
	
	@Autowired
	private QuilomboService quilomboService;

	@GetMapping("/dashboard")
	public String index(Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			CustomUserDetails user = (CustomUserDetails) principal;
			
//			if(user == null){
//				return "redirect:/login";
//			}
//			
//			if(user.getNome().equals(null)){
//				return "redirect:/login";
//			}
//			
//			System.out.println("Verificação de erro: "+user.getNome());
			
			try {
				if(!user.getNome().equals(null) || !user.getNome().equals("")) {
					session.setAttribute("NOME_USUARIO_LOGADO", user.getNome());				
					session.setAttribute("ID_USUARIO_LOGADO", user.getCodigo());				
					session.setAttribute("FOTO_USUARIO_LOGADO", user.getFoto());				
				}else {
					session.setAttribute("NOME_USUARIO_LOGADO", user.getUsername());
					session.setAttribute("ID_USUARIO_LOGADO", user.getCodigo());
					session.setAttribute("FOTO_USUARIO_LOGADO", user.getFoto());
				}
			}catch (Exception e) {
				return "redirect:/logout"; 
			}
		}
		return "home/index";
	}
	
	@GetMapping("/cadastrar-usuario")
	public String cadastrarUsuario() {
		return "registro";
	}
	
	@GetMapping("/reset-senha")
	public String resetSenha() {
		return "resetSenha";
	}
	
	@GetMapping("/api/v1/dashboard")
	@ResponseBody
	public ResponseEntity<?> getDashboard(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("numeroComunidades", this.getTotalComunidades());
		map.put("numeroTerritorios", this.getTotalTerritorios());
		map.put("numeroQuilombos"  , this.getTotalQuilombos());
		
		return ResponseEntity.ok(map);
	}
	
	@GetMapping({"/", "/hotsite"})
	public String getHotsite(Model model){
		return "hotsite/index.html";
	}
	
	@GetMapping({"/hotsite/json"})
	@ResponseBody
	public ResponseEntity<?> getHotsiteJson(){
		String json = "{\r\n" + 
				"    \"glossary\": {\r\n" + 
				"        \"title\": \"example glossary\",\r\n" + 
				"		\"GlossDiv\": {\r\n" + 
				"            \"title\": \"S\",\r\n" + 
				"			\"GlossList\": {\r\n" + 
				"                \"GlossEntry\": {\r\n" + 
				"                    \"ID\": \"SGML\",\r\n" + 
				"					\"SortAs\": \"SGML\",\r\n" + 
				"					\"GlossTerm\": \"Standard Generalized Markup Language\",\r\n" + 
				"					\"Acronym\": \"SGML\",\r\n" + 
				"					\"Abbrev\": \"ISO 8879:1986\",\r\n" + 
				"					\"GlossDef\": {\r\n" + 
				"                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\r\n" + 
				"						\"GlossSeeAlso\": [\"GML\", \"XML\"]\r\n" + 
				"                    },\r\n" + 
				"					\"GlossSee\": \"markup\"\r\n" + 
				"                }\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}";
		return ResponseEntity.ok(json);
	}
	
	private Integer getTotalComunidades() {
		return comunidadeService.findAll().size();
	}
	
	private Integer getTotalTerritorios() {
		return territorioService.findAll().size();
	}
	private Integer getTotalQuilombos() {
		return quilomboService.findAll().size();
	}
	

}
