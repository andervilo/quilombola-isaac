package br.org.quilombola.model.entity.relatorios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "relatorio_comunidade")
@JsonPropertyOrder({ "id", "comunidade",
		"municipios", "codigo_ibge", "estado", "superintendencia_codigo", "superintendencia_nome", "regiao",
		"amazonia_legal", "certificada", "numero_processo_fcp", "numero_processo_fcp2",
		"data_abertura_processo_certificacao", "livro", "numero", "numero_portaria", "data_portaria",
		"secoes_diario_oficial", "folhas_diario_oficial", "link1", "link2", "retificacao_fcp_observacao",
		"observacao" })
public class RelatorioComunidade extends BaseEntity {
	private static final long serialVersionUID = -7916040985569480804L;

	@Column(name = "comunidade")
	private String comunidade;

	@Column(name = "municipios")
	private String municipios;

	@Column(name = "codigo_ibge")
	private Integer codigoIbge;

	@Column(name = "estado")
	private String estado;

	@Column(name = "superintendencia_codigo")
	private Integer superintendenciaCodigo;

	@Column(name = "superintendencia_nome")
	private String superintendenciaNome;

	@Column(name = "regiao")
	private String regiao;

	@Column(name = "amazonia_legal")
	private String amazoniaLegal;

	@Column(name = "certificada")
	private String certificada;

	@Column(name = "numero_processo_fcp")
	private String numeroProcessoFcp;

	@Column(name = "numero_processo_fcp2")
	private String numeroProcessoFcp2;

	@Column(name = "data_abertura_processo_certificacao")
	private String dataAberturaProcessoCertificacao;

	@Column(name = "livro")
	private String livro;

	@Column(name = "numero")
	private String numero;

	@Column(name = "numero_portaria")
	private String numeroPortaria;

	@Column(name = "data_portaria")
	private String dataPortaria;

	@Column(name = "secoes_diario_oficial")
	private String secoesDiarioOficial;

	@Column(name = "folhas_diario_oficial")
	private String folhasDiarioOficial;

	@Column(name = "link1")
	private String link1;

	@Column(name = "link2")
	private String link2;

	@Column(name = "retificacao_fcp_observacao")
	private String retificacaoFcpObservacao;

	@Column(name = "observacao")
	private String observacao;

	public RelatorioComunidade() {
	}

	public RelatorioComunidade(String comunidade, String municipios, Integer codigoIbge, String estado,
			Integer superintendenciaCodigo, String superintendenciaNome, String regiao, String amazoniaLegal,
			String certificada, String numeroProcessoFcp, String numeroProcessoFcp2,
			String dataAberturaProcessoCertificacao, String livro, String numero, String numeroPortaria,
			String dataPortaria, String secoesDiarioOficial, String folhasDiarioOficial, String link1, String link2,
			String retificacaoFcpObservacao, String observacao) {
		super();
		this.comunidade = comunidade;
		this.municipios = municipios;
		this.codigoIbge = codigoIbge;
		this.estado = estado;
		this.superintendenciaCodigo = superintendenciaCodigo;
		this.superintendenciaNome = superintendenciaNome;
		this.regiao = regiao;
		this.amazoniaLegal = amazoniaLegal;
		this.certificada = certificada;
		this.numeroProcessoFcp = numeroProcessoFcp;
		this.numeroProcessoFcp2 = numeroProcessoFcp2;
		this.dataAberturaProcessoCertificacao = dataAberturaProcessoCertificacao;
		this.livro = livro;
		this.numero = numero;
		this.numeroPortaria = numeroPortaria;
		this.dataPortaria = dataPortaria;
		this.secoesDiarioOficial = secoesDiarioOficial;
		this.folhasDiarioOficial = folhasDiarioOficial;
		this.link1 = link1;
		this.link2 = link2;
		this.retificacaoFcpObservacao = retificacaoFcpObservacao;
		this.observacao = observacao;
	}

	public String getComunidade() {
		return comunidade;
	}

	public void setComunidade(String comunidade) {
		this.comunidade = comunidade;
	}

	public String getMunicipios() {
		return municipios;
	}

	public void setMunicipios(String municipios) {
		this.municipios = municipios;
	}

	public Integer getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getSuperintendenciaCodigo() {
		return superintendenciaCodigo;
	}

	public void setSuperintendenciaCodigo(Integer superintendenciaCodigo) {
		this.superintendenciaCodigo = superintendenciaCodigo;
	}

	public String getSuperintendenciaNome() {
		return superintendenciaNome;
	}

	public void setSuperintendenciaNome(String superintendenciaNome) {
		this.superintendenciaNome = superintendenciaNome;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getAmazoniaLegal() {
		return amazoniaLegal;
	}

	public void setAmazoniaLegal(String amazoniaLegal) {
		this.amazoniaLegal = amazoniaLegal;
	}

	public String getCertificada() {
		return certificada;
	}

	public void setCertificada(String certificada) {
		this.certificada = certificada;
	}

	public String getNumeroProcessoFcp() {
		return numeroProcessoFcp;
	}

	public void setNumeroProcessoFcp(String numeroProcessoFcp) {
		this.numeroProcessoFcp = numeroProcessoFcp;
	}

	public String getNumeroProcessoFcp2() {
		return numeroProcessoFcp2;
	}

	public void setNumeroProcessoFcp2(String numeroProcessoFcp2) {
		this.numeroProcessoFcp2 = numeroProcessoFcp2;
	}

	public String getDataAberturaProcessoCertificacao() {
		return dataAberturaProcessoCertificacao;
	}

	public void setDataAberturaProcessoCertificacao(String dataAberturaProcessoCertificacao) {
		this.dataAberturaProcessoCertificacao = dataAberturaProcessoCertificacao;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumeroPortaria() {
		return numeroPortaria;
	}

	public void setNumeroPortaria(String numeroPortaria) {
		this.numeroPortaria = numeroPortaria;
	}

	public String getDataPortaria() {
		return dataPortaria;
	}

	public void setDataPortaria(String dataPortaria) {
		this.dataPortaria = dataPortaria;
	}

	public String getSecoesDiarioOficial() {
		return secoesDiarioOficial;
	}

	public void setSecoesDiarioOficial(String secoesDiarioOficial) {
		this.secoesDiarioOficial = secoesDiarioOficial;
	}

	public String getFolhasDiarioOficial() {
		return folhasDiarioOficial;
	}

	public void setFolhasDiarioOficial(String folhasDiarioOficial) {
		this.folhasDiarioOficial = folhasDiarioOficial;
	}

	public String getLink1() {
		return link1;
	}

	public void setLink1(String link1) {
		this.link1 = link1;
	}

	public String getLink2() {
		return link2;
	}

	public void setLink2(String link2) {
		this.link2 = link2;
	}

	public String getRetificacaoFcpObservacao() {
		return retificacaoFcpObservacao;
	}

	public void setRetificacaoFcpObservacao(String retificacaoFcpObservacao) {
		this.retificacaoFcpObservacao = retificacaoFcpObservacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
