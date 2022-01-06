package br.org.quilombola.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.Orgao;
import br.org.quilombola.model.service.OrgaoService;

@RestController
@RequestMapping("/api/v1/orgaos")
public class OrgaoRestController extends AbstractRestController<Orgao, OrgaoService> {

}
