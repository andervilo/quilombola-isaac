package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.Ccdru;

@Transactional
@RepositoryRestResource(collectionResourceRel = "ccdrus" , path = "ccdrus")
public interface CcdruRepository extends IRepository<Ccdru, Long> {

}
