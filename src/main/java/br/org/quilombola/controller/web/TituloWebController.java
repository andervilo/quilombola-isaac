package br.org.quilombola.controller.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import br.org.quilombola.model.entity.TituloPropriedade;
import br.org.quilombola.model.service.TituloPropriedadeService;

@Controller
@RequestMapping("/titulos")
public class TituloWebController {
	
	@Autowired
	private TituloPropriedadeService service;
	
	private TituloPropriedade objeto;
	
	@PostMapping(value="/{id}/documento", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<?> uploadDocumento(@PathVariable Long id, @Valid @RequestParam MultipartFile documento){
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		if(!documento.getContentType().equalsIgnoreCase(MediaType.APPLICATION_PDF_VALUE) &&  
			!documento.getContentType().equalsIgnoreCase("application/msword") &&
			!documento.getContentType().equalsIgnoreCase("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de arquivo inválido!<br>Apenas PDF, DOC e DOCX são permitidos!");
		}
		if(!service.getRepository().existsById(id)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado não encontrado!");
		}
		objeto = service.findById(id);
		
		try {
			objeto.setNomeDocumento(documento.getOriginalFilename());
			objeto.setExtensaoDocumento(documento.getContentType());
			objeto.setBinarioDocumento(documento.getBytes());
			service.update(objeto.getId(), objeto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("message", "Documento adicionado com sucesso!");
		return ResponseEntity.ok().body(map);
	}
	
	@DeleteMapping(value="/{id}/documento", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deleteDocumento(@PathVariable Long id){
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		if(!service.getRepository().existsById(id)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado não encontrado!");
		}
		objeto = service.findById(id);
		
		try {
			objeto.setNomeDocumento(null);
			objeto.setExtensaoDocumento(null);
			objeto.setBinarioDocumento(null);
			service.update(objeto.getId(), objeto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("message", "Documento adicionado com sucesso!");
		return ResponseEntity.ok().body(map);
	}
	
	@GetMapping(value="/{id}/documento")
	public ResponseEntity<?> getDocumento(@PathVariable Long id) throws IOException{
		if(!service.getRepository().existsById(id)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado não encontrado!");
		}
		objeto = service.findById(id);

	    InputStream targetStream = new ByteArrayInputStream(objeto.getBinarioDocumento()) ;

		ByteArrayResource byteArrayResource = new ByteArrayResource(objeto.getBinarioDocumento());
		targetStream.close();
		System.out.println(MediaType.parseMediaType(objeto.getExtensaoDocumento()));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + objeto.getNomeDocumento() + "\"")
                .body(byteArrayResource);
		
	}

}
