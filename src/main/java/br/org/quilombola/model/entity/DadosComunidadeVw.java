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
@Table(name = "vw_dados_comunidade")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "estado", "comunidades", "comunidades_certificadas", "comunidades_processo", "comunidades_edital",
		"comunidades_potaria", "comunidades_decreto", "comunidades_ccdru", "comunidades_titulo",
		"comunidades_assentamento" })
public class DadosComunidadeVw extends BaseEntity{

	private static final long serialVersionUID = 3195023511882909232L;

	@Column(name = "estado")
	@JsonProperty("estado")
	private String estado;
	
	@Column(name = "comunidades")
	@JsonProperty("comunidades")
	private Integer comunidades;
	
	@Column(name = "comunidades_certificadas")
	@JsonProperty("comunidades_certificadas")
	private Integer comunidadesCertificadas;
	
	@Column(name = "comunidades_processo")
	@JsonProperty("comunidades_processo")
	private Integer comunidadesProcesso;
	
	@Column(name = "comunidades_edital")
	@JsonProperty("comunidades_edital")
	private Integer comunidadesEdital;
	
	@Column(name = "comunidades_potaria")
	@JsonProperty("comunidades_potaria")
	private Integer comunidadesPotaria;
	
	@Column(name = "comunidades_decreto")
	@JsonProperty("comunidades_decreto")
	private Integer comunidadesDecreto;
	
	@Column(name = "comunidades_ccdru")
	@JsonProperty("comunidades_ccdru")
	private Integer comunidadesCcdru;
	
	@Column(name = "comunidades_titulo")
	@JsonProperty("comunidades_titulo")
	private Integer comunidadesTitulo;
	
	@Column(name = "comunidades_assentamento")
	@JsonProperty("comunidades_assentamento")
	private Integer comunidadesAssentamento;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public DadosComunidadeVw() {
	}

	/**
	 *
	 * @param estado
	 * @param comunidadesCertificadas
	 * @param comunidadesCcdru
	 * @param comunidadesDecreto
	 * @param comunidadesEdital
	 * @param comunidadesPotaria
	 * @param comunidadesProcesso
	 * @param comunidades
	 * @param comunidadesAssentamento
	 * @param comunidadesTitulo
	 */
	public DadosComunidadeVw(String estado, Integer comunidades, Integer comunidadesCertificadas,
			Integer comunidadesProcesso, Integer comunidadesEdital, Integer comunidadesPotaria,
			Integer comunidadesDecreto, Integer comunidadesCcdru, Integer comunidadesTitulo,
			Integer comunidadesAssentamento) {
		super();
		this.estado = estado;
		this.comunidades = comunidades;
		this.comunidadesCertificadas = comunidadesCertificadas;
		this.comunidadesProcesso = comunidadesProcesso;
		this.comunidadesEdital = comunidadesEdital;
		this.comunidadesPotaria = comunidadesPotaria;
		this.comunidadesDecreto = comunidadesDecreto;
		this.comunidadesCcdru = comunidadesCcdru;
		this.comunidadesTitulo = comunidadesTitulo;
		this.comunidadesAssentamento = comunidadesAssentamento;
	}

	@JsonProperty("estado")
	public Estado getEstado() {
		if (estado == null || estado.equals("")) {
			return null;
		}
		return Estado.valueOf(estado);
	}

	@JsonProperty("estado")
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@JsonProperty("comunidades")
	public Integer getComunidades() {
		return comunidades;
	}

	@JsonProperty("comunidades")
	public void setComunidades(Integer comunidades) {
		this.comunidades = comunidades;
	}

	@JsonProperty("comunidades_certificadas")
	public Integer getComunidadesCertificadas() {
		return comunidadesCertificadas;
	}

	@JsonProperty("comunidades_certificadas")
	public void setComunidadesCertificadas(Integer comunidadesCertificadas) {
		this.comunidadesCertificadas = comunidadesCertificadas;
	}

	@JsonProperty("comunidades_processo")
	public Integer getComunidadesProcesso() {
		return comunidadesProcesso;
	}

	@JsonProperty("comunidades_processo")
	public void setComunidadesProcesso(Integer comunidadesProcesso) {
		this.comunidadesProcesso = comunidadesProcesso;
	}

	@JsonProperty("comunidades_edital")
	public Integer getComunidadesEdital() {
		return comunidadesEdital;
	}

	@JsonProperty("comunidades_edital")
	public void setComunidadesEdital(Integer comunidadesEdital) {
		this.comunidadesEdital = comunidadesEdital;
	}

	@JsonProperty("comunidades_potaria")
	public Integer getComunidadesPotaria() {
		return comunidadesPotaria;
	}

	@JsonProperty("comunidades_potaria")
	public void setComunidadesPotaria(Integer comunidadesPotaria) {
		this.comunidadesPotaria = comunidadesPotaria;
	}

	@JsonProperty("comunidades_decreto")
	public Integer getComunidadesDecreto() {
		return comunidadesDecreto;
	}

	@JsonProperty("comunidades_decreto")
	public void setComunidadesDecreto(Integer comunidadesDecreto) {
		this.comunidadesDecreto = comunidadesDecreto;
	}

	@JsonProperty("comunidades_ccdru")
	public Integer getComunidadesCcdru() {
		return comunidadesCcdru;
	}

	@JsonProperty("comunidades_ccdru")
	public void setComunidadesCcdru(Integer comunidadesCcdru) {
		this.comunidadesCcdru = comunidadesCcdru;
	}

	@JsonProperty("comunidades_titulo")
	public Integer getComunidadesTitulo() {
		return comunidadesTitulo;
	}

	@JsonProperty("comunidades_titulo")
	public void setComunidadesTitulo(Integer comunidadesTitulo) {
		this.comunidadesTitulo = comunidadesTitulo;
	}

	@JsonProperty("comunidades_assentamento")
	public Integer getComunidadesAssentamento() {
		return comunidadesAssentamento;
	}

	@JsonProperty("comunidades_assentamento")
	public void setComunidadesAssentamento(Integer comunidadesAssentamento) {
		this.comunidadesAssentamento = comunidadesAssentamento;
	}

}
