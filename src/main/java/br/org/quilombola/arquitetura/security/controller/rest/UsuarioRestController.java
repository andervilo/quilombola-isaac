package br.org.quilombola.arquitetura.security.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.arquitetura.security.model.CustomUserDetails;
import br.org.quilombola.arquitetura.security.model.dto.UsuarioDTO;
import br.org.quilombola.arquitetura.security.model.dto.UsuarioPermissaoDTO;
import br.org.quilombola.arquitetura.security.model.entity.Permissao;
import br.org.quilombola.arquitetura.security.model.entity.Usuario;
import br.org.quilombola.arquitetura.security.model.repository.PermissaoRepository;
import br.org.quilombola.arquitetura.security.model.repository.UsuarioRepository;
import br.org.quilombola.arquitetura.security.model.service.UsuarioService;

@RestController
@PreAuthorize("hasAuthority('ADMINISTRADOR','MASTER')")
@RequestMapping("/api/v1/security/usuarios")
public class UsuarioRestController extends AbstractRestController<Usuario, UsuarioService> {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PreAuthorize("hasAuthority('ADMINISTRADOR','MASTER')")
	@PostMapping(value="/create", consumes = "application/json")
	public ResponseEntity<?> createUser(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("success", false);
		Boolean hasError = false;
		String errorMessage = "";
		
		if(!usuarioDTO.getSenha().equals(usuarioDTO.getConfirmacaoSenha())) {			
			hasError = true;
			errorMessage += "Senha e confirmação não conferem!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha e confirmação não conferem!");
		}
		if(usuarioRepository.findByEmail(usuarioDTO.getEmail()) != null) {
			hasError = true;
			errorMessage += "Email informado já está em uso!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email informado já está em uso!");
		}
		if(usuarioRepository.findByUserName(usuarioDTO.getUserName()) != null) {
			hasError = true;
			errorMessage += "Nome de usuário informado já está em uso!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome de usuário informado já está em uso!");
		}
		
		if(hasError) {
			map.put("message", errorMessage);
			return ResponseEntity.badRequest().body(map);
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setUserName(usuarioDTO.getUserName());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
		usuario.setEnabled(usuarioDTO.getEnabled());
		usuario.setPermissoes(new ArrayList<Permissao>());
		
		try {
			Usuario usuarioResponse = usuarioRepository.saveAndFlush(usuario);			
			if(usuarioResponse != null) {
				map.put("success", true);
				BeanUtils.setProperty(usuarioResponse, "senha", "");
				map.put("usuario", usuarioResponse );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(map);
	}
	
	@PreAuthorize("hasAuthority('ADMINISTRADOR','MASTER')")
	@PutMapping(value="/update", consumes = "application/json")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("success", false);
		Boolean hasError = false;
		String errorMessage = "";
		
		Usuario usuario = new Usuario();
		
		if(!usuarioRepository.existsById(usuarioDTO.getCodigo())) {
			hasError = true;
			errorMessage += "Usuário informado não encontrado!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário informado não encontrado!");
		}else {
			usuario = usuarioRepository.findById(usuarioDTO.getCodigo()).get();
			
			if(usuarioDTO.getEmail() != null ) {	
				if(usuario.getEmail() !=null && !usuario.getEmail().equalsIgnoreCase(usuarioDTO.getEmail())) {
					if(usuarioRepository.findByEmail(usuarioDTO.getEmail()) != null) {
						hasError = true;
						errorMessage += "Email informado já está em uso!<br>";
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email informado já está em uso!");
					}
				}
			}
			
			if(!usuario.getUserName().equalsIgnoreCase(usuarioDTO.getUserName())
				&& usuarioRepository.findByUserName(usuarioDTO.getUserName()) != null) {
				hasError = true;
				errorMessage += "Nome de usuário informado já está em uso!<br>";
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome de usuário informado já está em uso!");
			}
		}
		
		if(hasError) {
			map.put("message", errorMessage);
			return ResponseEntity.badRequest().body(map);
		}
		
		usuario.setNome(usuarioDTO.getNome());
		usuario.setUserName(usuarioDTO.getUserName());
		usuario.setEmail(usuarioDTO.getEmail());
		
		try {
			Usuario usuarioResponse = usuarioRepository.saveAndFlush(usuario);			
			if(usuarioResponse != null) {
				map.put("success", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(map);
	}
	
	@PreAuthorize("hasAuthority('ADMINISTRADOR','MASTER')")
	@PostMapping(value="/add-permissao", consumes = "application/json")
	public ResponseEntity<?> addUserPermission(@Valid @RequestBody UsuarioPermissaoDTO usuarioPermissaoDTO) {
		
		System.out.println(usuarioPermissaoDTO);
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("success", false);
		Boolean hasError = false;
		String errorMessage = "";
		
		if(usuarioPermissaoDTO.getPermissaoId() <= 0) {			
			hasError = true;
			errorMessage += "Permissão não informada!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Permissão não informada!");
		}else if(!permissaoRepository.existsById(usuarioPermissaoDTO.getPermissaoId())) {
			hasError = true;
			errorMessage += "Permissão não encontrada!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Permissão não encontrada!");
		}
			
		if(usuarioPermissaoDTO.getUsuarioId() <= 0) {			
			hasError = true;
			errorMessage += "Usuário não informado!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não informado!");
		}else if(!usuarioRepository.existsById(usuarioPermissaoDTO.getUsuarioId())) {
			hasError = true;
			errorMessage += "Usuário não encontrado!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!");
		}
		
		Permissao permissao = permissaoRepository.findById(usuarioPermissaoDTO.getPermissaoId()).get();
		Usuario usuario = usuarioRepository.findById(usuarioPermissaoDTO.getUsuarioId()).get();
		
		if(usuario.getPermissoes() != null && usuario.getPermissoes().contains(permissao)){
			hasError = true;
			errorMessage += "Usuário já possui esta permissão!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já possui esta permissão!");
		}
		
		if(hasError) {
			map.put("message", errorMessage);
			return ResponseEntity.badRequest().body(map);
		}
		
		if(usuario.getPermissoes() == null) {
			usuario.setPermissoes(new ArrayList<Permissao>());
		}
		
		try {
			usuario.getPermissoes().add(permissao);
			usuarioRepository.saveAndFlush(usuario);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		usuario.getPermissoes().add(permissao);
		
		return ResponseEntity.ok().body(map);
		
	}
	
	@PreAuthorize("hasAuthority('ADMINISTRADOR','MASTER')")
	@PostMapping(value="/remove-permissao", consumes = "application/json")
	public ResponseEntity<?> delUserPermission(@Valid @RequestBody UsuarioPermissaoDTO usuarioPermissaoDTO) {
		
		System.out.println(usuarioPermissaoDTO);
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("success", false);
		Boolean hasError = false;
		String errorMessage = "";
		
		if(usuarioPermissaoDTO.getPermissaoId() <= 0) {			
			hasError = true;
			errorMessage += "Permissão não informada!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Permissão não informada!");
		}else if(!permissaoRepository.existsById(usuarioPermissaoDTO.getPermissaoId())) {
			hasError = true;
			errorMessage += "Permissão não encontrada!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Permissão não encontrada!");
		}
			
		if(usuarioPermissaoDTO.getUsuarioId() <= 0) {			
			hasError = true;
			errorMessage += "Usuário não informado!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não informado!");
		}else if(!usuarioRepository.existsById(usuarioPermissaoDTO.getUsuarioId())) {
			hasError = true;
			errorMessage += "Usuário não encontrado!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!");
		}
		
		Permissao permissao = permissaoRepository.findById(usuarioPermissaoDTO.getPermissaoId()).get();
		Usuario usuario = usuarioRepository.findById(usuarioPermissaoDTO.getUsuarioId()).get();
		
		if(usuario.getPermissoes() != null && !usuario.getPermissoes().contains(permissao)){
			hasError = true;
			errorMessage += "Usuário não possui esta permissão!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não possui esta permissão!");
		}
		
		if(usuario.getPermissoes() == null) {
			hasError = true;
			errorMessage += "Usuário não possui esta permissão!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não possui esta permissão!");
		}
		
		if(hasError) {
			map.put("message", errorMessage);
			return ResponseEntity.badRequest().body(map);
		}
		
		
		
		try {
			usuario.getPermissoes().remove(permissao);
			usuarioRepository.saveAndFlush(usuario);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		usuario.getPermissoes().add(permissao);
		
		return ResponseEntity.ok().body(map);
		
	}
	
	@PreAuthorize("hasAuthority('ADMINISTRADOR','MASTER')")
	@PostMapping(value="/enable-disable", consumes = "application/json")
	public ResponseEntity<?> desableUser(@Valid @RequestBody UsuarioPermissaoDTO usuarioPermissaoDTO) {
		
		System.out.println(usuarioPermissaoDTO);
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("success", false);
		Boolean hasError = false;
		String errorMessage = "";
		
			
		if(usuarioPermissaoDTO.getUsuarioId() <= 0) {			
			hasError = true;
			errorMessage += "Usuário não informado!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não informado!");
		}else if(!usuarioRepository.existsById(usuarioPermissaoDTO.getUsuarioId())) {
			hasError = true;
			errorMessage += "Usuário não encontrado!<br>";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!");
		}
		
		Usuario usuario = usuarioRepository.findById(usuarioPermissaoDTO.getUsuarioId()).get();
		
		if(hasError) {
			map.put("message", errorMessage);
			return ResponseEntity.badRequest().body(map);
		}
		
		try {
			usuario.setEnabled(!usuario.getEnabled());
			usuarioRepository.saveAndFlush(usuario);
			map.put("success", true);
			map.put("situacao", usuario.getEnabled());
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return ResponseEntity.ok().body(map);
		
	}
	
	@PostMapping(value="/alterar-senha/{userId}", consumes = "application/json")
	public ResponseEntity<?> trocarSenha(@PathVariable Long userId, @RequestBody Map<String, String> payload, HttpSession session) {
		
		if(!usuarioRepository.existsById(userId)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não enconrado!");
		}
		
		String novaSenha = payload.get("novaSenha");
		String confirmacaoSenha = payload.get("confirmacaoSenha");
		
		if(!novaSenha.equals(confirmacaoSenha)){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nova senha e confirmação de senha não conferem!");
		}
		
		CustomUserDetails user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			user = (CustomUserDetails) principal;
		}
		
		if(user.getCodigo() != userId){
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não pode alterar a senha desse usuário!");
		}
		
		try {
			Usuario usuario = usuarioRepository.findById(userId).get();
			usuario.setSenha(passwordEncoder.encode(novaSenha));
			usuarioRepository.save(usuario);
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao realizar operação! "+e.getMessage());
		}
		
		Map<Object, Object> response = new HashMap<Object, Object>();
		response.put("message", "Senha alterada com sucesso!");
		return ResponseEntity.ok().body(response);
		
	}

}
