package br.org.quilombola.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.*;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "certificado")
public class Certificado extends BaseEntity {

	private static final long serialVersionUID = 913505510646088518L;

	@Column(name = "id_quilombola")
	private String idQuilombola;

	@Column(name = "numero_processo_fcp")
	private String numeroProcessoFcp;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "data_abertura_processo_certificacao")
	private Date dataAberturaProcessoCertificado;

	@Column(name = "livro")
	private String livro;

	@Column(name = "folha")
	private String folha;

	@Column(name = "numero")
	private Integer numero;

	@Column(name = "numero_portaria")
	private String numeroPortaria;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "data_portaria")
	private Date dataPortaria;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "data_diario_oficial")
	private Date dataDiarioOficial;

	@Column(name = "diario_oficial")
	private String diarioOficial;

	@Column(name = "secao_do")
	private String secaoDO;

	@Column(name = "folha_do")
	private String folhaDO;

	@Column(name = "link_publicacao_do")
	private String linkPublicacaoDO;

	@Column(name = "link_publicacao_do2")
	private String linkPublicacaoDO2;

	@Column(name = "link_publicacao_do3")
	private String linkPublicacaoDO3;

	@Column(name = "retificacao_fcp_observacao", columnDefinition = "TEXT")
	private String retificacaoFcpObservacao;

	@JsonIgnoreProperties(
			value = {
					"certificados"
			},
			allowSetters = true
	)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "comunidade_id", nullable = false)
	private Comunidade comunidade;

	private String nomeDocumento;

	@JsonIgnore
	private String extensaoDocumento;

	@JsonIgnore
//	@Lob
	private byte[] binarioDocumento;

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

	public Certificado() {

	}

	public String getNumeroProcessoFcp() {
		return numeroProcessoFcp;
	}

	public void setNumeroProcessoFcp(String numeroProcessoFcp) {
		this.numeroProcessoFcp = numeroProcessoFcp;
	}

	public Date getDataAberturaProcessoCertificado() {
		return dataAberturaProcessoCertificado;
	}

	public void setDataAberturaProcessoCertificado(Date dataAberturaProcessoCertificado) {
		this.dataAberturaProcessoCertificado = dataAberturaProcessoCertificado;
	}

	public String getRetificacaoFcpObservacao() {
		return retificacaoFcpObservacao;
	}

	public void setRetificacaoFcpObservacao(String retificacaoFcpObservacao) {
		this.retificacaoFcpObservacao = retificacaoFcpObservacao;
	}

	public Comunidade getComunidade() {
		return comunidade;
	}

	public void setComunidade(Comunidade comunidade) {
		this.comunidade = comunidade;
	}

	public String getIdQuilombola() {
		return idQuilombola;
	}

	public void setIdQuilombola(String idQuilombola) {
		this.idQuilombola = idQuilombola;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public String getFolha() {
		return folha;
	}

	public void setFolha(String folha) {
		this.folha = folha;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNumeroPortaria() {
		return numeroPortaria;
	}

	public void setNumeroPortaria(String numeroPortaria) {
		this.numeroPortaria = numeroPortaria;
	}

	public Date getDataPortaria() {
		return dataPortaria;
	}

	public void setDataPortaria(Date dataPortaria) {
		this.dataPortaria = dataPortaria;
	}

	public Date getDataDiarioOficial() {
		return dataDiarioOficial;
	}

	public void setDataDiarioOficial(Date dataDiarioOficial) {
		this.dataDiarioOficial = dataDiarioOficial;
	}

	public String getDiarioOficial() {
		return diarioOficial;
	}

	public void setDiarioOficial(String diarioOficial) {
		this.diarioOficial = diarioOficial;
	}

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

	public String getLinkPublicacaoDO() {
		return linkPublicacaoDO;
	}

	public void setLinkPublicacaoDO(String linkPublicacaoDO) {
		this.linkPublicacaoDO = linkPublicacaoDO;
	}

	public String getLinkPublicacaoDO2() {
		return linkPublicacaoDO2;
	}

	public void setLinkPublicacaoDO2(String linkPublicacaoDO2) {
		this.linkPublicacaoDO2 = linkPublicacaoDO2;
	}

	public String getLinkPublicacaoDO3() {
		return linkPublicacaoDO3;
	}

	public void setLinkPublicacaoDO3(String linkPublicacaoDO3) {
		this.linkPublicacaoDO3 = linkPublicacaoDO3;
	}

}
