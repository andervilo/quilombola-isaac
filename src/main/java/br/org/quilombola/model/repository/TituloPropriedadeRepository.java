package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.TituloPropriedade;

@RepositoryRestResource(collectionResourceRel = "titulospropriedades" , path = "titulospropriedades")
public interface TituloPropriedadeRepository extends IRepository<TituloPropriedade, Long>{

}
