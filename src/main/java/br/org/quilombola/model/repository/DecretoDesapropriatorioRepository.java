package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.DecretoDesapropriatorio;

@RepositoryRestResource(collectionResourceRel = "decretos" , path = "decretos")
public interface DecretoDesapropriatorioRepository extends IRepository<DecretoDesapropriatorio, Long>{

}
