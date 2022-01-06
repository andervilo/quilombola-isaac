package br.org.quilombola.controller.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import br.org.quilombola.arquitetura.security.model.entity.Usuario;
import br.org.quilombola.model.entity.Certificado;
import br.org.quilombola.model.service.CertificadoService;

@Controller
@RequestMapping("/certificados")
public class CertificadoWebController {
	
	@Autowired
	private CertificadoService certificadoService;

	@GetMapping("")
	public String index(Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		model.addAttribute("namePage", "tiposdocumentos");
		return "certificados/list";
	}
	
	@GetMapping("/novo")
	public String create(Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		model.addAttribute("id", new Integer(0));
		return "certificados/create";
	}
	
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Long id, Model model, HttpServletRequest request, HttpSession session) {
		String baseUrl = String.format("%s://%s:%d%s/api/v1",request.getScheme(),  request.getServerName(), request.getServerPort(),request.getContextPath());
		session.setAttribute("BASE_URL_API", baseUrl);
		model.addAttribute("id", id);
		return "certificados/create";
	}
	
	@PostMapping(value="/{id}/documento", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<?> uploadDocumento(@PathVariable Long id, @Valid @RequestParam MultipartFile documento){
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		if(!documento.getContentType().equalsIgnoreCase(MediaType.APPLICATION_PDF_VALUE) &&  
			!documento.getContentType().equalsIgnoreCase("application/msword") &&
			!documento.getContentType().equalsIgnoreCase("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de arquivo inválido!<br>Apenas PDF, DOC e DOCX são permitidos!");
		}
		if(!certificadoService.getRepository().existsById(id)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado não encontrado!");
		}
		Certificado certificado = certificadoService.findById(id);
		
		try {
			certificado.setNomeDocumento(documento.getOriginalFilename());
			certificado.setExtensaoDocumento(documento.getContentType());
			certificado.setBinarioDocumento(documento.getBytes());
			certificadoService.update(certificado.getId(), certificado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("message", "Documento adicionado com sucesso!");
		return ResponseEntity.ok().body(map);
	}
	
	@DeleteMapping(value="/{id}/documento", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deleteDocumento(@PathVariable Long id){
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		if(!certificadoService.getRepository().existsById(id)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado não encontrado!");
		}
		Certificado certificado = certificadoService.findById(id);
		
		try {
			certificado.setNomeDocumento(null);
			certificado.setExtensaoDocumento(null);
			certificado.setBinarioDocumento(null);
			certificadoService.update(certificado.getId(), certificado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("message", "Documento adicionado com sucesso!");
		return ResponseEntity.ok().body(map);
	}
	
	@GetMapping(value="/{id}/documento")
	public ResponseEntity<?> getDocumento(@PathVariable Long id) throws IOException{
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		if(!certificadoService.getRepository().existsById(id)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado não encontrado!");
		}
		Certificado certificado = certificadoService.findById(id);

	    InputStream targetStream = new ByteArrayInputStream(certificado.getBinarioDocumento()) ;

		ByteArrayResource byteArrayResource = new ByteArrayResource(certificado.getBinarioDocumento());
		targetStream.close();
		System.out.println(MediaType.parseMediaType(certificado.getExtensaoDocumento()));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + certificado.getNomeDocumento() + "\"")
                .body(byteArrayResource);
		
	}

}
