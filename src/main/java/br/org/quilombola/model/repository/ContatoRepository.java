package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.Contato;

@RepositoryRestResource(collectionResourceRel = "contatos" , path = "contatos")
public interface ContatoRepository extends IRepository<Contato, Long> {

}
