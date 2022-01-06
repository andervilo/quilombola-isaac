package br.org.quilombola.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.Ccdru;
import br.org.quilombola.model.service.CcdruService;

@RestController
@RequestMapping("/api/v1/ccdrus")
public class CcdruRestController extends AbstractRestController<Ccdru, CcdruService> {

}
