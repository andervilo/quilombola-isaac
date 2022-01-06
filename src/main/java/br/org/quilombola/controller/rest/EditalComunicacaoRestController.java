package br.org.quilombola.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.entity.EditalComunicacao;
import br.org.quilombola.model.service.EditalComunicacaoService;

@RestController
@RequestMapping("/api/v1/editaiscomunicacoes")
public class EditalComunicacaoRestController extends AbstractRestController<EditalComunicacao, EditalComunicacaoService> {

}
