package br.org.quilombola.model.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioDecreto;

@RepositoryRestResource(collectionResourceRel = "relatorioterritoriodecreto" , path = "relatorioterritoriodecreto")
public interface RelatorioTerritorioDecretoRepository extends IRepository<RelatorioTerritorioDecreto, Long>{

	List<RelatorioTerritorioDecreto> findByEstado(String uf);
	
	List<RelatorioTerritorioDecreto> findByIdTerritorio(Integer id);
}
