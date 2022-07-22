package br.org.quilombola.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.org.quilombola.arquitetura.BaseEntity;
import br.org.quilombola.model.entity.jsonentity.ProcessoAdministrativoExtra;
import br.org.quilombola.model.entity.jsonentity.ProcessoJudicial;

@Entity
@Table(name = "dados_adicionais_comunidade")
public class DadosAdicionaisComunidade extends BaseEntity {

	private static final long serialVersionUID = 7357413308783628123L;
	
	@Column(name = "certificada")
	private Boolean certificada = false;

	@Column(name = "portaria")
	private Boolean portaria = false;

	@Column(name = "decreto")
	private Boolean decreto = false;

	@Column(name = "assentamentos")
	private Boolean assentamentos = false;

	@Column(name = "longitude_w_e_certificacao")
	private String longitudeWEcertificacao;

	@Column(name = "latitude_s_n_certificacao")
	private String latitudeSNcertificacao;

	@Column(name = "processos_reconhecimento")
	private Boolean processosReconhecimento = false;

	@Column(name = "edital")
	private Boolean edital = false;

	@JsonIgnoreProperties(
			value = {
					"dadosAdicionais"
			},
			allowSetters = true
	)
	@JoinColumn(name = "comunidade_id", referencedColumnName = "id", nullable = true)
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	private Comunidade comunidade;

	@JsonIgnoreProperties(
			value = {
					"municipioList",
					"quilombos",
					"ccdrus",
					"decretosDesapropriatorios",
					"editaisComunicacao",
					"matriculas",
					"portariasReconhecimento",
					"processosAdministrativos",
					"tituloPropriedade",
					"assentamentos",
					"dadosAdicionais"
			},
			allowSetters = true
	)
	@JoinColumn(name = "territorio_id", referencedColumnName = "id", nullable = true)
	@OneToOne(optional = true)
	private Territorio territorio;

	@Column(name = "concessao")
	private Boolean concessao = false;

	@Column(name = "ccdru")
	private Boolean ccdru;

	@Column(name = "titulada")
	private Boolean titulada;

	@Column(name = "areaha_territorial")
	private Double areahaTerritorial;

	@Column(name = "observacao", columnDefinition = "TEXT")
	private String observacao;

	@Column(name = "copia_titulo_arquivo_autor")
	private Boolean copiaTituloArquivoAutor = false;

	@Column(name = "publicacao_terra_quilombo")
	private String publicacaoTerraQuilombo;

	@Column(name = "quilombo_urbano")
	private Boolean quilomboUrbano = false;

	@Column(name = "peq_teq")
	private Boolean peqTeq = false;

	@Column(name = "familias_peq_teq")
	private Integer qtdFamiliasPeqTeq;

	@Column(name = "areaha_peq_teq")
	private Double areahaPeqTeq;

	@Column(name = "numero_portaria_peq_teq")
	private String numeroPortariaPeqTeq;

	@Column(name = "data_portaria_peq_teq")
	private Date dataPortariaPeqTeq;

	@Column(name = "dou_doe_portaria_peq_teq")
	private Date dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq;

	@Column(name = "cadatro_incra")
	private String cadastroIncra;

	@Column(name = "sigef")
	private String cadastroSigef;

	@Column(name = "car")
	private String cadastroCar;

	@Column(name = "latitude_ibge")
	private String latitudeIbge;

	@Column(name = "longitude_ibge")
	private String longitudeIbge;

	@Column(name = "latitude_incra")
	private String latitudeIncra;

	@Column(name = "longitude_incra")
	private String longitudeIncra;

	@Column(name = "localizacao_incra")
	private String localizacaoIncra;

	@Column(name = "codigo_poligono")
	private String codigoPoligono;

	@Column(name = "outro_processo_administrativo")
	private String outrosProcessosAdministrativos;

	@Column(name = "processo_judicial")
	private String processosJudiciais;

	public String getCodigoPoligono() {
		return codigoPoligono;
	}

	public void setCodigoPoligono(String codigoPoligono) {
		this.codigoPoligono = codigoPoligono;
	}

	private String nomeDocumento;

	@JsonIgnore
	private String extensaoDocumento;

	@JsonIgnore
	private byte[] binarioDocumento;

	public Boolean getCertificada() {
		return certificada;
	}

	public void setCertificada(Boolean certificada) {
		this.certificada = certificada;
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

	public Comunidade getComunidade() {
		return comunidade;
	}

	public void setComunidade(Comunidade comunidade) {
		this.comunidade = comunidade;
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

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public String getExtensaoDocumento() {
		return extensaoDocumento;
	}

	public void setExtensaoDocumento(String extensaoDocumento) {
		this.extensaoDocumento = extensaoDocumento;
	}

	public byte[] getBinarioDocumento() {
		return binarioDocumento;
	}

	public void setBinarioDocumento(byte[] binarioDocumento) {
		this.binarioDocumento = binarioDocumento;
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

	public Territorio getTerritorio() {
		return territorio;
	}

	public void setTerritorio(Territorio territorio) {
		this.territorio = territorio;
	}

	public List<ProcessoAdministrativoExtra> getOutrosProcessosAdministrativos() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProcessoAdministrativoExtra> list = new ArrayList<>();
		
		if(!StringUtils.isEmpty(this.outrosProcessosAdministrativos)) {
			try {
				list = objectMapper.readValue(this.outrosProcessosAdministrativos, new TypeReference<List<ProcessoAdministrativoExtra>>(){});
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public void setOutrosProcessoasAdministrativos(List<ProcessoAdministrativoExtra> outrosProcessosAdministrativos) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			this.outrosProcessosAdministrativos = objectMapper.writeValueAsString(outrosProcessosAdministrativos);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public List<ProcessoJudicial> getProcessosJudiciais() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProcessoJudicial> list = new ArrayList<>();
		
		if(!StringUtils.isEmpty(this.processosJudiciais)) {
			try {
				list = objectMapper.readValue(this.processosJudiciais, new TypeReference<List<ProcessoJudicial>>(){});
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void setProcessosJudiciais(List<ProcessoJudicial> processosJudiciais) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			this.processosJudiciais = objectMapper.writeValueAsString(processosJudiciais);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
