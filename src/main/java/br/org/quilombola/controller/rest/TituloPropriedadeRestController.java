package br.org.quilombola.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.TituloPropriedade;
import br.org.quilombola.model.service.TituloPropriedadeService;

@RestController
@RequestMapping("/api/v1/titulospropriedades")
public class TituloPropriedadeRestController extends AbstractRestController<TituloPropriedade, TituloPropriedadeService> {

}
