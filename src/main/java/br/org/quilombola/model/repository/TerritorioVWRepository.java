package br.org.quilombola.model.repository;

import java.util.Optional;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.DadosTerritorioVw;

@RepositoryRestResource(collectionResourceRel = "dadosterritorios" , path = "dadosterritorios")
public interface TerritorioVWRepository extends IRepository<DadosTerritorioVw, Long>{

	Optional<DadosTerritorioVw> findByEstado(String estado);
}
