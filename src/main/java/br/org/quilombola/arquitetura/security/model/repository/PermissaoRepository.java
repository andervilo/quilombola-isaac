package br.org.quilombola.arquitetura.security.model.repository;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.arquitetura.security.model.entity.Permissao;

public interface PermissaoRepository extends IRepository<Permissao, Long> {
	public Permissao findByDescricao(String descricao);
}
