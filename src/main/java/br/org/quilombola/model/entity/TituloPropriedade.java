package br.org.quilombola.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Type;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "titulo_propriedade")
public class TituloPropriedade extends BaseEntity {

	private static final long serialVersionUID = 6753282366076913964L;

	@Column(name = "areaha_titulo", precision = 19, scale = 4)
	@Type(type = "big_decimal")
	private BigDecimal areaHaTitulo;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "data_titulo")
	private Date dataTitulo;

	@Column(name = "numero_familia_titulos")
	private Integer numeroFamiliaTitulos;

	@Column(name = "observacao_area_titulo", columnDefinition = "TEXT")
	private String observacaoAreaTitulo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_orgao_expedidor", referencedColumnName = "id")
	private Orgao orgaoExpedidor;

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
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "territorio_id", nullable = false)
	private Territorio territorio;

	private String nomeDocumento;

	@JsonIgnore
	private String extensaoDocumento;

	@JsonIgnore
	private byte[] binarioDocumento;

	@Column(name = "link_publicacao_1")
	private String linkPublicacao_1;

	@Column(name = "link_publicacao_2")
	private String linkPublicacao_2;

	@Column(name = "link_publicacao_3")
	private String linkPublicacao_3;

	@Column(name = "secao_do")
	private String secaoDO;

	@Column(name = "folha_do")
	private String folhaDO;

	@Column(name = "area_territorio", precision = 19, scale = 4)
	@Type(type = "big_decimal")
	private BigDecimal areaTerritorio;

	@Column(name = "data_portaria_destinacao")
	private String dataPortariaDestinacao;

	@Column(name = "portaria_destinacao_dou")
	private String portariaDestinacaoDou;

	@Column(name = "cadastro_incra")
	private String cadastroIncra;

	@Column(name = "percentagem_area_titulada")
	private String percentagemAreaTitulada;

	@Column(name = "numero_processo")
	private String numeroProcesso;

	@Column(name = "numero_portaria_destinacao")
	private String numeroPortariaDestinacao;

	public String getSecaoDO() {
		return secaoDO;
	}

	public void setSecaoDO(String secaoDO) {
		this.secaoDO = secaoDO;
	}

	public String getFolhaDO() {
		return folhaDO;
	}

	public void setFolhaDO(String folhaDO) {
		this.folhaDO = folhaDO;
	}

	public String getLinkPublicacao_1() {
		return linkPublicacao_1;
	}

	public void setLinkPublicacao_1(String linkPublicacao_1) {
		this.linkPublicacao_1 = linkPublicacao_1;
	}

	public String getLinkPublicacao_2() {
		return linkPublicacao_2;
	}

	public void setLinkPublicacao_2(String linkPublicacao_2) {
		this.linkPublicacao_2 = linkPublicacao_2;
	}

	public String getLinkPublicacao_3() {
		return linkPublicacao_3;
	}

	public void setLinkPublicacao_3(String linkPublicacao_3) {
		this.linkPublicacao_3 = linkPublicacao_3;
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

	public Territorio getTerritorio() {
		return territorio;
	}

	public void setTerritorio(Territorio territorio) {
		this.territorio = territorio;
	}

	public BigDecimal getAreaHaTitulo() {
		return areaHaTitulo;
	}

	public void setAreaHaTitulo(BigDecimal areaHaTitulo) {
		this.areaHaTitulo = areaHaTitulo;
	}

	public Date getDataTitulo() {
		return dataTitulo;
	}

	public void setDataTitulo(Date dataTitulo) {
		this.dataTitulo = dataTitulo;
	}

	public String getPercentagemAreaTitulada() {
		return percentagemAreaTitulada;
	}

	public void setPercentagemAreaTitulada(String percentagemAreaTitulada) {
		this.percentagemAreaTitulada = percentagemAreaTitulada;
	}

	public Integer getNumeroFamiliaTitulos() {
		return numeroFamiliaTitulos;
	}

	public void setNumeroFamiliaTitulos(Integer numeroFamiliaTitulos) {
		this.numeroFamiliaTitulos = numeroFamiliaTitulos;
	}

	public String getObservacaoAreaTitulo() {
		return observacaoAreaTitulo;
	}

	public void setObservacaoAreaTitulo(String observacaoAreaTitulo) {
		this.observacaoAreaTitulo = observacaoAreaTitulo;
	}

	public Orgao getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(Orgao orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public BigDecimal getAreaTerritorio() {
		return areaTerritorio;
	}

	public void setAreaTerritorio(BigDecimal areaTerritorio) {
		this.areaTerritorio = areaTerritorio;
	}

	public String getDataPortariaDestinacao() {
		return dataPortariaDestinacao;
	}

	public void setDataPortariaDestinacao(String dataPortariaDestinacao) {
		this.dataPortariaDestinacao = dataPortariaDestinacao;
	}

	public String getPortariaDestinacaoDou() {
		return portariaDestinacaoDou;
	}

	public void setPortariaDestinacaoDou(String portariaDestinacaoDou) {
		this.portariaDestinacaoDou = portariaDestinacaoDou;
	}

	public String getCadastroIncra() {
		return cadastroIncra;
	}

	public void setCadastroIncra(String cadastroIncra) {
		this.cadastroIncra = cadastroIncra;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public String getNumeroPortariaDestinacao() {
		return numeroPortariaDestinacao;
	}

	public void setNumeroPortariaDestinacao(String numeroPortariaDestinacao) {
		this.numeroPortariaDestinacao = numeroPortariaDestinacao;
	}

}
