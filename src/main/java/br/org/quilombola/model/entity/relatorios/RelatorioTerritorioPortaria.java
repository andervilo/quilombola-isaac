package br.org.quilombola.model.entity.relatorios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "relatorio_portaria")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id_territorio", "territorio", "municipios", "estado", "numero_portaria_reconhecimento",
		"data_portaria_reconhecimento", "portaria_reconhecimento_dou", "secao_do", "folha_do", "link_publicacao_1",
		"link_publicacao_2", "link_publicacao_3", "link_publicacao_5", "link_publicacao_6" })
public class RelatorioTerritorioPortaria extends BaseEntity {

	private static final long serialVersionUID = -3866389782573537237L;

	@Column( name ="id_territorio")
	private Integer idTerritorio;
	
	@Column( name ="territorio")
	private String territorio;
	
	@Column( name ="municipios")
	private String municipios;
	
	@Column( name ="estado")
	private String estado;
	
	@Column( name ="numero_portaria_reconhecimento")
	private String numeroPortariaReconhecimento;
	
	@Column( name ="data_portaria_reconhecimento")
	private String dataPortariaReconhecimento;
	
	@Column( name ="portaria_reconhecimento_dou")
	private String portariaReconhecimentoDou;
	
	@Column( name ="secao_do")
	private String secaoDo;
	
	@Column( name ="folha_do")
	private String folhaDo;
	
	@Column( name ="link_publicacao_1")
	private String linkPublicacao1;
	
	@Column( name ="link_publicacao_2")
	private String linkPublicacao2;
	
	@Column( name ="link_publicacao_3")
	private String linkPublicacao3;
	
	@Column( name ="link_publicacao_5")
	private String linkPublicacao5;
	
	@Column( name ="link_publicacao_6")
	private String linkPublicacao6;

	public RelatorioTerritorioPortaria() {
	}

	public RelatorioTerritorioPortaria(Integer idTerritorio, String territorio, String municipios, String estado, String numeroPortariaReconhecimento, String dataPortariaReconhecimento, String portariaReconhecimentoDou, String secaoDo, String folhaDo, String linkPublicacao1, String linkPublicacao2, String linkPublicacao3, String linkPublicacao5, String linkPublicacao6) {
	super();
	this.idTerritorio = idTerritorio;
	this.territorio = territorio;
	this.municipios = municipios;
	this.estado = estado;
	this.numeroPortariaReconhecimento = numeroPortariaReconhecimento;
	this.dataPortariaReconhecimento = dataPortariaReconhecimento;
	this.portariaReconhecimentoDou = portariaReconhecimentoDou;
	this.secaoDo = secaoDo;
	this.folhaDo = folhaDo;
	this.linkPublicacao1 = linkPublicacao1;
	this.linkPublicacao2 = linkPublicacao2;
	this.linkPublicacao3 = linkPublicacao3;
	this.linkPublicacao5 = linkPublicacao5;
	this.linkPublicacao6 = linkPublicacao6;
	}

	@Column( name ="id_territorio")
	public Integer getIdTerritorio() {
		return idTerritorio;
	}

	@Column( name ="id_territorio")
	public void setIdTerritorio(Integer idTerritorio) {
		this.idTerritorio = idTerritorio;
	}

	@Column( name ="territorio")
	public String getTerritorio() {
		return territorio;
	}

	@Column( name ="territorio")
	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	@Column( name ="municipios")
	public String getMunicipios() {
		return municipios;
	}

	@Column( name ="municipios")
	public void setMunicipios(String municipios) {
		this.municipios = municipios;
	}

	@Column( name ="estado")
	public String getEstado() {
		return estado;
	}

	@Column( name ="estado")
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column( name ="numero_portaria_reconhecimento")
	public String getNumeroPortariaReconhecimento() {
		return numeroPortariaReconhecimento;
	}

	@Column( name ="numero_portaria_reconhecimento")
	public void setNumeroPortariaReconhecimento(String numeroPortariaReconhecimento) {
		this.numeroPortariaReconhecimento = numeroPortariaReconhecimento;
	}

	@Column( name ="data_portaria_reconhecimento")
	public String getDataPortariaReconhecimento() {
		return dataPortariaReconhecimento;
	}

	@Column( name ="data_portaria_reconhecimento")
	public void setDataPortariaReconhecimento(String dataPortariaReconhecimento) {
		this.dataPortariaReconhecimento = dataPortariaReconhecimento;
	}

	@Column( name ="portaria_reconhecimento_dou")
	public String getPortariaReconhecimentoDou() {
		return portariaReconhecimentoDou;
	}

	@Column( name ="portaria_reconhecimento_dou")
	public void setPortariaReconhecimentoDou(String portariaReconhecimentoDou) {
		this.portariaReconhecimentoDou = portariaReconhecimentoDou;
	}

	@Column( name ="secao_do")
	public String getSecaoDo() {
		return secaoDo;
	}

	@Column( name ="secao_do")
	public void setSecaoDo(String secaoDo) {
		this.secaoDo = secaoDo;
	}

	@Column( name ="folha_do")
	public String getFolhaDo() {
		return folhaDo;
	}

	@Column( name ="folha_do")
	public void setFolhaDo(String folhaDo) {
		this.folhaDo = folhaDo;
	}

	@Column( name ="link_publicacao_1")
	public String getLinkPublicacao1() {
		return linkPublicacao1;
	}

	@Column( name ="link_publicacao_1")
	public void setLinkPublicacao1(String linkPublicacao1) {
		this.linkPublicacao1 = linkPublicacao1;
	}

	@Column( name ="link_publicacao_2")
	public String getLinkPublicacao2() {
		return linkPublicacao2;
	}

	@Column( name ="link_publicacao_2")
	public void setLinkPublicacao2(String linkPublicacao2) {
		this.linkPublicacao2 = linkPublicacao2;
	}

	@Column( name ="link_publicacao_3")
	public String getLinkPublicacao3() {
		return linkPublicacao3;
	}

	@Column( name ="link_publicacao_3")
	public void setLinkPublicacao3(String linkPublicacao3) {
		this.linkPublicacao3 = linkPublicacao3;
	}

	@Column( name ="link_publicacao_5")
	public String getLinkPublicacao5() {
		return linkPublicacao5;
	}

	@Column( name ="link_publicacao_5")
	public void setLinkPublicacao5(String linkPublicacao5) {
		this.linkPublicacao5 = linkPublicacao5;
	}

	@Column( name ="link_publicacao_6")
	public String getLinkPublicacao6() {
		return linkPublicacao6;
	}

	@Column( name ="link_publicacao_6")
	public void setLinkPublicacao6(String linkPublicacao6) {
		this.linkPublicacao6 = linkPublicacao6;
	}
}
