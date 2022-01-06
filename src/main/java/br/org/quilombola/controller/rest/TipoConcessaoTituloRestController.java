package br.org.quilombola.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.TipoConcessaoTitulo;
import br.org.quilombola.model.service.TipoConcessaoTituloService;

@RestController
@RequestMapping("/api/v1/tiposconcessoes")
public class TipoConcessaoTituloRestController extends AbstractRestController<TipoConcessaoTitulo, TipoConcessaoTituloService> {

}
