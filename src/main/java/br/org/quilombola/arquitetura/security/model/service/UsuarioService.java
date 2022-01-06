package br.org.quilombola.arquitetura.security.model.service;

import org.springframework.stereotype.Service;

import br.org.quilombola.arquitetura.GenericService;
import br.org.quilombola.arquitetura.security.model.entity.Usuario;
import br.org.quilombola.arquitetura.security.model.repository.UsuarioRepository;

@Service
public class UsuarioService extends GenericService<UsuarioRepository, Usuario>{

}
