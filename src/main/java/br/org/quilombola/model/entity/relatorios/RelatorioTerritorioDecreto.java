package br.org.quilombola.model.entity.relatorios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.org.quilombola.arquitetura.BaseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "id_territorio", "territorio", "municipio", "estado", "numero_decreto_desapropriacao",
		"data_decreto_deaproriacaoa", "decreto_desapropriacao_dou", "secao_do", "folha_do", "link_publicacao_1",
		"link_publicacao_2", "link_publicacao_3", "link_publicacao_4", "link_publicacao_5" })

@Entity
@Table(name = "relatorio_decreto")
public class RelatorioTerritorioDecreto extends BaseEntity {

	private static final long serialVersionUID = 5001082207844429847L;

	@Column(name = "id_territorio")
	private Integer idTerritorio;

	@Column(name = "territorio")
	private String territorio;

	@Column(name = "municipio")
	private String municipio;

	@Column(name = "estado")
	private String estado;

	@Column(name = "numero_decreto_desapropriacao")
	private String numeroDecretoDesapropriacao;

	@Column(name = "data_decreto_deaproriacaoa")
	private String dataDecretoDeaproriacaoa;

	@Column(name = "decreto_desapropriacao_dou")
	private String decretoDesapropriacaoDou;

	@Column(name = "secao_do")
	private String secaoDo;

	@Column(name = "folha_do")
	private String folhaDo;

	@Column(name = "link_publicacao_1")
	private String linkPublicacao1;

	@Column(name = "link_publicacao_2")
	private String linkPublicacao2;

	@Column(name = "link_publicacao_3")
	private String linkPublicacao3;

	@Column(name = "link_publicacao_4")
	private String linkPublicacao4;

	@Column(name = "link_publicacao_5")
	private String linkPublicacao5;

	public RelatorioTerritorioDecreto() {
	}

	public RelatorioTerritorioDecreto(Integer idTerritorio, String territorio, String municipio, String estado,
			String numeroDecretoDesapropriacao, String dataDecretoDeaproriacaoa, String decretoDesapropriacaoDou,
			String secaoDo, String folhaDo, String linkPublicacao1, String linkPublicacao2, String linkPublicacao3,
			String linkPublicacao4, String linkPublicacao5) {
		super();
		this.idTerritorio = idTerritorio;
		this.territorio = territorio;
		this.municipio = municipio;
		this.estado = estado;
		this.numeroDecretoDesapropriacao = numeroDecretoDesapropriacao;
		this.dataDecretoDeaproriacaoa = dataDecretoDeaproriacaoa;
		this.decretoDesapropriacaoDou = decretoDesapropriacaoDou;
		this.secaoDo = secaoDo;
		this.folhaDo = folhaDo;
		this.linkPublicacao1 = linkPublicacao1;
		this.linkPublicacao2 = linkPublicacao2;
		this.linkPublicacao3 = linkPublicacao3;
		this.linkPublicacao4 = linkPublicacao4;
		this.linkPublicacao5 = linkPublicacao5;
	}

	@Column(name = "id_territorio")
	public Integer getIdTerritorio() {
		return idTerritorio;
	}

	@Column(name = "id_territorio")
	public void setIdTerritorio(Integer idTerritorio) {
		this.idTerritorio = idTerritorio;
	}

	@Column(name = "territorio")
	public String getTerritorio() {
		return territorio;
	}

	@Column(name = "territorio")
	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	@Column(name = "municipio")
	public String getMunicipio() {
		return municipio;
	}

	@Column(name = "municipio")
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Column(name = "estado")
	public String getEstado() {
		return estado;
	}

	@Column(name = "estado")
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "numero_decreto_desapropriacao")
	public String getNumeroDecretoDesapropriacao() {
		return numeroDecretoDesapropriacao;
	}

	@Column(name = "numero_decreto_desapropriacao")
	public void setNumeroDecretoDesapropriacao(String numeroDecretoDesapropriacao) {
		this.numeroDecretoDesapropriacao = numeroDecretoDesapropriacao;
	}

	@Column(name = "data_decreto_deaproriacaoa")
	public String getDataDecretoDeaproriacaoa() {
		return dataDecretoDeaproriacaoa;
	}

	@Column(name = "data_decreto_deaproriacaoa")
	public void setDataDecretoDeaproriacaoa(String dataDecretoDeaproriacaoa) {
		this.dataDecretoDeaproriacaoa = dataDecretoDeaproriacaoa;
	}

	@Column(name = "decreto_desapropriacao_dou")
	public String getDecretoDesapropriacaoDou() {
		return decretoDesapropriacaoDou;
	}

	@Column(name = "decreto_desapropriacao_dou")
	public void setDecretoDesapropriacaoDou(String decretoDesapropriacaoDou) {
		this.decretoDesapropriacaoDou = decretoDesapropriacaoDou;
	}

	@Column(name = "secao_do")
	public String getSecaoDo() {
		return secaoDo;
	}

	@Column(name = "secao_do")
	public void setSecaoDo(String secaoDo) {
		this.secaoDo = secaoDo;
	}

	@Column(name = "folha_do")
	public String getFolhaDo() {
		return folhaDo;
	}

	@Column(name = "folha_do")
	public void setFolhaDo(String folhaDo) {
		this.folhaDo = folhaDo;
	}

	@Column(name = "link_publicacao_1")
	public String getLinkPublicacao1() {
		return linkPublicacao1;
	}

	@Column(name = "link_publicacao_1")
	public void setLinkPublicacao1(String linkPublicacao1) {
		this.linkPublicacao1 = linkPublicacao1;
	}

	@Column(name = "link_publicacao_2")
	public String getLinkPublicacao2() {
		return linkPublicacao2;
	}

	@Column(name = "link_publicacao_2")
	public void setLinkPublicacao2(String linkPublicacao2) {
		this.linkPublicacao2 = linkPublicacao2;
	}

	@Column(name = "link_publicacao_3")
	public String getLinkPublicacao3() {
		return linkPublicacao3;
	}

	@Column(name = "link_publicacao_3")
	public void setLinkPublicacao3(String linkPublicacao3) {
		this.linkPublicacao3 = linkPublicacao3;
	}

	@Column(name = "link_publicacao_4")
	public String getLinkPublicacao4() {
		return linkPublicacao4;
	}

	@Column(name = "link_publicacao_4")
	public void setLinkPublicacao4(String linkPublicacao4) {
		this.linkPublicacao4 = linkPublicacao4;
	}

	@Column(name = "link_publicacao_5")
	public String getLinkPublicacao5() {
		return linkPublicacao5;
	}

	@Column(name = "link_publicacao_5")
	public void setLinkPublicacao5(String linkPublicacao5) {
		this.linkPublicacao5 = linkPublicacao5;
	}
}
