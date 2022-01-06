package br.org.quilombola.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.Superintendencia;
import br.org.quilombola.model.service.SuperintendenciaService;

@RestController
@RequestMapping("/api/v1/superintendencias")
public class SuperintendenciaRestController extends AbstractRestController<Superintendencia, SuperintendenciaService> {

}
