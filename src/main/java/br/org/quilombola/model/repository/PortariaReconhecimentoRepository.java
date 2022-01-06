package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.PortariaReconhecimento;

@RepositoryRestResource(collectionResourceRel = "portariasreconhecimentos" , path = "portariasreconhecimentos")
public interface PortariaReconhecimentoRepository extends IRepository<PortariaReconhecimento, Long> {

}
