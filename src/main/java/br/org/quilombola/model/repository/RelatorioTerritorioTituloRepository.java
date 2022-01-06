package br.org.quilombola.model.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioTitulo;

@RepositoryRestResource(collectionResourceRel = "relatorioterritoriotitulo" , path = "relatorioterritoriotitulo")
public interface RelatorioTerritorioTituloRepository extends IRepository<RelatorioTerritorioTitulo, Long>{

	List<RelatorioTerritorioTitulo> findByEstado(String uf);
	
	List<RelatorioTerritorioTitulo> findByIdTerritorio(Integer id);
}
