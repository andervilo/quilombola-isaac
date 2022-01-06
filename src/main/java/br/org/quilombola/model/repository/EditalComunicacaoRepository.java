package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.EditalComunicacao;

@RepositoryRestResource(collectionResourceRel = "editais" , path = "editais")
public interface EditalComunicacaoRepository extends IRepository<EditalComunicacao, Long> {

}
