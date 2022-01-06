package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.TipoDocumento;

@RepositoryRestResource(collectionResourceRel = "tiposdocumentos" , path = "tiposdocumentos")
public interface TipoDocumentoRepository extends IRepository<TipoDocumento, Long>{

}
