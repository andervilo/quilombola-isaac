package br.org.quilombola.model.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.Municipio;

@RepositoryRestResource(collectionResourceRel = "municipios" , path = "municipios")
public interface MunicipioRepository extends IRepository<Municipio, Long> {

	List<Municipio> findByEstado(String estado);
}
