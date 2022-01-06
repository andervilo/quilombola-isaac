package br.org.quilombola.model.dto;

import br.org.quilombola.arquitetura.enums.Estado;

public class MunicipioDTO {

	private Long id;

	private String nome;

	private Integer codigoIbge;

	private Estado estado;

	private Boolean amazoniaLegal;

	public MunicipioDTO() {

	}

	public MunicipioDTO(Long id, String nome, Integer codigoIbge, Estado estado, Boolean amazoniaLegal) {
		super();
		this.id = id;
		this.nome = nome;
		this.codigoIbge = codigoIbge;
		this.estado = estado;
		this.amazoniaLegal = amazoniaLegal;
	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Boolean getAmazoniaLegal() {
		return amazoniaLegal;
	}

	public void setAmazoniaLegal(Boolean amazoniaLegal) {
		this.amazoniaLegal = amazoniaLegal;
	}

}
