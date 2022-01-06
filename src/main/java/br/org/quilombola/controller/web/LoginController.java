package br.org.quilombola.controller.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import br.org.quilombola.arquitetura.security.model.CustomUserDetails;
import br.org.quilombola.arquitetura.security.model.entity.Usuario;
import br.org.quilombola.arquitetura.security.model.service.UsuarioService;
import br.org.quilombola.arquitetura.upload.Disco;

@Controller
@RequestMapping("")
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private Disco disco;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	
	@GetMapping("/alterar-foto/{userId}")
	public String alterarFoto(@PathVariable Long userId, Model model) {
		model.addAttribute("id", userId);
		
		CustomUserDetails user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			user = (CustomUserDetails) principal;
		}
		
		if(user.getCodigo() != userId){
			return "redirect:/403";
		}
		
		return "usuarios/uploadFoto";
	}
	
	@GetMapping("/alterar-senha/{userId}")
	public String alterarSenha(@PathVariable Long userId, Model model) {
		model.addAttribute("id", userId);
		
		CustomUserDetails user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			user = (CustomUserDetails) principal;
		}
		
		if(user.getCodigo() != userId){
			return "redirect:/403";
		}
		
		return "usuarios/trocaSenha";
	}
	
	@PostMapping(value="/api/v1/alterar-foto/{userId}", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<?> alterarFoto(@PathVariable Long userId, @Valid @RequestParam MultipartFile foto){
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("success", false);
		Boolean hasError = false;
		String errorMessage = "";
		
		if(!foto.getContentType().equalsIgnoreCase(MediaType.IMAGE_GIF_VALUE) &&  
			!foto.getContentType().equalsIgnoreCase(MediaType.IMAGE_JPEG_VALUE) &&
			!foto.getContentType().equalsIgnoreCase(MediaType.IMAGE_PNG_VALUE)) {
			hasError=true;
			errorMessage += "Formato de arquivo inválido!<br>Apenas JPG, GIF e PNG são permitidos!";
		}
		
		if(hasError) {
			map.put("message", errorMessage);
			return ResponseEntity.badRequest().body(map);
		}
		
		Usuario usuario = usuarioService.findById(userId);
		try {
//			disco.salvarFotoUsuario(foto, userId);
			usuario.setDataFoto(foto.getBytes());
			usuario.setFileType(foto.getContentType());
			usuario.setFoto(foto.getOriginalFilename());
			usuarioService.update(userId, usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("success", true);
		return ResponseEntity.ok().body(map);
	}
	
//	@GetMapping("/api/v1/user-foto/{userId}")
//	public @ResponseBody byte[] getImage(@PathVariable Long userId) throws IOException {
//		File pathToFile = new File("./uploads/sigequi/img/users/"+userId+".jpg");
//	    InputStream in = new FileInputStream(pathToFile);
//	    return StreamUtils.copyToByteArray(in);
//	}
	
	@GetMapping("/api/v1/user-foto/{userId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long userId) throws IOException {
        // Load file from database
        Usuario usuario = usuarioService.findById(userId);
        if(usuario.getDataFoto() == null){
			InputStream targetStream = new ClassPathResource("static/layout/img/user-avatar.png").getInputStream();;

			ByteArrayResource byteArrayResource = new ByteArrayResource(StreamUtils.copyToByteArray(targetStream));
			targetStream.close();

			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + userId+".jpg" + "\"")
					.body(byteArrayResource);
		}

	    InputStream targetStream = new ByteArrayInputStream(usuario.getDataFoto()) ;

		ByteArrayResource byteArrayResource = new ByteArrayResource(usuario.getDataFoto());
		targetStream.close();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + usuario.getFoto() + "\"")
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + userId+".jpg" + "\"")
                .body(byteArrayResource);
    }
	
//	@GetMapping("/api/v1/user-foto/{userId}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable Long userId) throws IOException {
//        // Load file from database
//        Usuario usuario = usuarioService.findById(userId);
//        String fPath = "/uploads/sigequi/img/users/"+userId+".jpg";
//        Path path = Paths.get(fPath);
//        if(!Files.exists(path)){
////			File resource = new ClassPathResource("classpath:static/layout/img/user-avatar.png").getFile();
////			InputStream targetStream = new FileInputStream(resource);
//			InputStream targetStream = new ClassPathResource("static/layout/img/user-avatar.png").getInputStream();;
//
//			ByteArrayResource byteArrayResource = new ByteArrayResource(StreamUtils.copyToByteArray(targetStream));
//			targetStream.close();
//
//			return ResponseEntity.ok()
//					.contentType(MediaType.IMAGE_JPEG)
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + userId+".jpg" + "\"")
//					.body(byteArrayResource);
//		}
//
//		File pathToFile = new File(fPath);
//	    InputStream targetStream = new FileInputStream(pathToFile);
//
//		ByteArrayResource byteArrayResource = new ByteArrayResource(StreamUtils.copyToByteArray(targetStream));
//		targetStream.close();
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_JPEG)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + userId+".jpg" + "\"")
//                .body(byteArrayResource);
//    }
}
