package br.org.quilombola.arquitetura.security.model.repository;

import org.springframework.transaction.annotation.Transactional;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.arquitetura.security.model.entity.Usuario;

@Transactional
public interface UsuarioRepository extends IRepository<Usuario, Long> {

	public Usuario findByUserName(String username);
	public Usuario findByEmail(String email);
}
