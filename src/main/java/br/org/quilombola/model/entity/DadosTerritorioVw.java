package br.org.quilombola.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.org.quilombola.arquitetura.BaseEntity;
import br.org.quilombola.arquitetura.enums.Estado;

@Entity
@Table(name = "vw_dados_territorio")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "estado", "territorios", "territorios_certificadas", "territorios_processo",
		"territorios_edital", "territorios_potaria", "territorios_decreto", "territorios_ccdru", "territorios_titulo",
		"territorio_assentamento", "territorio_ha_edital", "territorio_familias_edital", "territorio_ha_titulados",
		"territorio_familias_tituladas", "territorio_ha_assentamenos", "territorio_familas_assentamenos" })
public class DadosTerritorioVw extends BaseEntity{

	private static final long serialVersionUID = 2945658452930364193L;

	@Column(name = "estado")
	@JsonProperty("estado")
	private String estado;
	
	@Column(name = "territorios")
	@JsonProperty("territorios")
	private Integer territorios;
	
	@Column(name = "territorios_certificados")
	@JsonProperty("territorios_certificadas")
	private Integer territoriosCertificadas;
	
	@Column(name = "territorios_processo")
	@JsonProperty("territorios_processo")
	private Integer territoriosProcesso;
	
	@Column(name = "territorios_edital")
	@JsonProperty("territorios_edital")
	private Integer territoriosEdital;
	
	@Column(name = "territorios_potaria")
	@JsonProperty("territorios_potaria")
	private Integer territoriosPotaria;
	
	@Column(name = "territorios_decreto")
	@JsonProperty("territorios_decreto")
	private Integer territoriosDecreto;
	
	@Column(name = "territorios_ccdru")
	@JsonProperty("territorios_ccdru")
	private Integer territoriosCcdru;
	
	@Column(name = "territorios_titulo")
	@JsonProperty("territorios_titulo")
	private Integer territoriosTitulo;
	
	@Column(name = "territorio_assentamento")
	@JsonProperty("territorio_assentamento")
	private Integer territorioAssentamento;
	
	@Column(name = "territorio_ha_edital")
	@JsonProperty("territorio_ha_edital")
	private Double territorioHaEdital;
	
	@Column(name = "territorio_familias_edital")
	@JsonProperty("territorio_familias_edital")
	private Integer territorioFamiliasEdital;
	
	@Column(name = "territorio_ha_titulados")
	@JsonProperty("territorio_ha_titulados")
	private Double territorioHaTitulados;
	
	@Column(name = "territorio_familias_tituladas")
	@JsonProperty("territorio_familias_tituladas")
	private Integer territorioFamiliasTituladas;
	
	@Column(name = "territorio_ha_assentamenos")
	@JsonProperty("territorio_ha_assentamenos")
	private Double territorioHaAssentamenos;
	
	@Column(name = "territorio_familas_assentamenos")
	@JsonProperty("territorio_familas_assentamenos")
	private Integer territorioFamilasAssentamenos;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public DadosTerritorioVw() {
	}

	/**
	 *
	 * @param territorioHaTitulados
	 * @param estado
	 * @param territorios
	 * @param territoriosCertificadas
	 * @param territorioFamilasAssentamenos
	 * @param territorioHaAssentamenos
	 * @param territoriosEdital
	 * @param territoriosTitulo
	 * @param territoriosPotaria
	 * @param territorioFamiliasTituladas
	 * @param territorioAssentamento
	 * @param territoriosProcesso
	 * @param territoriosDecreto
	 * @param territorioHaEdital
	 * @param territoriosCcdru
	 * @param id
	 * @param territorioFamiliasEdital
	 */
	public DadosTerritorioVw(String estado, Integer territorios, Integer territoriosCertificadas,
			Integer territoriosProcesso, Integer territoriosEdital, Integer territoriosPotaria,
			Integer territoriosDecreto, Integer territoriosCcdru, Integer territoriosTitulo,
			Integer territorioAssentamento, Double territorioHaEdital, Integer territorioFamiliasEdital,
			Double territorioHaTitulados, Integer territorioFamiliasTituladas, Double territorioHaAssentamenos,
			Integer territorioFamilasAssentamenos) {
		super();
		this.estado = estado;
		this.territorios = territorios;
		this.territoriosCertificadas = territoriosCertificadas;
		this.territoriosProcesso = territoriosProcesso;
		this.territoriosEdital = territoriosEdital;
		this.territoriosPotaria = territoriosPotaria;
		this.territoriosDecreto = territoriosDecreto;
		this.territoriosCcdru = territoriosCcdru;
		this.territoriosTitulo = territoriosTitulo;
		this.territorioAssentamento = territorioAssentamento;
		this.territorioHaEdital = territorioHaEdital;
		this.territorioFamiliasEdital = territorioFamiliasEdital;
		this.territorioHaTitulados = territorioHaTitulados;
		this.territorioFamiliasTituladas = territorioFamiliasTituladas;
		this.territorioHaAssentamenos = territorioHaAssentamenos;
		this.territorioFamilasAssentamenos = territorioFamilasAssentamenos;
	}

	@JsonProperty("estado")
	public Estado getEstado() {
		if(estado == null || estado.equals("")) {
			return null;
		}
		return Estado.valueOf(estado);
	}

	@JsonProperty("estado")
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@JsonProperty("territorios")
	public Integer getTerritorios() {
		return territorios;
	}

	@JsonProperty("territorios")
	public void setTerritorios(Integer territorios) {
		this.territorios = territorios;
	}

	@JsonProperty("territorios_certificadas")
	public Integer getTerritoriosCertificadas() {
		return territoriosCertificadas;
	}

	@JsonProperty("territorios_certificadas")
	public void setTerritoriosCertificadas(Integer territoriosCertificadas) {
		this.territoriosCertificadas = territoriosCertificadas;
	}

	@JsonProperty("territorios_processo")
	public Integer getTerritoriosProcesso() {
		return territoriosProcesso;
	}

	@JsonProperty("territorios_processo")
	public void setTerritoriosProcesso(Integer territoriosProcesso) {
		this.territoriosProcesso = territoriosProcesso;
	}

	@JsonProperty("territorios_edital")
	public Integer getTerritoriosEdital() {
		return territoriosEdital;
	}

	@JsonProperty("territorios_edital")
	public void setTerritoriosEdital(Integer territoriosEdital) {
		this.territoriosEdital = territoriosEdital;
	}

	@JsonProperty("territorios_potaria")
	public Integer getTerritoriosPotaria() {
		return territoriosPotaria;
	}

	@JsonProperty("territorios_potaria")
	public void setTerritoriosPotaria(Integer territoriosPotaria) {
		this.territoriosPotaria = territoriosPotaria;
	}

	@JsonProperty("territorios_decreto")
	public Integer getTerritoriosDecreto() {
		return territoriosDecreto;
	}

	@JsonProperty("territorios_decreto")
	public void setTerritoriosDecreto(Integer territoriosDecreto) {
		this.territoriosDecreto = territoriosDecreto;
	}

	@JsonProperty("territorios_ccdru")
	public Integer getTerritoriosCcdru() {
		return territoriosCcdru;
	}

	@JsonProperty("territorios_ccdru")
	public void setTerritoriosCcdru(Integer territoriosCcdru) {
		this.territoriosCcdru = territoriosCcdru;
	}

	@JsonProperty("territorios_titulo")
	public Integer getTerritoriosTitulo() {
		return territoriosTitulo;
	}

	@JsonProperty("territorios_titulo")
	public void setTerritoriosTitulo(Integer territoriosTitulo) {
		this.territoriosTitulo = territoriosTitulo;
	}

	@JsonProperty("territorio_assentamento")
	public Integer getTerritorioAssentamento() {
		return territorioAssentamento;
	}

	@JsonProperty("territorio_assentamento")
	public void setTerritorioAssentamento(Integer territorioAssentamento) {
		this.territorioAssentamento = territorioAssentamento;
	}

	@JsonProperty("territorio_ha_edital")
	public Double getTerritorioHaEdital() {
		return territorioHaEdital == null ? 0 : territorioHaEdital;
	}

	@JsonProperty("territorio_ha_edital")
	public void setTerritorioHaEdital(Double territorioHaEdital) {
		this.territorioHaEdital = territorioHaEdital;
	}

	@JsonProperty("territorio_familias_edital")
	public Integer getTerritorioFamiliasEdital() {
		return territorioFamiliasEdital == null ? 0 : territorioFamiliasEdital;
	}

	@JsonProperty("territorio_familias_edital")
	public void setTerritorioFamiliasEdital(Integer territorioFamiliasEdital) {
		this.territorioFamiliasEdital = territorioFamiliasEdital;
	}

	@JsonProperty("territorio_ha_titulados")
	public Double getTerritorioHaTitulados() {
		return territorioHaTitulados == null ? 0 : territorioHaTitulados;
	}

	@JsonProperty("territorio_ha_titulados")
	public void setTerritorioHaTitulados(Double territorioHaTitulados) {
		this.territorioHaTitulados = territorioHaTitulados;
	}

	@JsonProperty("territorio_familias_tituladas")
	public Integer getTerritorioFamiliasTituladas() {
		return territorioFamiliasTituladas  == null ? 0 : territorioFamiliasTituladas;
	}

	@JsonProperty("territorio_familias_tituladas")
	public void setTerritorioFamiliasTituladas(Integer territorioFamiliasTituladas) {
		this.territorioFamiliasTituladas = territorioFamiliasTituladas;
	}

	@JsonProperty("territorio_ha_assentamenos")
	public Double getTerritorioHaAssentamenos() {
		return territorioHaAssentamenos  == null ? 0 : territorioHaAssentamenos;
	}

	@JsonProperty("territorio_ha_assentamenos")
	public void setTerritorioHaAssentamenos(Double territorioHaAssentamenos) {
		this.territorioHaAssentamenos = territorioHaAssentamenos;
	}

	@JsonProperty("territorio_familas_assentamenos")
	public Integer getTerritorioFamilasAssentamenos() {
		return territorioFamilasAssentamenos == null ? 0 : territorioFamilasAssentamenos;
	}

	@JsonProperty("territorio_familas_assentamenos")
	public void setTerritorioFamilasAssentamenos(Integer territorioFamilasAssentamenos) {
		this.territorioFamilasAssentamenos = territorioFamilasAssentamenos;
	}

}
