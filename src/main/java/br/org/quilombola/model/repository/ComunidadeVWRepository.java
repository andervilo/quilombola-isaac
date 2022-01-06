package br.org.quilombola.model.repository;

import java.util.Optional;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.DadosComunidadeVw;

@RepositoryRestResource(collectionResourceRel = "dadoscomunidades" , path = "dadoscomunidades")
public interface ComunidadeVWRepository extends IRepository<DadosComunidadeVw, Long>{

	Optional<DadosComunidadeVw> findByEstado(String estado);
}
