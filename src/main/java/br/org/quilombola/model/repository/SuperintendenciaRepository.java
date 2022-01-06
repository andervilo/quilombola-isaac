package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.Superintendencia;

@RepositoryRestResource(collectionResourceRel = "superintendencias" , path = "superintendencias")
public interface SuperintendenciaRepository extends IRepository<Superintendencia, Long> {

}
