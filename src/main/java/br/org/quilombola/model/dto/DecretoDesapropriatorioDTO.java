package br.org.quilombola.model.dto;

import java.util.Date;

public class DecretoDesapropriatorioDTO {

	private Long id;
	
	private String numeroDecretoDesapropriacao;
	
	private Date dataDecretoDeaproriacaoa;
	
	private Date dataDecretoDesapropriacaoDiarioOficialUniao;
	
    private Long comunidade;
    
    private String linkPublicacao_1;
	
	private String linkPublicacao_2;
	
	private String linkPublicacao_3;

	private String secaoDO;
	
	private String folhaDO;	
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroDecretoDesapropriacao() {
		return numeroDecretoDesapropriacao;
	}

	public void setNumeroDecretoDesapropriacao(String numeroDecretoDesapropriacao) {
		this.numeroDecretoDesapropriacao = numeroDecretoDesapropriacao;
	}

	public Date getDataDecretoDeaproriacaoa() {
		return dataDecretoDeaproriacaoa;
	}

	public void setDataDecretoDeaproriacaoa(Date dataDecretoDeaproriacaoa) {
		this.dataDecretoDeaproriacaoa = dataDecretoDeaproriacaoa;
	}

	public Date getDataDecretoDesapropriacaoDiarioOficialUniao() {
		return dataDecretoDesapropriacaoDiarioOficialUniao;
	}

	public void setDataDecretoDesapropriacaoDiarioOficialUniao(Date dataDecretoDesapropriacaoDiarioOficialUniao) {
		this.dataDecretoDesapropriacaoDiarioOficialUniao = dataDecretoDesapropriacaoDiarioOficialUniao;
	}

	public Long getComunidade() {
		return comunidade;
	}
	
	public void setComunidade(Long comunidade) {
		this.comunidade = comunidade;
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

}
