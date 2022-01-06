package br.org.quilombola.model.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.Certificado;

@RepositoryRestResource(collectionResourceRel = "certificados" , path = "certificados")
public interface CertificadoRepository extends IRepository<Certificado, Long> {

}
