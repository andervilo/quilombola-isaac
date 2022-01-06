package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.AssentamentoQuilombola;

@RepositoryRestResource(collectionResourceRel = "assentamentos" , path = "assentamentos")
public interface AssentamentoQuilombolaRepository extends IRepository<AssentamentoQuilombola, Long>{

}
