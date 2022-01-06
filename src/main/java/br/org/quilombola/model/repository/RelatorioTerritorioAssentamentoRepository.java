package br.org.quilombola.model.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioAssentamento;

@RepositoryRestResource(collectionResourceRel = "relatorioterritorioassentamento" , path = "relatorioterritorioassentamento")
public interface RelatorioTerritorioAssentamentoRepository extends IRepository<RelatorioTerritorioAssentamento, Long>{

	List<RelatorioTerritorioAssentamento> findByEstado(String uf);
	
	List<RelatorioTerritorioAssentamento> findByIdTerritorio(Integer id);
}
