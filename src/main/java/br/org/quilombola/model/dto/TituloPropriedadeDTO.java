package br.org.quilombola.model.dto;

import java.math.BigDecimal;
import java.util.Date;


public class TituloPropriedadeDTO {

	private Long id;
	
	private BigDecimal areaHaTitulo;
	
	private Date dataTitulo;
	
	private String percentagemAreaTitulada;
	
	private Integer numeroFamiliaTitulos;

	private String observacaoAreaTitulo;
	
	private Long orgaoExpedidor;
	
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

	public Long getComunidade() {
        return comunidade;
    }

    public void setComunidade(Long comunudade) {
        this.comunidade = comunudade;
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

	public Long getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(Long orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
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
