package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.Territorio;

@RepositoryRestResource(collectionResourceRel = "territorios" , path = "territorios")
public interface TerritorioRepository extends IRepository<Territorio, Long> {

}
