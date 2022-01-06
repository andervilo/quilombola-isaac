package br.org.quilombola.model.dto;

import java.math.BigDecimal;
import java.util.Date;


public class EditalComunicacaoDTO {

	private Long id;	
	
	private String tipoEdital;
	
	private Date dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital;
	
	private Date dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital;
	
	private Integer numeroFamilias;
	
	private BigDecimal areaHaEdital;
	
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

    public void setComunidade(Long comunidade) {
        this.comunidade = comunidade;
    }

	public String getTipoEdital() {
		return tipoEdital;
	}

	public void setTipoEdital(String tipoEdital) {
		this.tipoEdital = tipoEdital;
	}

	public Date getDataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital() {
		return dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital;
	}

	public void setDataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital(
			Date dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital) {
		this.dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital = dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital;
	}

	public Date getDataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital() {
		return dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital;
	}

	public void setDataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital(
			Date dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital) {
		this.dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital = dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital;
	}

	public Integer getNumeroFamilias() {
		return numeroFamilias;
	}

	public void setNumeroFamilias(Integer numeroFamilias) {
		this.numeroFamilias = numeroFamilias;
	}

	public BigDecimal getAreaHaEdital() {
		return areaHaEdital;
	}

	public void setAreaHaEdital(BigDecimal areaHaEdital) {
		this.areaHaEdital = areaHaEdital;
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
