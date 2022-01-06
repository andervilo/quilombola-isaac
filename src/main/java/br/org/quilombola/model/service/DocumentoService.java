package br.org.quilombola.model.service;

import org.springframework.stereotype.Service;

import br.org.quilombola.arquitetura.GenericService;
import br.org.quilombola.model.entity.Documento;
import br.org.quilombola.model.repository.DocumentoRepository;

@Service
public class DocumentoService extends GenericService<DocumentoRepository, Documento>{

}
