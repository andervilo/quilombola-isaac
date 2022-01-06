package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.TipoConcessaoTitulo;

@RepositoryRestResource(collectionResourceRel = "tiposconcessoes" , path = "tiposconcessoes")
public interface TipoConcessaoTituloRepository extends IRepository<TipoConcessaoTitulo, Long>{

}
