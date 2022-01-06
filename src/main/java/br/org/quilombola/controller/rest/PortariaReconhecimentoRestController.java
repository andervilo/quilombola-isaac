package br.org.quilombola.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.PortariaReconhecimento;
import br.org.quilombola.model.service.PortariaReconhecimentoService;

@RestController
@RequestMapping("/api/v1/portariasreconhecimentos")
public class PortariaReconhecimentoRestController extends AbstractRestController<PortariaReconhecimento, PortariaReconhecimentoService> {

}
