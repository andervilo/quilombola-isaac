package br.org.quilombola.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.org.quilombola.arquitetura.GenericService;
import br.org.quilombola.arquitetura.criteria.Filter;
import br.org.quilombola.arquitetura.criteria.Sorting;
import br.org.quilombola.arquitetura.enums.Estado;
import br.org.quilombola.model.dto.DadosConsolidadosDTO;
import br.org.quilombola.model.dto.DadosConsolidadosNacionalDTO;
import br.org.quilombola.model.entity.Comunidade;
import br.org.quilombola.model.entity.DadosComunidadeVw;
import br.org.quilombola.model.entity.DadosTerritorioVw;
import br.org.quilombola.model.entity.Municipio;
import br.org.quilombola.model.entity.Territorio;
import br.org.quilombola.model.repository.ComunidadeRepository;
import br.org.quilombola.model.repository.ComunidadeVWRepository;
import br.org.quilombola.model.repository.TerritorioVWRepository;
import br.org.quilombola.model.specification.ComunidadeSpec;

@Service
public class ComunidadeService extends GenericService<ComunidadeRepository, Comunidade>{
	
	@Autowired
	private ComunidadeRepository comunidadeRepository;
	
	@Autowired
	private ComunidadeVWRepository comunidadeVWRepository;
	
	@Autowired
	private TerritorioVWRepository territorioVWRepository;
	
	@Autowired
	private MunicipioService municipioService;
	
	private int comunidadesCertificadas = 0;
	private int comunidadesEdital = 0;
	private int comunidadesDecreto = 0;
	private int comunidadesTitulo = 0;
	private int comunidadesPortaria = 0;
	private int comunidadesProcessoReconhecimento = 0;
	private int comunidadesAcentamento = 0;
	private int totalFamiliasTituladas = 0;
	private Double totalHectaresTitulados = 0.0;
	
	
	public ResponseEntity<?> getTerritoriosByComunidadeId(Long id){
		List<Territorio> territorios = new ArrayList<Territorio>();
		
		
		return ResponseEntity.ok().body(territorios);
	}
	
	public DadosConsolidadosDTO getResumoByCodigoEstado(int codUf){
		DadosConsolidadosDTO consolidadosDTO = new DadosConsolidadosDTO();
		DadosComunidadeVw comunidadeVw = new DadosComunidadeVw();
		comunidadeVw = comunidadeVWRepository.findByEstado(Estado.getEstadoByCodigoIbge(codUf).getSigla()).
				orElse(new DadosComunidadeVw(Estado.getEstadoByCodigoIbge(codUf).getSigla(), 0, 0, 0, 0, 0, 0, 0, 0, 0));
		
		DadosTerritorioVw dadosTerritorioVw = new DadosTerritorioVw();
		dadosTerritorioVw = territorioVWRepository.findByEstado(Estado.getEstadoByCodigoIbge(codUf).getSigla()).
				orElse(new DadosTerritorioVw(Estado.getEstadoByCodigoIbge(codUf).getSigla(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0, 0, 0.0, 0, 0.0, 0));
		
		consolidadosDTO.setDadosComunidadeVw(comunidadeVw);
		consolidadosDTO.setDadosTerritorioVw(dadosTerritorioVw);
		return consolidadosDTO;
	}
	
	public Object getDadosConsolidadosNacionalDTO() {
		DadosComunidadeVw comunidadeVw = new DadosComunidadeVw("", 0, 0, 0, 0, 0, 0, 0, 0, 0);
		DadosTerritorioVw territorioVw = new DadosTerritorioVw("", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0, 0, 0.0, 0, 0.0, 0);
		
		DadosComunidadeVw comunidadeNorte = new DadosComunidadeVw("", 0, 0, 0, 0, 0, 0, 0, 0, 0);
		DadosTerritorioVw territorioNorte = new DadosTerritorioVw("", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0, 0, 0.0, 0, 0.0, 0);
		
		DadosComunidadeVw comunidadeNordeste = new DadosComunidadeVw("", 0, 0, 0, 0, 0, 0, 0, 0, 0);
		DadosTerritorioVw territorioNordeste = new DadosTerritorioVw("", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0, 0, 0.0, 0, 0.0, 0);
		
		DadosComunidadeVw comunidadeCentrooeste = new DadosComunidadeVw("", 0, 0, 0, 0, 0, 0, 0, 0, 0);
		DadosTerritorioVw territorioCentrooeste = new DadosTerritorioVw("", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0, 0, 0.0, 0, 0.0, 0);
		
		DadosComunidadeVw comunidadeSudeste = new DadosComunidadeVw("", 0, 0, 0, 0, 0, 0, 0, 0, 0);
		DadosTerritorioVw territorioSudeste = new DadosTerritorioVw("", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0, 0, 0.0, 0, 0.0, 0);
		
		DadosComunidadeVw comunidadeSul = new DadosComunidadeVw("", 0, 0, 0, 0, 0, 0, 0, 0, 0);
		DadosTerritorioVw territorioSul = new DadosTerritorioVw("", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0, 0, 0.0, 0, 0.0, 0);
		
		Map<String, Object> list = new HashMap<String, Object>();
		
		for (DadosComunidadeVw cmw : comunidadeVWRepository.findAll()) {
			
			if(cmw.getEstado() != null) {
				switch (cmw.getEstado().getRegiao()) {
				case NORTE:
					comunidadeNorte.setComunidades(comunidadeNorte.getComunidades() + cmw.getComunidades());
					comunidadeNorte.setComunidadesAssentamento(comunidadeNorte.getComunidadesAssentamento() + cmw.getComunidadesAssentamento());
					comunidadeNorte.setComunidadesCcdru(comunidadeNorte.getComunidadesCcdru() + cmw.getComunidadesCcdru());
					comunidadeNorte.setComunidadesCertificadas(comunidadeNorte.getComunidadesCertificadas() + cmw.getComunidadesCertificadas());
					comunidadeNorte.setComunidadesDecreto(comunidadeNorte.getComunidadesDecreto() + cmw.getComunidadesDecreto());
					comunidadeNorte.setComunidadesEdital(comunidadeNorte.getComunidadesEdital() + cmw.getComunidadesEdital());
					comunidadeNorte.setComunidadesPotaria(comunidadeNorte.getComunidadesPotaria() + cmw.getComunidadesPotaria());
					comunidadeNorte.setComunidadesProcesso(comunidadeNorte.getComunidadesProcesso() + cmw.getComunidadesProcesso());
					comunidadeNorte.setComunidadesTitulo(comunidadeNorte.getComunidadesTitulo() + cmw.getComunidadesTitulo());
					break;
					
				case NORDESTE:
					comunidadeNordeste.setComunidades(comunidadeNordeste.getComunidades() + cmw.getComunidades());
					comunidadeNordeste.setComunidadesAssentamento(comunidadeNordeste.getComunidadesAssentamento() + cmw.getComunidadesAssentamento());
					comunidadeNordeste.setComunidadesCcdru(comunidadeNordeste.getComunidadesCcdru() + cmw.getComunidadesCcdru());
					comunidadeNordeste.setComunidadesCertificadas(comunidadeNordeste.getComunidadesCertificadas() + cmw.getComunidadesCertificadas());
					comunidadeNordeste.setComunidadesDecreto(comunidadeNordeste.getComunidadesDecreto() + cmw.getComunidadesDecreto());
					comunidadeNordeste.setComunidadesEdital(comunidadeNordeste.getComunidadesEdital() + cmw.getComunidadesEdital());
					comunidadeNordeste.setComunidadesPotaria(comunidadeNordeste.getComunidadesPotaria() + cmw.getComunidadesPotaria());
					comunidadeNordeste.setComunidadesProcesso(comunidadeNordeste.getComunidadesProcesso() + cmw.getComunidadesProcesso());
					comunidadeNordeste.setComunidadesTitulo(comunidadeNordeste.getComunidadesTitulo() + cmw.getComunidadesTitulo());
					break;
					
				case CENTROOESTE:
					comunidadeCentrooeste.setComunidades(comunidadeCentrooeste.getComunidades() + cmw.getComunidades());
					comunidadeCentrooeste.setComunidadesAssentamento(comunidadeCentrooeste.getComunidadesAssentamento() + cmw.getComunidadesAssentamento());
					comunidadeCentrooeste.setComunidadesCcdru(comunidadeCentrooeste.getComunidadesCcdru() + cmw.getComunidadesCcdru());
					comunidadeCentrooeste.setComunidadesCertificadas(comunidadeCentrooeste.getComunidadesCertificadas() + cmw.getComunidadesCertificadas());
					comunidadeCentrooeste.setComunidadesDecreto(comunidadeCentrooeste.getComunidadesDecreto() + cmw.getComunidadesDecreto());
					comunidadeCentrooeste.setComunidadesEdital(comunidadeCentrooeste.getComunidadesEdital() + cmw.getComunidadesEdital());
					comunidadeCentrooeste.setComunidadesPotaria(comunidadeCentrooeste.getComunidadesPotaria() + cmw.getComunidadesPotaria());
					comunidadeCentrooeste.setComunidadesProcesso(comunidadeCentrooeste.getComunidadesProcesso() + cmw.getComunidadesProcesso());
					comunidadeCentrooeste.setComunidadesTitulo(comunidadeCentrooeste.getComunidadesTitulo() + cmw.getComunidadesTitulo());
					break;
					
				case SUDESTE:
					comunidadeSudeste.setComunidades(comunidadeSudeste.getComunidades() + cmw.getComunidades());
					comunidadeSudeste.setComunidadesAssentamento(comunidadeSudeste.getComunidadesAssentamento() + cmw.getComunidadesAssentamento());
					comunidadeSudeste.setComunidadesCcdru(comunidadeSudeste.getComunidadesCcdru() + cmw.getComunidadesCcdru());
					comunidadeSudeste.setComunidadesCertificadas(comunidadeSudeste.getComunidadesCertificadas() + cmw.getComunidadesCertificadas());
					comunidadeSudeste.setComunidadesDecreto(comunidadeSudeste.getComunidadesDecreto() + cmw.getComunidadesDecreto());
					comunidadeSudeste.setComunidadesEdital(comunidadeSudeste.getComunidadesEdital() + cmw.getComunidadesEdital());
					comunidadeSudeste.setComunidadesPotaria(comunidadeSudeste.getComunidadesPotaria() + cmw.getComunidadesPotaria());
					comunidadeSudeste.setComunidadesProcesso(comunidadeSudeste.getComunidadesProcesso() + cmw.getComunidadesProcesso());
					comunidadeSudeste.setComunidadesTitulo(comunidadeSudeste.getComunidadesTitulo() + cmw.getComunidadesTitulo());
					break;
		
				case SUL:
					comunidadeSul.setComunidades(comunidadeSul.getComunidades() + cmw.getComunidades());
					comunidadeSul.setComunidadesAssentamento(comunidadeSul.getComunidadesAssentamento() + cmw.getComunidadesAssentamento());
					comunidadeSul.setComunidadesCcdru(comunidadeSul.getComunidadesCcdru() + cmw.getComunidadesCcdru());
					comunidadeSul.setComunidadesCertificadas(comunidadeSul.getComunidadesCertificadas() + cmw.getComunidadesCertificadas());
					comunidadeSul.setComunidadesDecreto(comunidadeSul.getComunidadesDecreto() + cmw.getComunidadesDecreto());
					comunidadeSul.setComunidadesEdital(comunidadeSul.getComunidadesEdital() + cmw.getComunidadesEdital());
					comunidadeSul.setComunidadesPotaria(comunidadeSul.getComunidadesPotaria() + cmw.getComunidadesPotaria());
					comunidadeSul.setComunidadesProcesso(comunidadeSul.getComunidadesProcesso() + cmw.getComunidadesProcesso());
					comunidadeSul.setComunidadesTitulo(comunidadeSul.getComunidadesTitulo() + cmw.getComunidadesTitulo());
					break;
				default:
					break;
				}
			}
			
			comunidadeVw.setComunidades(comunidadeVw.getComunidades() + cmw.getComunidades());
			comunidadeVw.setComunidadesAssentamento(comunidadeVw.getComunidadesAssentamento() + cmw.getComunidadesAssentamento());
			comunidadeVw.setComunidadesCcdru(comunidadeVw.getComunidadesCcdru() + cmw.getComunidadesCcdru());
			comunidadeVw.setComunidadesCertificadas(comunidadeVw.getComunidadesCertificadas() + cmw.getComunidadesCertificadas());
			comunidadeVw.setComunidadesDecreto(comunidadeVw.getComunidadesDecreto() + cmw.getComunidadesDecreto());
			comunidadeVw.setComunidadesEdital(comunidadeVw.getComunidadesEdital() + cmw.getComunidadesEdital());
			comunidadeVw.setComunidadesPotaria(comunidadeVw.getComunidadesPotaria() + cmw.getComunidadesPotaria());
			comunidadeVw.setComunidadesProcesso(comunidadeVw.getComunidadesProcesso() + cmw.getComunidadesProcesso());
			comunidadeVw.setComunidadesTitulo(comunidadeVw.getComunidadesTitulo() + cmw.getComunidadesTitulo());
		}
		
		for (DadosTerritorioVw trw : territorioVWRepository.findAll()) {
			if(trw.getEstado() != null) {
				switch (trw.getEstado().getRegiao()) {
				case NORTE:
					territorioNorte.setTerritorios(territorioNorte.getTerritorios() + trw.getTerritorios());
					territorioNorte.setTerritoriosCcdru(territorioNorte.getTerritoriosCcdru() + trw.getTerritoriosCcdru());
					territorioNorte.setTerritoriosCertificadas(territorioNorte.getTerritoriosCertificadas() + trw.getTerritoriosCertificadas());
					territorioNorte.setTerritoriosDecreto(territorioNorte.getTerritoriosDecreto() + trw.getTerritoriosDecreto());
					territorioNorte.setTerritoriosEdital(territorioNorte.getTerritoriosEdital() + trw.getTerritoriosEdital());
					territorioNorte.setTerritoriosPotaria(territorioNorte.getTerritoriosPotaria() + trw.getTerritoriosPotaria());
					territorioNorte.setTerritoriosProcesso(territorioNorte.getTerritoriosProcesso() + trw.getTerritoriosProcesso());
					territorioNorte.setTerritoriosTitulo(territorioNorte.getTerritoriosTitulo() + trw.getTerritoriosTitulo());
					territorioNorte.setTerritorioAssentamento(territorioNorte.getTerritorioAssentamento() + trw.getTerritorioAssentamento());
					territorioNorte.setTerritorioFamilasAssentamenos(territorioNorte.getTerritorioFamilasAssentamenos() + trw.getTerritorioFamilasAssentamenos());
					territorioNorte.setTerritorioFamiliasEdital(territorioNorte.getTerritorioFamiliasEdital() + trw.getTerritorioFamiliasEdital());
					territorioNorte.setTerritorioFamiliasTituladas(territorioNorte.getTerritorioFamiliasTituladas() + trw.getTerritorioFamiliasTituladas());
					territorioNorte.setTerritorioHaAssentamenos(territorioNorte.getTerritorioHaAssentamenos() + trw.getTerritorioHaAssentamenos());
					territorioNorte.setTerritorioHaEdital(territorioNorte.getTerritorioHaEdital() + trw.getTerritorioHaEdital());
					territorioNorte.setTerritorioHaTitulados(territorioNorte.getTerritorioHaTitulados() + trw.getTerritorioHaTitulados());
					break;

				case NORDESTE:
					territorioNordeste.setTerritorios(territorioNordeste.getTerritorios() + trw.getTerritorios());
					territorioNordeste.setTerritoriosCcdru(territorioNordeste.getTerritoriosCcdru() + trw.getTerritoriosCcdru());
					territorioNordeste.setTerritoriosCertificadas(territorioNordeste.getTerritoriosCertificadas() + trw.getTerritoriosCertificadas());
					territorioNordeste.setTerritoriosDecreto(territorioNordeste.getTerritoriosDecreto() + trw.getTerritoriosDecreto());
					territorioNordeste.setTerritoriosEdital(territorioNordeste.getTerritoriosEdital() + trw.getTerritoriosEdital());
					territorioNordeste.setTerritoriosPotaria(territorioNordeste.getTerritoriosPotaria() + trw.getTerritoriosPotaria());
					territorioNordeste.setTerritoriosProcesso(territorioNordeste.getTerritoriosProcesso() + trw.getTerritoriosProcesso());
					territorioNordeste.setTerritoriosTitulo(territorioNordeste.getTerritoriosTitulo() + trw.getTerritoriosTitulo());
					territorioNordeste.setTerritorioAssentamento(territorioNordeste.getTerritorioAssentamento() + trw.getTerritorioAssentamento());
					territorioNordeste.setTerritorioFamilasAssentamenos(territorioNordeste.getTerritorioFamilasAssentamenos() + trw.getTerritorioFamilasAssentamenos());
					territorioNordeste.setTerritorioFamiliasEdital(territorioNordeste.getTerritorioFamiliasEdital() + trw.getTerritorioFamiliasEdital());
					territorioNordeste.setTerritorioFamiliasTituladas(territorioNordeste.getTerritorioFamiliasTituladas() + trw.getTerritorioFamiliasTituladas());
					territorioNordeste.setTerritorioHaAssentamenos(territorioNordeste.getTerritorioHaAssentamenos() + trw.getTerritorioHaAssentamenos());
					territorioNordeste.setTerritorioHaEdital(territorioNordeste.getTerritorioHaEdital() + trw.getTerritorioHaEdital());
					territorioNordeste.setTerritorioHaTitulados(territorioNordeste.getTerritorioHaTitulados() + trw.getTerritorioHaTitulados());
					break;
					
				case CENTROOESTE:
					territorioCentrooeste.setTerritorios(territorioCentrooeste.getTerritorios() + trw.getTerritorios());
					territorioCentrooeste.setTerritoriosCcdru(territorioCentrooeste.getTerritoriosCcdru() + trw.getTerritoriosCcdru());
					territorioCentrooeste.setTerritoriosCertificadas(territorioCentrooeste.getTerritoriosCertificadas() + trw.getTerritoriosCertificadas());
					territorioCentrooeste.setTerritoriosDecreto(territorioCentrooeste.getTerritoriosDecreto() + trw.getTerritoriosDecreto());
					territorioCentrooeste.setTerritoriosEdital(territorioCentrooeste.getTerritoriosEdital() + trw.getTerritoriosEdital());
					territorioCentrooeste.setTerritoriosPotaria(territorioCentrooeste.getTerritoriosPotaria() + trw.getTerritoriosPotaria());
					territorioCentrooeste.setTerritoriosProcesso(territorioCentrooeste.getTerritoriosProcesso() + trw.getTerritoriosProcesso());
					territorioCentrooeste.setTerritoriosTitulo(territorioCentrooeste.getTerritoriosTitulo() + trw.getTerritoriosTitulo());
					territorioCentrooeste.setTerritorioAssentamento(territorioCentrooeste.getTerritorioAssentamento() + trw.getTerritorioAssentamento());
					territorioCentrooeste.setTerritorioFamilasAssentamenos(territorioCentrooeste.getTerritorioFamilasAssentamenos() + trw.getTerritorioFamilasAssentamenos());
					territorioCentrooeste.setTerritorioFamiliasEdital(territorioCentrooeste.getTerritorioFamiliasEdital() + trw.getTerritorioFamiliasEdital());
					territorioCentrooeste.setTerritorioFamiliasTituladas(territorioCentrooeste.getTerritorioFamiliasTituladas() + trw.getTerritorioFamiliasTituladas());
					territorioCentrooeste.setTerritorioHaAssentamenos(territorioCentrooeste.getTerritorioHaAssentamenos() + trw.getTerritorioHaAssentamenos());
					territorioCentrooeste.setTerritorioHaEdital(territorioCentrooeste.getTerritorioHaEdital() + trw.getTerritorioHaEdital());
					territorioCentrooeste.setTerritorioHaTitulados(territorioCentrooeste.getTerritorioHaTitulados() + trw.getTerritorioHaTitulados());
					break;
					
				case SUDESTE:
					territorioSudeste.setTerritorios(territorioSudeste.getTerritorios() + trw.getTerritorios());
					territorioSudeste.setTerritoriosCcdru(territorioSudeste.getTerritoriosCcdru() + trw.getTerritoriosCcdru());
					territorioSudeste.setTerritoriosCertificadas(territorioSudeste.getTerritoriosCertificadas() + trw.getTerritoriosCertificadas());
					territorioSudeste.setTerritoriosDecreto(territorioSudeste.getTerritoriosDecreto() + trw.getTerritoriosDecreto());
					territorioSudeste.setTerritoriosEdital(territorioSudeste.getTerritoriosEdital() + trw.getTerritoriosEdital());
					territorioSudeste.setTerritoriosPotaria(territorioSudeste.getTerritoriosPotaria() + trw.getTerritoriosPotaria());
					territorioSudeste.setTerritoriosProcesso(territorioSudeste.getTerritoriosProcesso() + trw.getTerritoriosProcesso());
					territorioSudeste.setTerritoriosTitulo(territorioSudeste.getTerritoriosTitulo() + trw.getTerritoriosTitulo());
					territorioSudeste.setTerritorioAssentamento(territorioSudeste.getTerritorioAssentamento() + trw.getTerritorioAssentamento());
					territorioSudeste.setTerritorioFamilasAssentamenos(territorioSudeste.getTerritorioFamilasAssentamenos() + trw.getTerritorioFamilasAssentamenos());
					territorioSudeste.setTerritorioFamiliasEdital(territorioSudeste.getTerritorioFamiliasEdital() + trw.getTerritorioFamiliasEdital());
					territorioSudeste.setTerritorioFamiliasTituladas(territorioSudeste.getTerritorioFamiliasTituladas() + trw.getTerritorioFamiliasTituladas());
					territorioSudeste.setTerritorioHaAssentamenos(territorioSudeste.getTerritorioHaAssentamenos() + trw.getTerritorioHaAssentamenos());
					territorioSudeste.setTerritorioHaEdital(territorioSudeste.getTerritorioHaEdital() + trw.getTerritorioHaEdital());
					territorioSudeste.setTerritorioHaTitulados(territorioSudeste.getTerritorioHaTitulados() + trw.getTerritorioHaTitulados());
					break;
		
				case SUL:
					territorioSul.setTerritorios(territorioSul.getTerritorios() + trw.getTerritorios());
					territorioSul.setTerritoriosCcdru(territorioSul.getTerritoriosCcdru() + trw.getTerritoriosCcdru());
					territorioSul.setTerritoriosCertificadas(territorioSul.getTerritoriosCertificadas() + trw.getTerritoriosCertificadas());
					territorioSul.setTerritoriosDecreto(territorioSul.getTerritoriosDecreto() + trw.getTerritoriosDecreto());
					territorioSul.setTerritoriosEdital(territorioSul.getTerritoriosEdital() + trw.getTerritoriosEdital());
					territorioSul.setTerritoriosPotaria(territorioSul.getTerritoriosPotaria() + trw.getTerritoriosPotaria());
					territorioSul.setTerritoriosProcesso(territorioSul.getTerritoriosProcesso() + trw.getTerritoriosProcesso());
					territorioSul.setTerritoriosTitulo(territorioSul.getTerritoriosTitulo() + trw.getTerritoriosTitulo());
					territorioSul.setTerritorioAssentamento(territorioSul.getTerritorioAssentamento() + trw.getTerritorioAssentamento());
					territorioSul.setTerritorioFamilasAssentamenos(territorioSul.getTerritorioFamilasAssentamenos() + trw.getTerritorioFamilasAssentamenos());
					territorioSul.setTerritorioFamiliasEdital(territorioSul.getTerritorioFamiliasEdital() + trw.getTerritorioFamiliasEdital());
					territorioSul.setTerritorioFamiliasTituladas(territorioSul.getTerritorioFamiliasTituladas() + trw.getTerritorioFamiliasTituladas());
					territorioSul.setTerritorioHaAssentamenos(territorioSul.getTerritorioHaAssentamenos() + trw.getTerritorioHaAssentamenos());
					territorioSul.setTerritorioHaEdital(territorioSul.getTerritorioHaEdital() + trw.getTerritorioHaEdital());
					territorioSul.setTerritorioHaTitulados(territorioSul.getTerritorioHaTitulados() + trw.getTerritorioHaTitulados());
					break;
				default:
					break;
				}
			}
			
			territorioVw.setTerritorios(territorioVw.getTerritorios() + trw.getTerritorios());
			territorioVw.setTerritoriosCcdru(territorioVw.getTerritoriosCcdru() + trw.getTerritoriosCcdru());
			territorioVw.setTerritoriosCertificadas(territorioVw.getTerritoriosCertificadas() + trw.getTerritoriosCertificadas());
			territorioVw.setTerritoriosDecreto(territorioVw.getTerritoriosDecreto() + trw.getTerritoriosDecreto());
			territorioVw.setTerritoriosEdital(territorioVw.getTerritoriosEdital() + trw.getTerritoriosEdital());
			territorioVw.setTerritoriosPotaria(territorioVw.getTerritoriosPotaria() + trw.getTerritoriosPotaria());
			territorioVw.setTerritoriosProcesso(territorioVw.getTerritoriosProcesso() + trw.getTerritoriosProcesso());
			territorioVw.setTerritoriosTitulo(territorioVw.getTerritoriosTitulo() + trw.getTerritoriosTitulo());
			territorioVw.setTerritorioAssentamento(territorioVw.getTerritorioAssentamento() + trw.getTerritorioAssentamento());
			territorioVw.setTerritorioFamilasAssentamenos(territorioVw.getTerritorioFamilasAssentamenos() + trw.getTerritorioFamilasAssentamenos());
			territorioVw.setTerritorioFamiliasEdital(territorioVw.getTerritorioFamiliasEdital() + trw.getTerritorioFamiliasEdital());
			territorioVw.setTerritorioFamiliasTituladas(territorioVw.getTerritorioFamiliasTituladas() + trw.getTerritorioFamiliasTituladas());
			territorioVw.setTerritorioHaAssentamenos(territorioVw.getTerritorioHaAssentamenos() + trw.getTerritorioHaAssentamenos());
			territorioVw.setTerritorioHaEdital(territorioVw.getTerritorioHaEdital() + trw.getTerritorioHaEdital());
			territorioVw.setTerritorioHaTitulados(territorioVw.getTerritorioHaTitulados() + trw.getTerritorioHaTitulados());
		}
		
		list.put("comunidades", comunidadeVw);
		list.put("territorios", territorioVw);
		
		list.put("territoriosnorte", territorioNorte);
		list.put("territoriosnordeste", territorioNordeste);
		list.put("territorioscentrooeste", territorioCentrooeste);
		list.put("territoriossudeste", territorioSudeste);
		list.put("territoriossul", territorioSul);
		
		list.put("comunidadesnorte", comunidadeNorte);
		list.put("comunidadesnordeste", comunidadeNordeste);
		list.put("comunidadescentrooeste", comunidadeCentrooeste);
		list.put("comunidadessudeste", comunidadeSudeste);
		list.put("comunidadessul", comunidadeSul);
		
		return list;
	}

	public List<Comunidade> getComunidadesByUfCode(int codUf) {
		List<Comunidade> comunidades = new ArrayList<Comunidade>();
		
		municipioService.
		findByEstado(Estado.getEstadoByCodigoIbge(codUf).getSigla()).forEach(municipio -> {
			municipio.getComunidades().forEach(comunidade -> {
				if(!comunidades.contains(comunidade)) {
					comunidades.add(comunidade);
				}
			});
		});
		return comunidades;
	}
	
	public List<Comunidade> getComunidadesByUfCodeAndSearchText(int codUf, @Nullable String busca) {

		List<Comunidade> comunidades = new ArrayList<Comunidade>();
		
		String estado = Estado.getEstadoByCodigoIbge(codUf).getSigla();
		
		if(!StringUtils.isEmpty(busca) ) {
			comunidades = comunidadeRepository.findAll(ComunidadeSpec.getComunidadeByComunidadeOrTerritorioAndEstado(busca, estado));
		}else {
			comunidades = comunidadeRepository.findAll(ComunidadeSpec.getComunidadeByEstado(estado));
		}
		return comunidades;
	}
	
	public List<Comunidade> getComunidadesByNome(String nomeComunidade) {
		List<Comunidade> comunidades = new ArrayList<Comunidade>();
		
		if(!StringUtils.isEmpty(nomeComunidade) ) {
			comunidades = comunidadeRepository.findAll(ComunidadeSpec.getComunidadeByNomeTirandoAcento(nomeComunidade));
		}

		return comunidades;
	}
	
	public Page<Comunidade> findByCriteria(Pageable pageable, Sorting orderBy, List<Filter> filters) {
		
		Long id = 0L;
		String nome = "";

		for (Filter filter : filters) {
			switch (filter.getOperator()) {
				case IGUAL:
					switch (filter.getField()) {
						case "id":
							try {
								id = Long.parseLong((String) filter.getValue());
							} catch (Exception e) {
								// TODO: handle exception
							}
							
							break;
		
						case "nome":
							nome = (String)filter.getValue();
							break;
		
						default:
							break;
					}
	
					break;
	
				case CONTENDO_STR:
					switch (filter.getField()) {
						case "id":
							try {
								id = Long.parseLong((String) filter.getValue());
							} catch (Exception e) {
								// TODO: handle exception
							}
							break;
		
						case "nome":
							nome = (String)filter.getValue();
							break;
		
						default:
							break;
					}
					break;
				case CONTENDO:
					switch (filter.getField()) {
						case "id":
							try {
								id = Long.parseLong((String) filter.getValue());
							} catch (Exception e) {
								// TODO: handle exception
							}
							break;
		
						case "nome":
							nome = (String)filter.getValue();
							break;
			
						default:
							break;
					}
					break;
	
				default:
					break;
			}
		}
		
		Page<Comunidade> page = comunidadeRepository.findAll(
				ComunidadeSpec.getComunidadeById(id)
				.or(ComunidadeSpec.getComunidadeByNome(nome)), 
				pageable);
		return page;
	}
}
