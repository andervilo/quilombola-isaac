package br.org.quilombola.model.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioPortaria;

@RepositoryRestResource(collectionResourceRel = "relatorioterritorioportaria" , path = "relatorioterritorioportaria")
public interface RelatorioTerritorioPortariaRepository extends IRepository<RelatorioTerritorioPortaria, Long>{

	List<RelatorioTerritorioPortaria> findByEstado(String uf);
	
	List<RelatorioTerritorioPortaria> findByIdTerritorio(Integer id);
}
