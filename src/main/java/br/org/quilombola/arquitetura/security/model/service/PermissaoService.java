package br.org.quilombola.arquitetura.security.model.service;

import org.springframework.stereotype.Service;

import br.org.quilombola.arquitetura.GenericService;
import br.org.quilombola.arquitetura.security.model.entity.Permissao;
import br.org.quilombola.arquitetura.security.model.repository.PermissaoRepository;

@Service
public class PermissaoService extends GenericService<PermissaoRepository, Permissao>{

}
