package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.Matricula;

@RepositoryRestResource(collectionResourceRel = "matriculas" , path = "matriculas")
public interface MatriculaRepository extends IRepository<Matricula, Long> {

}
