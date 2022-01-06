package br.org.quilombola.model.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioCcdru;

@RepositoryRestResource(collectionResourceRel = "relatorioterritorioccdru" , path = "relatorioterritorioccdru")
public interface RelatorioTerritorioCcdruRepository extends IRepository<RelatorioTerritorioCcdru, Long>{

	List<RelatorioTerritorioCcdru> findByEstado(String uf);
	
	List<RelatorioTerritorioCcdru> findByIdTerritorio(Integer id);
}
