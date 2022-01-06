package br.org.quilombola.configuracao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.security.auth.kerberos.ServicePermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.org.quilombola.arquitetura.security.model.entity.Permissao;
import br.org.quilombola.arquitetura.security.model.entity.Usuario;
import br.org.quilombola.arquitetura.security.model.repository.PermissaoRepository;
import br.org.quilombola.arquitetura.security.model.repository.UsuarioRepository;
import br.org.quilombola.arquitetura.security.model.service.PermissaoService;
import br.org.quilombola.enums.PermissoesSistemaEnum;

@Configuration
public class Configuracao {
	
	@Autowired
	private PermissaoService servicePermissao;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoderb;

	@PostConstruct
	public void configPermissoes() {
		List<PermissoesSistemaEnum> listPermissoes = new ArrayList<PermissoesSistemaEnum>();
		listPermissoes = Arrays.asList(PermissoesSistemaEnum.values());
		
		listPermissoes.forEach(p->{
			Permissao permissao = servicePermissao.findById(p.getId().longValue());
			if(permissao == null) {
				permissao = new Permissao();
				permissao.setCodigo(p.getId().longValue());
				permissao.setDescricao(p.name());
				try {
					servicePermissao.create(permissao);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		if(repository.findByUserName("admin") == null) {
			Usuario usuarioCreate = new Usuario();
			Usuario usuario = new Usuario();
			
			Permissao permissao = permissaoRepository.findByDescricao("ADMINISTRADOR");
			
			usuarioCreate.setNome("Administrador");
			usuarioCreate.setUserName("admin");
			usuarioCreate.setPermissoes(new ArrayList<Permissao>());			
			usuarioCreate.setSenha(passwordEncoderb.encode("123456")); 
			usuarioCreate.setEnabled(true);			
			usuario = repository.saveAndFlush(usuarioCreate);
			
			if(usuario.getPermissoes() == null)
				usuario.setPermissoes(new ArrayList<Permissao>());
			
			usuario.getPermissoes().add(permissao);
			repository.saveAndFlush(usuario);
		}
	}
}
