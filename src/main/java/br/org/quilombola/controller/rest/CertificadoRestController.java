package br.org.quilombola.controller.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.model.dto.CertificadoDTO;
import br.org.quilombola.model.entity.Certificado;
import br.org.quilombola.model.service.CertificadoService;
import br.org.quilombola.response.Response;

@RestController
@RequestMapping("/api/v1/certificados")
public class CertificadoRestController extends AbstractRestController<Certificado, CertificadoService> {
	

}
