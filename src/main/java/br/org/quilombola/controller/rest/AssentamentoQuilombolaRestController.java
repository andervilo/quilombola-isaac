package br.org.quilombola.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.AssentamentoQuilombola;
import br.org.quilombola.model.service.AssentamentoQuilombolaService;

@RestController
@RequestMapping("/api/v1/assentamentos")
public class AssentamentoQuilombolaRestController extends AbstractRestController<AssentamentoQuilombola, AssentamentoQuilombolaService> {

}
