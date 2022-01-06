package br.org.quilombola.model.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.relatorios.RelatorioComunidade;

@RepositoryRestResource(collectionResourceRel = "relatoriocomunidade" , path = "relatoriocomunidade")
public interface RelatorioComunidadeRepository extends IRepository<RelatorioComunidade, Long>{

	List<RelatorioComunidade> findByEstado(String uf);
}
