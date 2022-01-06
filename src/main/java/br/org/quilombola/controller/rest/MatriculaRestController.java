package br.org.quilombola.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.Matricula;
import br.org.quilombola.model.service.MatriculaService;

@RestController
@RequestMapping("/api/v1/matriculas")
public class MatriculaRestController extends AbstractRestController<Matricula, MatriculaService> {

}
