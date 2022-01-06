package br.org.quilombola.arquitetura.security.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.org.quilombola.arquitetura.security.model.entity.Usuario;
import br.org.quilombola.arquitetura.security.model.repository.UsuarioRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = userRepository.findByUserName(username);
		
		if(usuario == null){
			throw new UsernameNotFoundException("Usuário "+username.toUpperCase()+" não encontrado!");
		}else{
			return new CustomUserDetails(usuario);
		}
	}
		
}
