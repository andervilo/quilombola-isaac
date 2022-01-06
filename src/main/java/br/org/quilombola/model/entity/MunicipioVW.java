package br.org.quilombola.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.org.quilombola.arquitetura.BaseEntity;
import br.org.quilombola.arquitetura.enums.Estado;

@Entity
@Table(name = "vw_municipios")
public class MunicipioVW extends BaseEntity{

	private static final long serialVersionUID = 8609380594975131746L;
	
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "codigo_ibge")
	private Integer codigoIbge;
	
    @Column(name = "estado")
	private String estado;
	
	@Column(name = "amazonia_legal") 
	private Boolean amazoniaLegal;

	MunicipioVW() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}
	
	public Estado getEstado() {
		return estado == null ? null : Estado.valueOf(estado);
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getAmazoniaLegal() {
		return amazoniaLegal;
	}

	public void setAmazoniaLegal(Boolean amazoniaLegal) {
		this.amazoniaLegal = amazoniaLegal;
	}
		
}
