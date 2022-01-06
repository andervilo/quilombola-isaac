package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.Orgao;

@RepositoryRestResource(collectionResourceRel = "orgaos" , path = "orgaos")
public interface OrgaoRepository extends IRepository<Orgao, Long> {

}
