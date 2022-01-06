package br.org.quilombola.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.ProcessoAdministrativo;
import br.org.quilombola.model.service.ProcessoAdministrativoService;

@RestController
@RequestMapping("/api/v1/processosadministrativos")
public class ProcessoAdministrativoRestController extends AbstractRestController<ProcessoAdministrativo, ProcessoAdministrativoService> {

}
