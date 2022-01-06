package br.org.quilombola.controller.rest.hotsite;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.org.quilombola.arquitetura.security.model.entity.Permissao;
import br.org.quilombola.arquitetura.security.model.entity.Usuario;
import br.org.quilombola.arquitetura.security.model.repository.UsuarioRepository;
import br.org.quilombola.arquitetura.utils.EmailUtils;
import br.org.quilombola.arquitetura.utils.GeradorSenhasUtil;

@Service
public class HotSiteService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Boolean createSimpleUser(String nome, String email) {
		
		if(findUser(email) != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email informado já está em uso!");
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setUserName(email);
		usuario.setEmail(email);
		
		String senha = GeradorSenhasUtil.gerarSenhaAleatoria();
		
		usuario.setSenha(passwordEncoder.encode(senha));
		usuario.setEnabled(true);
		usuario.setPermissoes(new ArrayList<Permissao>());
		
		try {
			Usuario usuarioResponse = usuarioRepository.saveAndFlush(usuario);
			Boolean isEmailEnviado = EmailUtils.enviarEmailSimpleUser(email, nome, senha);
			
			if(!isEmailEnviado) {
				usuarioRepository.deleteById(usuarioResponse.getCodigo());
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao incluir usuário! Problema com E-mail!");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao incluir usuário! "+e.getMessage());
		}
		
		return true;
		
	}
	
	public Boolean resetPassword(String email) {
		Usuario usuario = findUser(email);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Usuario usuarioRollBack = null;
		try {
			usuarioRollBack = objectMapper.readValue(objectMapper.writeValueAsString(usuario), Usuario.class);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
		
		if(usuario == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!");
		}
		
		String senha = GeradorSenhasUtil.gerarSenhaAleatoria();
		
		usuario.setSenha(passwordEncoder.encode(senha));
		
		try {
			Usuario usuarioResponse = usuarioRepository.saveAndFlush(usuario);
			Boolean isEmailEnviado = EmailUtils.enviarEmailSimpleUserResetPass(usuario.getEmail(), usuario.getNome(), senha);
			
			if(!isEmailEnviado) {
				usuarioRepository.saveAndFlush(usuarioRollBack);
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao alterar senha do Usuário. Tente novamente!!");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao alterar senha do Usuário. Tente novamente!!"+e.getMessage());
		}
		
		return true;
	}
	
	private Usuario findUser(String email) {
		return usuarioRepository.findByEmail(email);
	}

}
