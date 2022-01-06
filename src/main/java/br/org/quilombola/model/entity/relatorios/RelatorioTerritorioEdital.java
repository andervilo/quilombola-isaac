package br.org.quilombola.model.entity.relatorios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "relatorio_edital")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id_territorio", "territorio", "municipio", "estado", "dou_doe_primeiro_edital", "secao_do",
		"folha_do", "link_publicacao_1", "link_publicacao_2", "numero_familias", "areaha_titulo", "areaha_2" })
public class RelatorioTerritorioEdital extends BaseEntity {

	private static final long serialVersionUID = 2352702152335344507L;

	@Column(name = "id_territorio")
	private Integer idTerritorio;
	@Column(name = "territorio")
	private String territorio;
	@Column(name = "municipio")
	private String municipio;
	@Column(name = "estado")
	private String estado;
	@Column(name = "dou_doe_primeiro_edital")
	private String douDoePrimeiroEdital;
	@Column(name = "secao_do")
	private String secaoDo;
	@Column(name = "folha_do")
	private String folhaDo;
	@Column(name = "link_publicacao_1")
	private String linkPublicacao1;
	@Column(name = "link_publicacao_2")
	private String linkPublicacao2;
	@Column(name = "numero_familias")
	private Integer numeroFamilias;
	@Column(name = "areaha_titulo")
	private Integer areahaTitulo;
	@Column(name = "areaha_2")
	private Integer areaha2;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public RelatorioTerritorioEdital() {
	}

	/**
	 *
	 * @param territorio
	 * @param estado
	 * @param douDoePrimeiroEdital
	 * @param numeroFamilias
	 * @param areahaTitulo
	 * @param municipio
	 * @param idTerritorio
	 * @param secaoDo
	 * @param linkPublicacao1
	 * @param linkPublicacao2
	 * @param areaha2
	 * @param folhaDo
	 */
	public RelatorioTerritorioEdital(Integer idTerritorio, String territorio, String municipio, String estado,
			String douDoePrimeiroEdital, String secaoDo, String folhaDo, String linkPublicacao1, String linkPublicacao2,
			Integer numeroFamilias, Integer areahaTitulo, Integer areaha2) {
		super();
		this.idTerritorio = idTerritorio;
		this.territorio = territorio;
		this.municipio = municipio;
		this.estado = estado;
		this.douDoePrimeiroEdital = douDoePrimeiroEdital;
		this.secaoDo = secaoDo;
		this.folhaDo = folhaDo;
		this.linkPublicacao1 = linkPublicacao1;
		this.linkPublicacao2 = linkPublicacao2;
		this.numeroFamilias = numeroFamilias;
		this.areahaTitulo = areahaTitulo;
		this.areaha2 = areaha2;
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

	@Column(name = "dou_doe_primeiro_edital")
	public String getDouDoePrimeiroEdital() {
		return douDoePrimeiroEdital;
	}

	@Column(name = "dou_doe_primeiro_edital")
	public void setDouDoePrimeiroEdital(String douDoePrimeiroEdital) {
		this.douDoePrimeiroEdital = douDoePrimeiroEdital;
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

	@Column(name = "numero_familias")
	public Integer getNumeroFamilias() {
		return numeroFamilias;
	}

	@Column(name = "numero_familias")
	public void setNumeroFamilias(Integer numeroFamilias) {
		this.numeroFamilias = numeroFamilias;
	}

	@Column(name = "areaha_titulo")
	public Integer getAreahaTitulo() {
		return areahaTitulo;
	}

	@Column(name = "areaha_titulo")
	public void setAreahaTitulo(Integer areahaTitulo) {
		this.areahaTitulo = areahaTitulo;
	}

	@Column(name = "areaha_2")
	public Integer getAreaha2() {
		return areaha2;
	}

	@Column(name = "areaha_2")
	public void setAreaha2(Integer areaha2) {
		this.areaha2 = areaha2;
	}

}
