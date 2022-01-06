package br.org.quilombola.model.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioEdital;

@RepositoryRestResource(collectionResourceRel = "relatorioterritorioedital" , path = "relatorioterritorioedital")
public interface RelatorioTerritorioEditalRepository extends IRepository<RelatorioTerritorioEdital, Long>{

	List<RelatorioTerritorioEdital> findByEstado(String uf);
	
	List<RelatorioTerritorioEdital> findByIdTerritorio(Integer id);
}
