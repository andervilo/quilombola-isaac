package br.org.quilombola.model.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.org.quilombola.model.entity.Certificado;
import br.org.quilombola.model.entity.Comunidade;

public class CertificadoDTO {

	private Long id;

	private String idQuilombola;

	@NotEmpty(message = "Número Processo FCP é um campo obrigatório!")
	private String numeroProcessoFcp;

	private Date dataAberturaProcessoCertificado;

	private String livro;

	private String folha;

	private Integer numero;

	private String numeroPortaria;

	private Date dataPortaria;

	private Date dataDiarioOficial;

	private String diarioOficial;

	private String secaoDO;

	private String folhaDO;

	private String linkPublicacaoDO;

	private String linkPublicacaoDO2;

	private String linkPublicacaoDO3;

	private String retificacaoFcpObservacao;

	@NotNull(message = "Comunidade é um campo obrigatório!")
	private Long comunidade;
	
	public Certificado toCertificado() {
		Certificado certificado =  new Certificado();
		certificado.setId(id);
		certificado.setIdQuilombola(idQuilombola);
		certificado.setNumeroProcessoFcp(numeroProcessoFcp);
		certificado.setDataAberturaProcessoCertificado(dataAberturaProcessoCertificado);
		certificado.setLivro(livro);
		certificado.setFolha(folha);
		certificado.setNumero(numero);
		certificado.setNumeroPortaria(numeroPortaria);
		certificado.setDataPortaria(dataPortaria);
		certificado.setDataDiarioOficial(dataDiarioOficial);
		certificado.setDiarioOficial(diarioOficial);
		certificado.setSecaoDO(secaoDO);
		certificado.setFolhaDO(folhaDO);
		certificado.setLinkPublicacaoDO(linkPublicacaoDO);
		certificado.setLinkPublicacaoDO2(linkPublicacaoDO2);
		certificado.setLinkPublicacaoDO3(linkPublicacaoDO3);
		certificado.setRetificacaoFcpObservacao(retificacaoFcpObservacao);
		
		Comunidade comunidadeObj = new Comunidade();
		comunidadeObj.setId(comunidade);
		
		certificado.setComunidade(comunidadeObj);
		
		
		return certificado;
	}
	
	public void fromCertificado(Certificado certificado) {
		this.id = certificado.getId();
		this.idQuilombola = certificado.getIdQuilombola();
		this.numeroProcessoFcp = certificado.getNumeroProcessoFcp();
		this.dataAberturaProcessoCertificado = certificado.getDataAberturaProcessoCertificado();
		this.livro = certificado.getLivro();
		this.folha = certificado.getFolha();
		this.numero = certificado.getNumero();
		this.numeroPortaria = certificado.getNumeroPortaria();
		this.dataPortaria = certificado.getDataPortaria();
		this.dataDiarioOficial = certificado.getDataDiarioOficial();
		this.diarioOficial = certificado.getDiarioOficial();
		this.secaoDO = certificado.getSecaoDO();
		this.folhaDO = certificado.getFolhaDO();
		this.linkPublicacaoDO = certificado.getLinkPublicacaoDO();
		this.linkPublicacaoDO2 = certificado.getLinkPublicacaoDO2();
		this.linkPublicacaoDO3 = certificado.getLinkPublicacaoDO3();
		this.retificacaoFcpObservacao = certificado.getRetificacaoFcpObservacao();
		this.comunidade = certificado.getComunidade().getId();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdQuilombola() {
		return idQuilombola;
	}

	public void setIdQuilombola(String idQuilombola) {
		this.idQuilombola = idQuilombola;
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

	public String getRetificacaoFcpObservacao() {
		return retificacaoFcpObservacao;
	}

	public void setRetificacaoFcpObservacao(String retificacaoFcpObservacao) {
		this.retificacaoFcpObservacao = retificacaoFcpObservacao;
	}

	public Long getComunidade() {
		return comunidade;
	}

	public void setComunidade(Long comunidade) {
		this.comunidade = comunidade;
	}

}
