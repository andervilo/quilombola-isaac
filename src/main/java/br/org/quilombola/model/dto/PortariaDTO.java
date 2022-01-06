package br.org.quilombola.model.dto;

import java.util.Date;

public class PortariaDTO {

	private Long id;
	
	private String numeroDecretoTeq;

	private Date dataDecretoTeq;

	private Date diarioOficialUniaoTeq;
	
	private Long territorio;
	
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

	public Long getTerritorio() {
        return territorio;
    }

    public void setTerritorio(Long territorio) {
        this.territorio = territorio;
    }

	public String getNumeroDecretoTeq() {
		return numeroDecretoTeq;
	}

	public void setNumeroDecretoTeq(String numeroDecretoTeq) {
		this.numeroDecretoTeq = numeroDecretoTeq;
	}

	public Date getDataDecretoTeq() {
		return dataDecretoTeq;
	}

	public void setDataDecretoTeq(Date dataDecretoTeq) {
		this.dataDecretoTeq = dataDecretoTeq;
	}

	public Date getDiarioOficialUniaoTeq() {
		return diarioOficialUniaoTeq;
	}

	public void setDiarioOficialUniaoTeq(Date diarioOficialUniaoTeq) {
		this.diarioOficialUniaoTeq = diarioOficialUniaoTeq;
	}


}
