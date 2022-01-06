package br.org.quilombola.model.entity.relatorios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "relatorio_titulo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "idTerritorio", "territorio", "municipio", "estado", "numeroProcesso",
		"areahaTitulo", "fkOrgaoExpedidor", "dataTitulo", "cartorioRegistroImoveis", "numeroOficio",
		"livroRegistroImoveis", "folhaRegistroImoveis", "matricula", "dataRegistroImoveis", "nomeProprietario",
		"cadastro_incra", "numero_portaria_destinacao", "data_portaria_destinacao", "portaria_destinacao_dou",
		"secaoDo", "folhaDo", "linkPublicacao1", "areaTerritorio", "numeroFamiliaTitulos",
		"percentagemAreaTitulada", "observacaoAreaTitulo" })

public class RelatorioTerritorioTitulo extends BaseEntity {

	private static final long serialVersionUID = -4724795302951017860L;

	@Column(name = "id_territorio")
	private Integer idTerritorio;
	
	@Column(name = "territorio")
	private String territorio;
	
	@Column(name = "municipios")
	private String municipio;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "numero_processo")
	private String numeroProcesso;
	
	@Column(name = "areaha_titulo")
	private Double areahaTitulo;
	
	@Column(name = "orgao_expedidor")
	private String fkOrgaoExpedidor;
	
	@Column(name = "data_titulo")
	private String dataTitulo;
	
//	@Column(name = "cartorio_registro_imoveis")
//	private String cartorioRegistroImoveis;
	
//	@Column(name = "numero_oficio")
//	private String numeroOficio;
	
//	@Column(name = "livro_registro_imoveis")
//	private String livroRegistroImoveis;
	
//	@Column(name = "folha_registro_imoveis")
//	private String folhaRegistroImoveis;
//	
//	@Column(name = "matricula")
//	private String matricula;
	
//	@Column(name = "data_registro_imoveis")
//	private String dataRegistroImoveis;
	
//	@Column(name = "nome_proprietario")
//	private String nomeProprietario;
//	
	@Column(name = "cadastro_incra")
	private String cadastroIncra;
	
	@Column(name = "numero_portaria_destinacao")
	private String numeroPortariaDestinacao;
	
//	@Column(name = "data_portaria_destinacao")
//	private String dataPortariaDestinacao;
	
	@Column(name = "portaria_destinacao_dou")
	private String portariaDestinacaoDou;
	
	@Column(name = "secao_do")
	private String secaoDo;
	
	@Column(name = "folha_do")
	private String folhaDo;
	
	@Column(name = "link_publicacao_1")
	private String linkPublicacao1;
	
	@Column(name = "area_territorio")
	private Double areaTerritorio;
	
	@Column(name = "numero_familia_titulos")
	private String numeroFamiliaTitulos;
	
	@Column(name = "percentagem_area_titulada")
	private String percentagemAreaTitulada;
	
	@Column(name = "observacao_area_titulo")
	private String observacaoAreaTitulo;

	public RelatorioTerritorioTitulo() {
	}

	public RelatorioTerritorioTitulo(Integer idTerritorio, String territorio, String municipio, String estado, String numeroProcesso, Double areahaTitulo, String fkOrgaoExpedidor, String dataTitulo, String cartorioRegistroImoveis, String numeroOficio, String livroRegistroImoveis, String folhaRegistroImoveis, String matricula, String dataRegistroImoveis, String nomeProprietario, String cadastroIncra, String numeroPortariaDestinacao, String dataPortariaDestinacao, String portariaDestinacaoDou, String secaoDo, String folhaDo, String linkPublicacao1, Double areaTerritorio, String numeroFamiliaTitulos, String percentagemAreaTitulada, String observacaoAreaTitulo) {
	super();
	this.idTerritorio = idTerritorio;
	this.territorio = territorio;
	this.municipio = municipio;
	this.estado = estado;
	this.numeroProcesso = numeroProcesso;
	this.areahaTitulo = areahaTitulo;
	this.fkOrgaoExpedidor = fkOrgaoExpedidor;
	this.dataTitulo = dataTitulo;
	//this.cartorioRegistroImoveis = cartorioRegistroImoveis;
//	this.numeroOficio = numeroOficio;
//	this.livroRegistroImoveis = livroRegistroImoveis;
//	this.folhaRegistroImoveis = folhaRegistroImoveis;
//	this.matricula = matricula;
//	this.dataRegistroImoveis = dataRegistroImoveis;
//	this.nomeProprietario = nomeProprietario;
	this.cadastroIncra = cadastroIncra;
	this.numeroPortariaDestinacao = numeroPortariaDestinacao;
//	this.dataPortariaDestinacao = dataPortariaDestinacao;
	this.portariaDestinacaoDou = portariaDestinacaoDou;
	this.secaoDo = secaoDo;
	this.folhaDo = folhaDo;
	this.linkPublicacao1 = linkPublicacao1;
	this.areaTerritorio = areaTerritorio;
	this.numeroFamiliaTitulos = numeroFamiliaTitulos;
	this.percentagemAreaTitulada = percentagemAreaTitulada;
	this.observacaoAreaTitulo = observacaoAreaTitulo;
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

	@Column(name = "numero_processo")
	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	@Column(name = "numero_processo")
	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	@Column(name = "areaha_titulo")
	public Double getAreahaTitulo() {
		return areahaTitulo;
	}

	@Column(name = "areaha_titulo")
	public void setAreahaTitulo(Double areahaTitulo) {
		this.areahaTitulo = areahaTitulo;
	}

	@Column(name = "fk_orgao_expedidor")
	public String getFkOrgaoExpedidor() {
		return fkOrgaoExpedidor;
	}

	@Column(name = "fk_orgao_expedidor")
	public void setFkOrgaoExpedidor(String fkOrgaoExpedidor) {
		this.fkOrgaoExpedidor = fkOrgaoExpedidor;
	}

	@Column(name = "data_titulo")
	public String getDataTitulo() {
		return dataTitulo;
	}

	@Column(name = "data_titulo")
	public void setDataTitulo(String dataTitulo) {
		this.dataTitulo = dataTitulo;
	}

//	@Column(name = "cartorio_registro_imoveis")
//	public String getCartorioRegistroImoveis() {
//		return cartorioRegistroImoveis;
//	}
//
//	@Column(name = "cartorio_registro_imoveis")
//	public void setCartorioRegistroImoveis(String cartorioRegistroImoveis) {
//		this.cartorioRegistroImoveis = cartorioRegistroImoveis;
//	}

//	@Column(name = "numero_oficio")
//	public String getNumeroOficio() {
//		return numeroOficio;
//	}
//
//	@Column(name = "numero_oficio")
//	public void setNumeroOficio(String numeroOficio) {
//		this.numeroOficio = numeroOficio;
//	}
//
//	@Column(name = "livro_registro_imoveis")
//	public String getLivroRegistroImoveis() {
//		return livroRegistroImoveis;
//	}
//
//	@Column(name = "livro_registro_imoveis")
//	public void setLivroRegistroImoveis(String livroRegistroImoveis) {
//		this.livroRegistroImoveis = livroRegistroImoveis;
//	}
//
//	@Column(name = "folha_registro_imoveis")
//	public String getFolhaRegistroImoveis() {
//		return folhaRegistroImoveis;
//	}
//
//	@Column(name = "folha_registro_imoveis")
//	public void setFolhaRegistroImoveis(String folhaRegistroImoveis) {
//		this.folhaRegistroImoveis = folhaRegistroImoveis;
//	}
//
//	@Column(name = "matricula")
//	public String getMatricula() {
//		return matricula;
//	}
//
//	@Column(name = "matricula")
//	public void setMatricula(String matricula) {
//		this.matricula = matricula;
//	}
//
//	@Column(name = "data_registro_imoveis")
//	public String getDataRegistroImoveis() {
//		return dataRegistroImoveis;
//	}
//
//	@Column(name = "data_registro_imoveis")
//	public void setDataRegistroImoveis(String dataRegistroImoveis) {
//		this.dataRegistroImoveis = dataRegistroImoveis;
//	}
//
//	@Column(name = "nome_proprietario")
//	public String getNomeProprietario() {
//		return nomeProprietario;
//	}
//
//	@Column(name = "nome_proprietario")
//	public void setNomeProprietario(String nomeProprietario) {
//		this.nomeProprietario = nomeProprietario;
//	}

	@Column(name = "cadastro_incra")
	public String getCadastroIncra() {
		return cadastroIncra;
	}

	@Column(name = "cadastro_incra")
	public void setCadastroIncra(String cadastroIncra) {
		this.cadastroIncra = cadastroIncra;
	}

	@Column(name = "numero_portaria_destinacao")
	public String getNumeroPortariaDestinacao() {
		return numeroPortariaDestinacao;
	}

	@Column(name = "numero_portaria_destinacao")
	public void setNumeroPortariaDestinacao(String numeroPortariaDestinacao) {
		this.numeroPortariaDestinacao = numeroPortariaDestinacao;
	}

//	@Column(name = "data_portaria_destinacao")
//	public String getDataPortariaDestinacao() {
//		return dataPortariaDestinacao;
//	}
//
//	@Column(name = "data_portaria_destinacao")
//	public void setDataPortariaDestinacao(String dataPortariaDestinacao) {
//		this.dataPortariaDestinacao = dataPortariaDestinacao;
//	}

	@Column(name = "portaria_destinacao_dou")
	public String getPortariaDestinacaoDou() {
		return portariaDestinacaoDou;
	}

	@Column(name = "portaria_destinacao_dou")
	public void setPortariaDestinacaoDou(String portariaDestinacaoDou) {
		this.portariaDestinacaoDou = portariaDestinacaoDou;
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

	@Column(name = "area_territorio")
	public Double getAreaTerritorio() {
		return areaTerritorio;
	}

	@Column(name = "area_territorio")
	public void setAreaTerritorio(Double areaTerritorio) {
		this.areaTerritorio = areaTerritorio;
	}

	@Column(name = "numero_familia_titulos")
	public String getNumeroFamiliaTitulos() {
		return numeroFamiliaTitulos;
	}

	@Column(name = "numero_familia_titulos")
	public void setNumeroFamiliaTitulos(String numeroFamiliaTitulos) {
		this.numeroFamiliaTitulos = numeroFamiliaTitulos;
	}

	@Column(name = "percentagem_area_titulada")
	public String getPercentagemAreaTitulada() {
		return percentagemAreaTitulada;
	}

	@Column(name = "percentagem_area_titulada")
	public void setPercentagemAreaTitulada(String percentagemAreaTitulada) {
		this.percentagemAreaTitulada = percentagemAreaTitulada;
	}

	@Column(name = "observacao_area_titulo")
	public String getObservacaoAreaTitulo() {
		return observacaoAreaTitulo;
	}

	@Column(name = "observacao_area_titulo")
	public void setObservacaoAreaTitulo(String observacaoAreaTitulo) {
		this.observacaoAreaTitulo = observacaoAreaTitulo;
	}

}
