package br.org.quilombola.arquitetura.security;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.org.quilombola.arquitetura.security.model.entity.Permissao;
import br.org.quilombola.arquitetura.security.model.entity.Usuario;
import br.org.quilombola.arquitetura.security.model.repository.UsuarioRepository;

//@Component
public class GenerateUser {
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoderb;
	
//	@PostConstruct
//    public void doLog() {
//		
//		if(repository.findByUserName("admin") == null) {
//			Usuario usuarioCreate = new Usuario();
//			
//			Permissao permissao = new Permissao();
//			permissao.setDescricao("ADMINISTRADOR");
//			
//			usuarioCreate.setNome("Administrador");
//			usuarioCreate.setUserName("admin");
//			usuarioCreate.setPermissoes(new ArrayList<Permissao>());
//			usuarioCreate.getPermissoes().add(permissao);
//			usuarioCreate.setSenha(passwordEncoderb.encode("123456")); 
//			usuarioCreate.setEnabled(true);
//			
//			repository.save(usuarioCreate);
//		}
//		
//		if(repository.findByUserName("user") == null) {
//			Usuario usuarioCreate = new Usuario();
//			
//			Permissao permissao = new Permissao();
//			permissao.setDescricao("USUARIO");
//			Permissao permissao2 = new Permissao();
//			permissao2.setDescricao("VER_TERRITORIO");
//			
//			usuarioCreate.setNome("Usuário");
//			usuarioCreate.setUserName("user");
//			usuarioCreate.setPermissoes(new ArrayList<Permissao>());
//			usuarioCreate.getPermissoes().add(permissao);
//			usuarioCreate.getPermissoes().add(permissao2);
//			usuarioCreate.setSenha(passwordEncoderb.encode("123456")); 
//			usuarioCreate.setEnabled(true);
//			
//			repository.save(usuarioCreate);
//		}
//    }
}
