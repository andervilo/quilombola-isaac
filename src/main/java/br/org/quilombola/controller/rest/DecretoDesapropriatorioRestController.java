package br.org.quilombola.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.DecretoDesapropriatorio;
import br.org.quilombola.model.service.DecretoDesapropriatorioService;

@RestController
@RequestMapping("/api/v1/decretosdesapropriatorios")
public class DecretoDesapropriatorioRestController extends AbstractRestController<DecretoDesapropriatorio, DecretoDesapropriatorioService> {

}
