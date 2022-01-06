package br.org.quilombola.controller.rest;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.org.quilombola.arquitetura.AbstractRestController;
import br.org.quilombola.arquitetura.criteria.BodyFIlterAndSort;
import br.org.quilombola.arquitetura.criteria.DirectionEnum;
import br.org.quilombola.arquitetura.criteria.Filter;
import br.org.quilombola.arquitetura.criteria.Sorting;
import br.org.quilombola.model.dto.CertificadoDTO;
import br.org.quilombola.model.dto.DadosConsolidadosDTO;
import br.org.quilombola.model.dto.DecretoDesapropriatorioDTO;
import br.org.quilombola.model.dto.EditalComunicacaoDTO;
import br.org.quilombola.model.dto.MatriculaDTO;
import br.org.quilombola.model.dto.PortariaReconhecimentoDTO;
import br.org.quilombola.model.dto.ProcessoAdministrativoDTO;
import br.org.quilombola.model.dto.TituloPropriedadeDTO;
import br.org.quilombola.model.entity.AssentamentoQuilombola;
import br.org.quilombola.model.entity.Certificado;
import br.org.quilombola.model.entity.Comunidade;
import br.org.quilombola.model.entity.DadosAdicionaisComunidade;
import br.org.quilombola.model.entity.DecretoDesapropriatorio;
import br.org.quilombola.model.entity.EditalComunicacao;
import br.org.quilombola.model.entity.Matricula;
import br.org.quilombola.model.entity.Municipio;
import br.org.quilombola.model.entity.Orgao;
import br.org.quilombola.model.entity.PortariaReconhecimento;
import br.org.quilombola.model.entity.ProcessoAdministrativo;
import br.org.quilombola.model.entity.TituloPropriedade;
import br.org.quilombola.model.repository.AssentamentoQuilombolaRepository;
import br.org.quilombola.model.repository.ComunidadeRepository;
import br.org.quilombola.model.repository.DadosAdicionaisRepository;
import br.org.quilombola.model.service.AssentamentoQuilombolaService;
import br.org.quilombola.model.service.CertificadoService;
import br.org.quilombola.model.service.ComunidadeService;
import br.org.quilombola.model.service.DecretoDesapropriatorioService;
import br.org.quilombola.model.service.EditalComunicacaoService;
import br.org.quilombola.model.service.MatriculaService;
import br.org.quilombola.model.service.MunicipioService;
import br.org.quilombola.model.service.OrgaoService;
import br.org.quilombola.model.service.PortariaReconhecimentoService;
import br.org.quilombola.model.service.ProcessoAdministrativoService;
import br.org.quilombola.model.service.TerritorioService;
import br.org.quilombola.model.service.TituloPropriedadeService;

@RestController
@RequestMapping("/api/v1/comunidades")
public class ComunidadeRestController extends AbstractRestController<Comunidade, ComunidadeService> {
	
	@Autowired
	private MunicipioService municipioService;

	@Autowired
	private TerritorioService territorioService;
	
	@Autowired
	private ComunidadeService comunidadeService;
	
	@Autowired
	private DecretoDesapropriatorioService decretoService;
	
	@Autowired
	private CertificadoService certificadoService;
	
	@Autowired
	private MatriculaService matriculaService;
	
	@Autowired
	private PortariaReconhecimentoService portRecService;
	
	@Autowired
	private AssentamentoQuilombolaRepository assentamentoQuilombolaRepository;
	
	@Autowired
	private EditalComunicacaoService editalService;
	
	@Autowired
	private TituloPropriedadeService tituloService;
	
	@Autowired
	private OrgaoService orgaoService;
	
	@Autowired
	private ProcessoAdministrativoService processoService;
	
	@Autowired
	private AssentamentoQuilombolaService assentamentoService;
	
	@Autowired
	private ComunidadeRepository comunidadeRepository;
	
	@Autowired
	private DadosAdicionaisRepository dadosAdicionaisRepository;
	
	// TODO desvincular-territorio
	@PutMapping("/desvincular-comunidade")
	public ResponseEntity<?> desvincularTerritorio(@RequestBody Map<String, Long> payload){
		Municipio municipio = municipioService.findById(payload.get("municipioId"));
		Comunidade comunidade = comunidadeService.findById(payload.get("comunidadeId"));

		if(municipio == null){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Município não encontrado!");
		}
		if(comunidade == null){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comunidade não encontrada!");
		}
		System.out.println(municipio);
		if(!comunidade.getMunicipios().contains(municipio)){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Município não está vinculado à Comunidade informada!");
		}

		municipio.removeComunidade(comunidade);
		municipioService.getRepository().save(municipio);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Município desvinculado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/vincular-comunidade")
	public ResponseEntity<?> vincularTerritorio(@RequestBody Map<String, Long> payload){
		Municipio municipio = municipioService.findById(payload.get("municipioId"));
		Comunidade comunidade = comunidadeService.findById(payload.get("comunidadeId"));

		if(municipio == null){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Município não encontrado!");
		}
		if(comunidade == null){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comunidade não encontrada!");
		}

		if(comunidade.getMunicipios().contains(municipio)){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Município já está vinculado à COmunidade informada!");
		}

		municipio.addComunidade(comunidade);
		municipioService.getRepository().save(municipio);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Município vinculado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/decretodesapropriatorio")
	public ResponseEntity<?> createDecreto(@RequestBody DecretoDesapropriatorioDTO decreto){
		System.out.println(decreto);
		try {
			Comunidade comunidade = this.comunidadeService.findById(decreto.getComunidade());
			DecretoDesapropriatorio desapropriatorio = new DecretoDesapropriatorio();
			desapropriatorio.setDataDecretoDeaproriacaoa(decreto.getDataDecretoDeaproriacaoa());
			desapropriatorio.setDataDecretoDesapropriacaoDiarioOficialUniao(decreto.getDataDecretoDesapropriacaoDiarioOficialUniao());
			desapropriatorio.setNumeroDecretoDesapropriacao(decreto.getNumeroDecretoDesapropriacao());
			desapropriatorio.setLinkPublicacao_1(decreto.getLinkPublicacao_1());
			desapropriatorio.setLinkPublicacao_2(decreto.getLinkPublicacao_2());
			desapropriatorio.setLinkPublicacao_3(decreto.getLinkPublicacao_3());
			desapropriatorio.setSecaoDO(decreto.getSecaoDO());
			desapropriatorio.setFolhaDO(decreto.getFolhaDO());
			//TODO(trocar por territorio)
			// desapropriatorio.setComunidade(comunidade);
			this.decretoService.create(desapropriatorio);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Decreto adicionado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/decretodesapropriatorio")
	public ResponseEntity<?> updateDecreto(@RequestBody DecretoDesapropriatorioDTO decreto){
		try {
			Comunidade comunidade = this.comunidadeService.findById(decreto.getComunidade());
			DecretoDesapropriatorio desapropriatorio = this.decretoService.findById(decreto.getId());
			desapropriatorio.setDataDecretoDeaproriacaoa(decreto.getDataDecretoDeaproriacaoa());
			desapropriatorio.setDataDecretoDesapropriacaoDiarioOficialUniao(decreto.getDataDecretoDesapropriacaoDiarioOficialUniao());
			desapropriatorio.setNumeroDecretoDesapropriacao(decreto.getNumeroDecretoDesapropriacao());
			//TODO(trocar por territorio)
			// desapropriatorio.setComunidade(comunidade);
			desapropriatorio.setLinkPublicacao_1(decreto.getLinkPublicacao_1());
			desapropriatorio.setLinkPublicacao_2(decreto.getLinkPublicacao_2());
			desapropriatorio.setLinkPublicacao_3(decreto.getLinkPublicacao_3());
			desapropriatorio.setSecaoDO(decreto.getSecaoDO());
			desapropriatorio.setFolhaDO(decreto.getFolhaDO());
			this.decretoService.update(desapropriatorio.getId(), desapropriatorio);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Decreto atualizado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/{id}/certificado")
	public ResponseEntity<?> createCertificado(@PathVariable Long id, @RequestBody Certificado _certificado){
		try {
			Comunidade comunidade = this.comunidadeService.findById(id);
			_certificado.setComunidade(comunidade);
			Certificado certificado = this.certificadoService.create(_certificado);
			
//			this.comunidadeService.update(comunidade.getId(), comunidade);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Certificado adicionado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/certificado")
	public ResponseEntity<?> updateCertificado(@RequestBody CertificadoDTO certificadoDTO){
		try {
			Comunidade comunidade = this.comunidadeService.findById(certificadoDTO.getComunidade());
			Certificado certificado = this.certificadoService.findById(certificadoDTO.getId());
			certificado.setDataAberturaProcessoCertificado(certificadoDTO.getDataAberturaProcessoCertificado());
			certificado.setNumeroProcessoFcp(certificadoDTO.getNumeroProcessoFcp());
			certificado.setRetificacaoFcpObservacao(certificadoDTO.getRetificacaoFcpObservacao());
			certificado.setComunidade(comunidade);
			this.certificadoService.update(certificado.getId(),certificado);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Certificado atualizado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/matricula")
	public ResponseEntity<?> createMatricula(@RequestBody MatriculaDTO matriculaDTO){
		try {
			Comunidade comunidade = this.comunidadeService.findById(matriculaDTO.getComunidade());
			Matricula matricula = new Matricula();
			matricula.setCartorioRegistroImoveis(matriculaDTO.getCartorioRegistroImoveis());
			matricula.setFolhaRegistroImoveis(matriculaDTO.getFolhaRegistroImoveis());
			matricula.setLivroRegistroImoveis(matriculaDTO.getLivroRegistroImoveis());
			matricula.setMatricula(matriculaDTO.getMatricula());
			matricula.setLinkPublicacao_1(matriculaDTO.getLinkPublicacao_1());
			matricula.setLinkPublicacao_2(matriculaDTO.getLinkPublicacao_2());
			matricula.setLinkPublicacao_3(matriculaDTO.getLinkPublicacao_3());
			matricula.setSecaoDO(matriculaDTO.getSecaoDO());
			matricula.setFolhaDO(matriculaDTO.getFolhaDO());
			
			this.matriculaService.create(matricula);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Matrícula adicionada com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/matricula")
	public ResponseEntity<?> updateMatricula(@RequestBody MatriculaDTO matriculaDTO){
		try {
			Comunidade comunidade = this.comunidadeService.findById(matriculaDTO.getComunidade());
			Matricula matricula = this.matriculaService.findById(matriculaDTO.getId());
			matricula.setCartorioRegistroImoveis(matriculaDTO.getCartorioRegistroImoveis());
			matricula.setFolhaRegistroImoveis(matriculaDTO.getFolhaRegistroImoveis());
			matricula.setLivroRegistroImoveis(matriculaDTO.getLivroRegistroImoveis());
			matricula.setMatricula(matriculaDTO.getMatricula());
			matricula.setLinkPublicacao_1(matriculaDTO.getLinkPublicacao_1());
			matricula.setLinkPublicacao_2(matriculaDTO.getLinkPublicacao_2());
			matricula.setLinkPublicacao_3(matriculaDTO.getLinkPublicacao_3());
			matricula.setSecaoDO(matriculaDTO.getSecaoDO());
			matricula.setFolhaDO(matriculaDTO.getFolhaDO());
			
			this.matriculaService.update(matricula.getId(), matricula);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Matrícula atualizada com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/portaria-reconhecimento")
	public ResponseEntity<?> createPortariaReconhecimento(@RequestBody PortariaReconhecimentoDTO portRecDTO){
		try {
			Comunidade comunidade = this.comunidadeService.findById(portRecDTO.getComunidade());
			PortariaReconhecimento portRec = new PortariaReconhecimento();
			portRec.setDataPortariaReconhecimento(portRecDTO.getDataPortariaReconhecimento());
			portRec.setNumeroPortariaReconhecimento(portRecDTO.getNumeroPortariaReconhecimento());
			portRec.setPortariaReconhecimentoDiarioOficialUniao(portRecDTO.getPortariaReconhecimentoDiarioOficialUniao());
			portRec.setLinkPublicacao_1(portRecDTO.getLinkPublicacao_1());
			portRec.setLinkPublicacao_2(portRecDTO.getLinkPublicacao_2());
			portRec.setLinkPublicacao_3(portRecDTO.getLinkPublicacao_3());
			portRec.setSecaoDO(portRecDTO.getSecaoDO());
			portRec.setFolhaDO(portRecDTO.getFolhaDO());
			this.portRecService.create(portRec);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Portaria Reconhecimento adicionada com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/portaria-reconhecimento")
	public ResponseEntity<?> updatePortariaReconhecimento(@RequestBody PortariaReconhecimentoDTO portRecDTO){
		try {
			Comunidade comunidade = this.comunidadeService.findById(portRecDTO.getComunidade());
			PortariaReconhecimento portRec = this.portRecService.findById(portRecDTO.getId());
			portRec.setDataPortariaReconhecimento(portRecDTO.getDataPortariaReconhecimento());
			portRec.setNumeroPortariaReconhecimento(portRecDTO.getNumeroPortariaReconhecimento());
			portRec.setPortariaReconhecimentoDiarioOficialUniao(portRecDTO.getPortariaReconhecimentoDiarioOficialUniao());
			portRec.setLinkPublicacao_1(portRecDTO.getLinkPublicacao_1());
			portRec.setLinkPublicacao_2(portRecDTO.getLinkPublicacao_2());
			portRec.setLinkPublicacao_3(portRecDTO.getLinkPublicacao_3());
			portRec.setSecaoDO(portRecDTO.getSecaoDO());
			portRec.setFolhaDO(portRecDTO.getFolhaDO());
			this.portRecService.update(portRec.getId(),portRec);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Portaria Reconhecimento atualizada com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	
	@PostMapping("/edital-comunicacao")
	public ResponseEntity<?> createEdital(@RequestBody EditalComunicacaoDTO editalDTO){
		try {
			Comunidade comunidade = this.comunidadeService.findById(editalDTO.getComunidade());
			EditalComunicacao edital = new EditalComunicacao();
			edital.setAreaHaEdital(editalDTO.getAreaHaEdital());
			edital.setDataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital(editalDTO.getDataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital());
			edital.setDataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital(editalDTO.getDataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital());
			edital.setNumeroFamilias(editalDTO.getNumeroFamilias());
			edital.setLinkPublicacao_1(editalDTO.getLinkPublicacao_1());
			edital.setLinkPublicacao_2(editalDTO.getLinkPublicacao_2());
			edital.setLinkPublicacao_3(editalDTO.getLinkPublicacao_3());
			edital.setSecaoDO(editalDTO.getSecaoDO());
			edital.setFolhaDO(editalDTO.getFolhaDO());
			this.editalService.create(edital);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Edital Comunicação adicionado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/edital-comunicacao")
	public ResponseEntity<?> updateEdital(@RequestBody EditalComunicacaoDTO editalDTO){
		try {
			Comunidade comunidade = this.comunidadeService.findById(editalDTO.getComunidade());
			EditalComunicacao edital = this.editalService.findById(editalDTO.getId());
			edital.setAreaHaEdital(editalDTO.getAreaHaEdital());
			edital.setDataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital(editalDTO.getDataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital());
			edital.setDataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital(editalDTO.getDataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital());
			edital.setNumeroFamilias(editalDTO.getNumeroFamilias());
			edital.setLinkPublicacao_1(editalDTO.getLinkPublicacao_1());
			edital.setLinkPublicacao_2(editalDTO.getLinkPublicacao_2());
			edital.setLinkPublicacao_3(editalDTO.getLinkPublicacao_3());
			edital.setSecaoDO(editalDTO.getSecaoDO());
			edital.setFolhaDO(editalDTO.getFolhaDO());
			this.editalService.update(edital.getId(), edital);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Edital Comunicação atualizado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/edital-comunicacao2")
	public ResponseEntity<?> createEdital2(@RequestBody EditalComunicacaoDTO editalDTO){
		try {
			Comunidade comunidade = this.comunidadeService.findById(editalDTO.getComunidade());
			EditalComunicacao edital = new EditalComunicacao();
			edital.setAreaHaEdital(editalDTO.getAreaHaEdital());
			edital.setDataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital(editalDTO.getDataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital());
			edital.setDataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital(editalDTO.getDataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital());
			edital.setNumeroFamilias(editalDTO.getNumeroFamilias());
			edital.setLinkPublicacao_1(editalDTO.getLinkPublicacao_1());
			edital.setLinkPublicacao_2(editalDTO.getLinkPublicacao_2());
			edital.setLinkPublicacao_3(editalDTO.getLinkPublicacao_3());
			edital.setSecaoDO(editalDTO.getSecaoDO());
			edital.setFolhaDO(editalDTO.getFolhaDO());
			
			EditalComunicacao _edital = this.editalService.create(edital);
			this.comunidadeService.update(comunidade.getId(), comunidade);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Edital Comunicação adicionado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/edital-comunicacao2")
	public ResponseEntity<?> updateEdital2(@RequestBody EditalComunicacaoDTO editalDTO){
		try {
			Comunidade comunidade = this.comunidadeService.findById(editalDTO.getComunidade());
			EditalComunicacao edital = this.editalService.findById(editalDTO.getId());
			edital.setAreaHaEdital(editalDTO.getAreaHaEdital());
			edital.setDataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital(editalDTO.getDataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital());
			edital.setDataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital(editalDTO.getDataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital());
			edital.setNumeroFamilias(editalDTO.getNumeroFamilias());
			edital.setLinkPublicacao_1(editalDTO.getLinkPublicacao_1());
			edital.setLinkPublicacao_2(editalDTO.getLinkPublicacao_2());
			edital.setLinkPublicacao_3(editalDTO.getLinkPublicacao_3());
			edital.setSecaoDO(editalDTO.getSecaoDO());
			edital.setFolhaDO(editalDTO.getFolhaDO());
			this.editalService.update(edital.getId(), edital);
			this.comunidadeService.update(comunidade.getId(), comunidade);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Edital Comunicação atualizado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/titulo-propriedade")
	public ResponseEntity<?> createTitulo(@RequestBody TituloPropriedadeDTO tituloDTO){
		try {
			Orgao orgao = this.orgaoService.findById(tituloDTO.getOrgaoExpedidor());
			TituloPropriedade titulo = new TituloPropriedade();
			titulo.setAreaHaTitulo(tituloDTO.getAreaHaTitulo());
			titulo.setDataTitulo(tituloDTO.getDataTitulo());
			titulo.setNumeroFamiliaTitulos(tituloDTO.getNumeroFamiliaTitulos());
			titulo.setPercentagemAreaTitulada(tituloDTO.getPercentagemAreaTitulada());
			titulo.setObservacaoAreaTitulo(tituloDTO.getObservacaoAreaTitulo());
			titulo.setLinkPublicacao_1(tituloDTO.getLinkPublicacao_1());
			titulo.setLinkPublicacao_2(tituloDTO.getLinkPublicacao_2());
			titulo.setLinkPublicacao_3(tituloDTO.getLinkPublicacao_3());
			titulo.setSecaoDO(tituloDTO.getSecaoDO());
			titulo.setFolhaDO(tituloDTO.getFolhaDO());
			titulo.setOrgaoExpedidor(orgao);
			this.tituloService.create(titulo);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação! "+e.getMessage());
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Título adicionado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/titulo-propriedade")
	public ResponseEntity<?> updateTitulo(@RequestBody TituloPropriedadeDTO tituloDTO){
		try {
			Comunidade comunidade = this.comunidadeService.findById(tituloDTO.getComunidade());
			Orgao orgao = this.orgaoService.findById(tituloDTO.getOrgaoExpedidor());
			TituloPropriedade titulo = this.tituloService.findById(tituloDTO.getId());
			titulo.setAreaHaTitulo(tituloDTO.getAreaHaTitulo());
			titulo.setDataTitulo(tituloDTO.getDataTitulo());
			titulo.setNumeroFamiliaTitulos(tituloDTO.getNumeroFamiliaTitulos());
			titulo.setPercentagemAreaTitulada(tituloDTO.getPercentagemAreaTitulada());
			titulo.setObservacaoAreaTitulo(tituloDTO.getObservacaoAreaTitulo());
			titulo.setLinkPublicacao_1(tituloDTO.getLinkPublicacao_1());
			titulo.setLinkPublicacao_2(tituloDTO.getLinkPublicacao_2());
			titulo.setLinkPublicacao_3(tituloDTO.getLinkPublicacao_3());
			titulo.setSecaoDO(tituloDTO.getSecaoDO());
			titulo.setFolhaDO(tituloDTO.getFolhaDO());
			titulo.setOrgaoExpedidor(orgao);
			this.tituloService.update(titulo.getId(), titulo);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Título adicionado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/processo-administrativo")
	public ResponseEntity<?> createProcesso(@RequestBody ProcessoAdministrativoDTO processoDTO){
		try {
			Comunidade comunidade = this.comunidadeService.findById(processoDTO.getComunidade());
			ProcessoAdministrativo processo = new ProcessoAdministrativo();
			processo.setAmbito(processoDTO.getAmbito());
			processo.setLinkProcessoSei(processoDTO.getLinkProcessoSei());
			processo.setLinkProcessoSei2(processoDTO.getLinkProcessoSei2());
			processo.setLinkProcessoSei3(processoDTO.getLinkProcessoSei3());
			processo.setLocalizacaoAcervoFundiario(processoDTO.getLocalizacaoAcervoFundiario());
			processo.setNumeroProcessoReconhecimento(processoDTO.getNumeroProcessoReconhecimento());
			processo.setSecaoDO(processoDTO.getSecaoDO());
			processo.setFolhaDO(processoDTO.getFolhaDO());
			this.processoService.create(processo);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Processo adicionado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/processo-administrativo")
	public ResponseEntity<?> updateProcesso(@RequestBody ProcessoAdministrativoDTO processoDTO){
		System.out.println(processoDTO);
		try {
			Comunidade comunidade = this.comunidadeService.findById(processoDTO.getComunidade());
			ProcessoAdministrativo processo = this.processoService.findById(processoDTO.getId());
			processo.setAmbito(processoDTO.getAmbito());
			processo.setLinkProcessoSei(processoDTO.getLinkProcessoSei());
			processo.setLinkProcessoSei2(processoDTO.getLinkProcessoSei2());
			processo.setLinkProcessoSei3(processoDTO.getLinkProcessoSei3());
			processo.setLocalizacaoAcervoFundiario(processoDTO.getLocalizacaoAcervoFundiario());
			processo.setNumeroProcessoReconhecimento(processoDTO.getNumeroProcessoReconhecimento());
			processo.setSecaoDO(processoDTO.getSecaoDO());
			processo.setFolhaDO(processoDTO.getFolhaDO());
			this.processoService.update(processo.getId(), processo);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação!");
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Processo atualizado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/{comunidadeId}/assentamento-estadual")
	public ResponseEntity<?> createAcentamentoEstadual(@PathVariable Long comunidadeId, @RequestBody AssentamentoQuilombola assentamentoEstadual){
		try {
			Comunidade comunidade = this.comunidadeService.findById(comunidadeId);
			AssentamentoQuilombola assentamento = assentamentoService.create(assentamentoEstadual);
			this.comunidadeService.update(comunidade.getId(), comunidade);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação! "+e.getMessage());
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Assentamento Estadual adicionado com sucesso!");
		return ResponseEntity.ok().body(response);
	}

	
	@PostMapping("/{comunidadeId}/assentamento-federal")
	public ResponseEntity<?> createAcentamentoFederal(@PathVariable Long comunidadeId, @RequestBody AssentamentoQuilombola assentamentoFederal){
		try {
			Comunidade comunidade = this.comunidadeService.findById(comunidadeId);
			AssentamentoQuilombola assentamento = assentamentoService.create(assentamentoFederal);
			// comunidade.setAssentamentoFederal(assentamento);
			this.comunidadeService.update(comunidade.getId(), comunidade);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao realizar operação! "+e.getMessage());
		}		
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Assentamento Federal adicionado com sucesso!");
		return ResponseEntity.ok().body(response);
	}

	
	@PutMapping("/dados-adicionais/{id}")
	public ResponseEntity<?> updateDadosAdicionais(@PathVariable Long id, @RequestBody DadosAdicionaisComunidade dadosAdicionaisComunidade){
		Comunidade comunidade = this.comunidadeRepository.findById(id).get();
		if(dadosAdicionaisComunidade.getId() == null) {	
			dadosAdicionaisComunidade.setComunidade(comunidade);
			DadosAdicionaisComunidade _adicionaisComunidade = dadosAdicionaisRepository.saveAndFlush(dadosAdicionaisComunidade);
		}else {
			DadosAdicionaisComunidade _dadosAdicionaisComunidade = 
					dadosAdicionaisRepository.findById(dadosAdicionaisComunidade.getId()).get();
			org.springframework.beans.BeanUtils.copyProperties(dadosAdicionaisComunidade, _dadosAdicionaisComunidade, "id", "comunidade");
			dadosAdicionaisRepository.save(_dadosAdicionaisComunidade);
		}
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Dados atualizados com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/get-by-cod-uf/{id}")
	public ResponseEntity<?> getComunidadeByUf(@PathVariable int id){
		DadosConsolidadosDTO response = comunidadeService.getResumoByCodigoEstado(id);
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/dados-consolidados-nacional")
	public ResponseEntity<?> getDadosConsolidadosNacional(){
		return ResponseEntity.ok().body(comunidadeService.getDadosConsolidadosNacionalDTO());
	}
	
	@GetMapping("/teste-csv")
	public ResponseEntity<?> getComunidadeCsv(HttpServletRequest request, HttpServletResponse response){
		StringBuilder sb = new StringBuilder();
		sb.append("Nome Comunidade"+";");
		sb.append("Certificada"+";");
		sb.append(System.lineSeparator());
		
		List<Comunidade> list = (List<Comunidade>) comunidadeService.findAll(PageRequest.of(0, 10)).getContent();
		
		for(Comunidade com: list) {
			sb.append(
					com.getNome()+";"+
					com.getDadosAdicionais().getCertificada()+";'"
		);
			sb.append(System.lineSeparator());
		}
		
		InputStream inputStream = new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.ISO_8859_1));  			
		HttpHeaders headers = new HttpHeaders(); 			
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate"); 			
		headers.add("Pragma", "no-cache"); 			
		headers.add("Expires", "0");  						
		String nomeArquivo = "teste";  			
		response.setHeader("Content-Disposition", "attachment; filename=\" " + nomeArquivo + ".xls\"");
		return ResponseEntity.ok().headers(headers) 					
		.contentType(MediaType.parseMediaType("application/octet-stream")) 					
		.body(new InputStreamResource(inputStream));
	}
	
	
	
	@GetMapping("/estado/{ufCod}")
	public ResponseEntity<?> getComunidadeByUf_hotsite(@PathVariable int ufCod){
		List<Comunidade> comunidades = comunidadeService.getComunidadesByUfCode(ufCod);
		return ResponseEntity.ok().body(comunidades);
	}
	
	@PostMapping("/estado/{ufCod}")
	public ResponseEntity<?> getComunidadeByUfAndComunidade_hotsite(@PathVariable int ufCod, @RequestBody Map<String, String> payload){
		List<Comunidade> comunidades = new ArrayList<Comunidade>();
		if(payload.containsKey("comunidade")) {
			String comunidadeBusca = payload.get("comunidade");
			comunidades = comunidadeService.getComunidadesByUfCode(ufCod)
					.stream()
					.filter(com->com.getNome().contains(comunidadeBusca))
					.collect(Collectors.toList());
			
		}else if(payload.containsKey("territorio")){
			String territorioBusca = payload.get("territorio");
			comunidades = comunidadeService.getComunidadesByUfCode(ufCod)
					.stream()
					.filter(com -> com.getTerritorios().stream().allMatch(territorio -> territorio.getNome().contains(territorioBusca)))
					.collect(Collectors.toList());
		}else {
			comunidades = comunidadeService.getComunidadesByUfCode(ufCod);
		}
		return ResponseEntity.ok().body(comunidades);
	}
	
	@PostMapping(value={"/search/{ufCod}","/search"})
	public ResponseEntity<?> getComunidadeByUfAndSearch_hotsite(@Nullable @PathVariable Integer ufCod, @Nullable @RequestBody Map<String, String> payload){
		List<Comunidade> comunidades = new ArrayList<Comunidade>();
		String textoBusca = null;
		
		
		if(ufCod != null) {
			if(payload != null && payload.containsKey("busca")) {				
				if(!StringUtils.isEmpty(payload.containsKey("busca"))) {
					textoBusca = payload.get("busca");
					comunidades = comunidadeService.getComunidadesByUfCodeAndSearchText(ufCod.intValue(), textoBusca);
				}			
			}else{
				comunidades = comunidadeService.getComunidadesByUfCodeAndSearchText(ufCod.intValue(), null);
			}
		}else if(ufCod == null && payload != null && payload.containsKey("busca") && !StringUtils.isEmpty(payload.containsKey("busca"))){
			textoBusca = payload.get("busca");
			comunidades = comunidadeService.getComunidadesByNome(textoBusca);
		}		
		
		return ResponseEntity.ok().body(comunidades);
	}
	
	@PostMapping("/{id}/territorios")
	public ResponseEntity<?> getTerritoriosByComunidadeId(@PathVariable Long id){
		return comunidadeService.getTerritoriosByComunidadeId(id);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/criteria" })
	public ResponseEntity<?> getByCriteria(@Nullable Pageable pageable,
			@Nullable @RequestBody BodyFIlterAndSort bodyFIlter)
			throws JsonParseException, JsonMappingException, IOException {

		Pageable pageable2 = null;
		if (bodyFIlter != null) {
			if (bodyFIlter.getSize() == 0) {
				bodyFIlter.setSize(5);
			}

			if (bodyFIlter.getFilters() == null) {
				bodyFIlter.setFilters(new ArrayList<>());
			}

			pageable2 = PageRequest.of(bodyFIlter.getPage(), bodyFIlter.getSize(),
					Direction.valueOf(bodyFIlter.getSorting().getDirection().name()),
					bodyFIlter.getSorting().getField());

			if (bodyFIlter.getSorting() == null) {
				bodyFIlter.setSorting(new Sorting("id", DirectionEnum.ASC));
			}

			return ResponseEntity.ok().body(
					this.getService().findByCriteria(pageable2, bodyFIlter.getSorting(), bodyFIlter.getFilters()));
		}

		return ResponseEntity.ok().body(this.getService().findByCriteria(pageable, new Sorting("id", DirectionEnum.ASC),
				new ArrayList<Filter>()));
	}

}
