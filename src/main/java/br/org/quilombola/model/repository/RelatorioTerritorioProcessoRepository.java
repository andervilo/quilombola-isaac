package br.org.quilombola.model.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioProcesso;

@RepositoryRestResource(collectionResourceRel = "relatorioterritorioprocesso" , path = "relatorioterritorioprocesso")
public interface RelatorioTerritorioProcessoRepository extends IRepository<RelatorioTerritorioProcesso, Long>{

	List<RelatorioTerritorioProcesso> findByIdTerritorio(Integer id);
}
