package br.org.quilombola.controller.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.TipoDocumento;
import br.org.quilombola.model.service.TipoDocumentoService;

@RestController
@RequestMapping("/api/v1/tipodocumentos")
public class TipoDocumentoRestController extends AbstractRestController<TipoDocumento, TipoDocumentoService>{

}
