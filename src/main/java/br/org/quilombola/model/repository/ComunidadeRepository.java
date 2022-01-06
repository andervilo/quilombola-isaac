package br.org.quilombola.model.repository;

import java.util.List;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.Comunidade;

@Transactional
@RepositoryRestResource(collectionResourceRel = "comunidades" , path = "comunidades")
public interface ComunidadeRepository extends IRepository<Comunidade, Long>{

	List<Comunidade> findByNomeContainingIgnoreCaseAndMunicipiosEstadoOrMunicipiosTerritorioListNomeContainingIgnoreCaseAndMunicipiosEstado(String comunidadeNome, String estado1, String territorioNome, String estado2);
	
	List<Comunidade> findByMunicipiosEstado(String estado);
	
	List<Comunidade> findByNomeContainingIgnoreCase(String nomeComunidade);
	
	

}
