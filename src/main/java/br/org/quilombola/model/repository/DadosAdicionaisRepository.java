package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.DadosAdicionaisComunidade;

@RepositoryRestResource(collectionResourceRel = "portarias" , path = "portarias")
public interface DadosAdicionaisRepository extends IRepository<DadosAdicionaisComunidade, Long>{

}
