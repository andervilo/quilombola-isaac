package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.ProcessoAdministrativo;

@RepositoryRestResource(collectionResourceRel = "processosadministrativos" , path = "processosadministrativos")
public interface ProcessoAdministrativoRepository extends IRepository<ProcessoAdministrativo, Long>{

}
