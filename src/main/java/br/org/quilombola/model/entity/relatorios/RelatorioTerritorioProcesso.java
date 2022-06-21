package br.org.quilombola.model.entity.relatorios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "relatorio_processo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"id_territorio",
"territorio",
"municipios",
"numero_processo_reconhecimento",
"link_processo_sei",
"localizacao_acervo_fundiario"
})

public class RelatorioTerritorioProcesso extends BaseEntity {

	private static final long serialVersionUID = 1287821888811209564L;
	
	@Column(name = "id_territorio")
	private Integer idTerritorio;
	@Column(name = "territorio")
	private String territorio;
	@Column(name = "municipios")
	private String municipios;
	@Column(name = "numero_processo_reconhecimento")
	private String numeroProcessoReconhecimento;
	@Column(name = "link_processo_sei")
	private String linkProcessoSei;
	@Column(name = "localizacao_acervo_fundiario")
	private String localizacaoAcervoFundiario;

	/**
	* No args constructor for use in serialization
	*
	*/
	public RelatorioTerritorioProcesso() {
	}

	/**
	*
	* @param linkProcessoSei
	* @param territorio
	* @param localizacaoAcervoFundiario
	* @param municipios
	* @param id
	* @param idTerritorio
	* @param numeroProcessoReconhecimento
	*/
	public RelatorioTerritorioProcesso(Integer idTerritorio, String territorio, String municipios, String numeroProcessoReconhecimento, String linkProcessoSei, String localizacaoAcervoFundiario) {
	super();
	this.idTerritorio = idTerritorio;
	this.territorio = territorio;
	this.municipios = municipios;
	this.numeroProcessoReconhecimento = numeroProcessoReconhecimento;
	this.linkProcessoSei = linkProcessoSei;
	this.localizacaoAcervoFundiario = localizacaoAcervoFundiario;
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

	@Column(name = "municipios")
	public String getMunicipios() {
	return municipios;
	}

	@Column(name = "municipios")
	public void setMunicipios(String municipios) {
	this.municipios = municipios;
	}

	@Column(name = "numero_processo_reconhecimento")
	public String getNumeroProcessoReconhecimento() {
	return numeroProcessoReconhecimento;
	}

	@Column(name = "numero_processo_reconhecimento")
	public void setNumeroProcessoReconhecimento(String numeroProcessoReconhecimento) {
	this.numeroProcessoReconhecimento = numeroProcessoReconhecimento;
	}

	@Column(name = "link_processo_sei")
	public String getLinkProcessoSei() {
	return linkProcessoSei;
	}

	@Column(name = "link_processo_sei")
	public void setLinkProcessoSei(String linkProcessoSei) {
	this.linkProcessoSei = linkProcessoSei;
	}

	@Column(name = "localizacao_acervo_fundiario")
	public String getLocalizacaoAcervoFundiario() {
	return localizacaoAcervoFundiario;
	}

	@Column(name = "localizacao_acervo_fundiario")
	public void setLocalizacaoAcervoFundiario(String localizacaoAcervoFundiario) {
	this.localizacaoAcervoFundiario = localizacaoAcervoFundiario;
	}

}
