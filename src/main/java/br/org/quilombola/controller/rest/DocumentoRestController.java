package br.org.quilombola.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.Documento;
import br.org.quilombola.model.service.DocumentoService;

@RestController
@RequestMapping("/api/v1/documentos")
public class DocumentoRestController extends AbstractRestController<Documento, DocumentoService>{

}
