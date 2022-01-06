package br.org.quilombola.model.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.Municipio;
import br.org.quilombola.model.entity.MunicipioVW;

@RepositoryRestResource(collectionResourceRel = "municipiosvw" , path = "municipiosvw")
public interface MunicipioVWRepository extends IRepository<MunicipioVW, Long> {

	List<Municipio> findByEstado(String estado);
}
