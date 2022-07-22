package br.org.quilombola.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.org.quilombola.arquitetura.BaseEntity;
import br.org.quilombola.model.entity.jsonentity.ProcessoAdministrativoExtra;
import br.org.quilombola.model.entity.jsonentity.ProcessoJudicial;

public class DadosAdicionaisDTO extends BaseEntity {

	private static final long serialVersionUID = 7357413308783628123L;

	private Long id;

	private Boolean certificada = false;

	private Boolean portaria = false;

	private Boolean decreto = false;

	private Boolean assentamentos = false;

	private String longitudeWEcertificacao;

	private String latitudeSNcertificacao;

	private Boolean processosReconhecimento = false;

	private Boolean edital = false;

	private Long territorio;

	private Boolean concessao = false;

	private Boolean ccdru;

	private Boolean titulada;

	private Double areahaTerritorial;

	private String observacao;

	private Boolean copiaTituloArquivoAutor = false;

	private String publicacaoTerraQuilombo;

	private Boolean quilomboUrbano = false;

	private Boolean peqTeq = false;

	private Integer qtdFamiliasPeqTeq;

	private Double areahaPeqTeq;

	private String numeroPortariaPeqTeq;

	private Date dataPortariaPeqTeq;

	private Date dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq;

	private String cadastroIncra;

	private String cadastroSigef;

	private String cadastroCar;

	private String latitudeIbge;

	private String longitudeIbge;

	private String latitudeIncra;

	private String longitudeIncra;

	private String localizacaoIncra;

	private String codigoPoligono;

	private List<ProcessoAdministrativoExtraDTO> outrosProcessosAdministrativos = new ArrayList<>();

	private List<ProcessoJudicialDTO> processosJudiciais = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getCertificada() {
		return certificada;
	}

	public void setCertificada(Boolean certificada) {
		this.certificada = certificada;
	}

	public Boolean getPortaria() {
		return portaria;
	}

	public void setPortaria(Boolean portaria) {
		this.portaria = portaria;
	}

	public Boolean getDecreto() {
		return decreto;
	}

	public void setDecreto(Boolean decreto) {
		this.decreto = decreto;
	}

	public Boolean getAssentamentos() {
		return assentamentos;
	}

	public void setAssentamentos(Boolean assentamentos) {
		this.assentamentos = assentamentos;
	}

	public String getLongitudeWEcertificacao() {
		return longitudeWEcertificacao;
	}

	public void setLongitudeWEcertificacao(String longitudeWEcertificacao) {
		this.longitudeWEcertificacao = longitudeWEcertificacao;
	}

	public String getLatitudeSNcertificacao() {
		return latitudeSNcertificacao;
	}

	public void setLatitudeSNcertificacao(String latitudeSNcertificacao) {
		this.latitudeSNcertificacao = latitudeSNcertificacao;
	}

	public Boolean getProcessosReconhecimento() {
		return processosReconhecimento;
	}

	public void setProcessosReconhecimento(Boolean processosReconhecimento) {
		this.processosReconhecimento = processosReconhecimento;
	}

	public Boolean getEdital() {
		return edital;
	}

	public void setEdital(Boolean edital) {
		this.edital = edital;
	}

	public Long getTerritorio() {
		return territorio;
	}

	public void setTerritorio(Long territorio) {
		this.territorio = territorio;
	}

	public Boolean getConcessao() {
		return concessao;
	}

	public void setConcessao(Boolean concessao) {
		this.concessao = concessao;
	}

	public Boolean getCcdru() {
		return ccdru;
	}

	public void setCcdru(Boolean ccdru) {
		this.ccdru = ccdru;
	}

	public Boolean getTitulada() {
		return titulada;
	}

	public void setTitulada(Boolean titulada) {
		this.titulada = titulada;
	}

	public Double getAreahaTerritorial() {
		return areahaTerritorial;
	}

	public void setAreahaTerritorial(Double areahaTerritorial) {
		this.areahaTerritorial = areahaTerritorial;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getCopiaTituloArquivoAutor() {
		return copiaTituloArquivoAutor;
	}

	public void setCopiaTituloArquivoAutor(Boolean copiaTituloArquivoAutor) {
		this.copiaTituloArquivoAutor = copiaTituloArquivoAutor;
	}

	public String getPublicacaoTerraQuilombo() {
		return publicacaoTerraQuilombo;
	}

	public void setPublicacaoTerraQuilombo(String publicacaoTerraQuilombo) {
		this.publicacaoTerraQuilombo = publicacaoTerraQuilombo;
	}

	public Boolean getQuilomboUrbano() {
		return quilomboUrbano;
	}

	public void setQuilomboUrbano(Boolean quilomboUrbano) {
		this.quilomboUrbano = quilomboUrbano;
	}

	public Boolean getPeqTeq() {
		return peqTeq;
	}

	public void setPeqTeq(Boolean peqTeq) {
		this.peqTeq = peqTeq;
	}

	public Integer getQtdFamiliasPeqTeq() {
		return qtdFamiliasPeqTeq;
	}

	public void setQtdFamiliasPeqTeq(Integer qtdFamiliasPeqTeq) {
		this.qtdFamiliasPeqTeq = qtdFamiliasPeqTeq;
	}

	public Double getAreahaPeqTeq() {
		return areahaPeqTeq;
	}

	public void setAreahaPeqTeq(Double areahaPeqTeq) {
		this.areahaPeqTeq = areahaPeqTeq;
	}

	public String getNumeroPortariaPeqTeq() {
		return numeroPortariaPeqTeq;
	}

	public void setNumeroPortariaPeqTeq(String numeroPortariaPeqTeq) {
		this.numeroPortariaPeqTeq = numeroPortariaPeqTeq;
	}

	public Date getDataPortariaPeqTeq() {
		return dataPortariaPeqTeq;
	}

	public void setDataPortariaPeqTeq(Date dataPortariaPeqTeq) {
		this.dataPortariaPeqTeq = dataPortariaPeqTeq;
	}

	public Date getDataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq() {
		return dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq;
	}

	public void setDataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq(
			Date dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq) {
		this.dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq = dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq;
	}

	public String getCadastroIncra() {
		return cadastroIncra;
	}

	public void setCadastroIncra(String cadastroIncra) {
		this.cadastroIncra = cadastroIncra;
	}

	public String getCadastroSigef() {
		return cadastroSigef;
	}

	public void setCadastroSigef(String cadastroSigef) {
		this.cadastroSigef = cadastroSigef;
	}

	public String getCadastroCar() {
		return cadastroCar;
	}

	public void setCadastroCar(String cadastroCar) {
		this.cadastroCar = cadastroCar;
	}

	public String getLatitudeIbge() {
		return latitudeIbge;
	}

	public void setLatitudeIbge(String latitudeIbge) {
		this.latitudeIbge = latitudeIbge;
	}

	public String getLongitudeIbge() {
		return longitudeIbge;
	}

	public void setLongitudeIbge(String longitudeIbge) {
		this.longitudeIbge = longitudeIbge;
	}

	public String getLatitudeIncra() {
		return latitudeIncra;
	}

	public void setLatitudeIncra(String latitudeIncra) {
		this.latitudeIncra = latitudeIncra;
	}

	public String getLongitudeIncra() {
		return longitudeIncra;
	}

	public void setLongitudeIncra(String longitudeIncra) {
		this.longitudeIncra = longitudeIncra;
	}

	public String getLocalizacaoIncra() {
		return localizacaoIncra;
	}

	public void setLocalizacaoIncra(String localizacaoIncra) {
		this.localizacaoIncra = localizacaoIncra;
	}

	public String getCodigoPoligono() {
		return codigoPoligono;
	}

	public void setCodigoPoligono(String codigoPoligono) {
		this.codigoPoligono = codigoPoligono;
	}

	public List<ProcessoAdministrativoExtraDTO> getOutrosProcessosAdministrativos() {
		return outrosProcessosAdministrativos;
	}

	public void setOutrosProcessosAdministrativos(List<ProcessoAdministrativoExtraDTO> outrosProcessosAdministrativos) {
		this.outrosProcessosAdministrativos = outrosProcessosAdministrativos;
	}

	public List<ProcessoJudicialDTO> getProcessosJudiciais() {
		return processosJudiciais;
	}

	public void setProcessosJudiciais(List<ProcessoJudicialDTO> processosJudiciais) {
		this.processosJudiciais = processosJudiciais;
	}

	@Override
	public String toString() {
		return "DadosAdicionaisDTO [certificada=" + certificada + ", portaria=" + portaria + ", decreto=" + decreto
				+ ", assentamentos=" + assentamentos + ", longitudeWEcertificacao=" + longitudeWEcertificacao
				+ ", latitudeSNcertificacao=" + latitudeSNcertificacao + ", processosReconhecimento="
				+ processosReconhecimento + ", edital=" + edital + ", territorio=" + territorio + ", concessao="
				+ concessao + ", ccdru=" + ccdru + ", titulada=" + titulada + ", areahaTerritorial=" + areahaTerritorial
				+ ", observacao=" + observacao + ", copiaTituloArquivoAutor=" + copiaTituloArquivoAutor
				+ ", publicacaoTerraQuilombo=" + publicacaoTerraQuilombo + ", quilomboUrbano=" + quilomboUrbano
				+ ", peqTeq=" + peqTeq + ", qtdFamiliasPeqTeq=" + qtdFamiliasPeqTeq + ", areahaPeqTeq=" + areahaPeqTeq
				+ ", numeroPortariaPeqTeq=" + numeroPortariaPeqTeq + ", dataPortariaPeqTeq=" + dataPortariaPeqTeq
				+ ", dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq="
				+ dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq + ", cadastroIncra=" + cadastroIncra
				+ ", cadastroSigef=" + cadastroSigef + ", cadastroCar=" + cadastroCar + ", latitudeIbge=" + latitudeIbge
				+ ", longitudeIbge=" + longitudeIbge + ", latitudeIncra=" + latitudeIncra + ", longitudeIncra="
				+ longitudeIncra + ", localizacaoIncra=" + localizacaoIncra + ", codigoPoligono=" + codigoPoligono
				+ ", outrosProcessosAdministrativos=" + outrosProcessosAdministrativos + ", processosJudiciais="
				+ processosJudiciais + "]";
	}

}
