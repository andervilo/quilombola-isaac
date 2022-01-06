package br.org.quilombola.model.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.Quilombo;

@RepositoryRestResource(collectionResourceRel = "quilombos" , path = "quilombos")
public interface QuilomboRepository extends IRepository<Quilombo, Long> {
	
//	@Query("select q from Quilombo q join Territorio t where t.id = :id")
//	List<Quilombo> findByTerritorioList_Id(Long id);

	List<Quilombo> findByComunidades_Id(Long id);

}
