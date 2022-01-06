package br.org.quilombola.arquitetura.security.controller.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.arquitetura.security.model.entity.Permissao;
import br.org.quilombola.arquitetura.security.model.service.PermissaoService;

@RestController
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
@RequestMapping("/api/v1/security/permissoes")
public class PermissaoRestController extends AbstractRestController<Permissao, PermissaoService> {

}
