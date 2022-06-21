package br.org.quilombola.model.entity.relatorios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "relatorio_ccdru")
public class RelatorioTerritorioCcdru extends BaseEntity {

	private static final long serialVersionUID = -1230489836842086507L;
	
	@Column(name = "id_territorio")
	private Integer idTerritorio;
	
	@Column(name = "territorio")
	private String territorio;
	
	@Column(name = "municipios")
	private String municipios;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "numero_processo")
	private String numeroProcesso;
	
	@Column(name = "numero_portaria")
	private String numeroPortaria;
	
	@Column(name = "data_portaria")
	private String dataPortaria;
	
	@Column(name = "secao_dou")
	private String secaoDou;
	
	@Column(name = "folha_dou")
	private String folhaDou;
	
	@Column(name = "link_dou")
	private String linkDou;
	
	@Column(name = "link_dou_2")
	private String linkDou2;
	
	@Column(name = "numero_familias")
	private Integer numeroFamilias;
	
	@Column(name = "nome_imovel")
	private String nomeImovel;
	
	@Column(name = "areaha_titulo")
	private Double areahaTitulo;
	
	@Column(name = "cartorio")
	private String cartorio;
	
	@Column(name = "numero_oficio")
	private String numeroOficio;
	
	@Column(name = "livro")
	private String livro;
	
	@Column(name = "folha")
	private String folha;
	
	@Column(name = "matricula")
	private String matricula;
	
	@Column(name = "nome_proprietario")
	private String nomeProprietario;
	
	@Column(name = "cadastro_incra")
	private String cadastroIncra;

	public RelatorioTerritorioCcdru() {
	}

	public RelatorioTerritorioCcdru(Integer idTerritorio, String territorio, String municipios, String estado,
			String numeroProcesso, String numeroPortaria, String dataPortaria, String secaoDou, String folhaDou,
			String linkDou, String linkDou2, Integer numeroFamilias, String nomeImovel, Double areahaTitulo,
			String cartorio, String numeroOficio, String livro, String folha, String matricula, String nomeProprietario,
			String cadastroIncra) {
		super();
		this.idTerritorio = idTerritorio;
		this.territorio = territorio;
		this.municipios = municipios;
		this.estado = estado;
		this.numeroProcesso = numeroProcesso;
		this.numeroPortaria = numeroPortaria;
		this.dataPortaria = dataPortaria;
		this.secaoDou = secaoDou;
		this.folhaDou = folhaDou;
		this.linkDou = linkDou;
		this.linkDou2 = linkDou2;
		this.numeroFamilias = numeroFamilias;
		this.nomeImovel = nomeImovel;
		this.areahaTitulo = areahaTitulo;
		this.cartorio = cartorio;
		this.numeroOficio = numeroOficio;
		this.livro = livro;
		this.folha = folha;
		this.matricula = matricula;
		this.nomeProprietario = nomeProprietario;
		this.cadastroIncra = cadastroIncra;
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
	public String getMunicipios() {
		return municipios;
	}

	@Column(name = "municipio")
	public void setMunicipios(String municipios) {
		this.municipios = municipios;
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

	@Column(name = "numero_portaria")
	public String getNumeroPortaria() {
		return numeroPortaria;
	}

	@Column(name = "numero_portaria")
	public void setNumeroPortaria(String numeroPortaria) {
		this.numeroPortaria = numeroPortaria;
	}

	@Column(name = "data_portaria")
	public String getDataPortaria() {
		return dataPortaria;
	}

	@Column(name = "data_portaria")
	public void setDataPortaria(String dataPortaria) {
		this.dataPortaria = dataPortaria;
	}

	@Column(name = "secao_dou")
	public String getSecaoDou() {
		return secaoDou;
	}

	@Column(name = "secao_dou")
	public void setSecaoDou(String secaoDou) {
		this.secaoDou = secaoDou;
	}

	@Column(name = "folha_dou")
	public String getFolhaDou() {
		return folhaDou;
	}

	@Column(name = "folha_dou")
	public void setFolhaDou(String folhaDou) {
		this.folhaDou = folhaDou;
	}

	@Column(name = "link_dou")
	public String getLinkDou() {
		return linkDou;
	}

	@Column(name = "link_dou")
	public void setLinkDou(String linkDou) {
		this.linkDou = linkDou;
	}

	@Column(name = "link_dou_2")
	public String getLinkDou2() {
		return linkDou2;
	}

	@Column(name = "link_dou_2")
	public void setLinkDou2(String linkDou2) {
		this.linkDou2 = linkDou2;
	}

	@Column(name = "numero_familias")
	public Integer getNumeroFamilias() {
		return numeroFamilias;
	}

	@Column(name = "numero_familias")
	public void setNumeroFamilias(Integer numeroFamilias) {
		this.numeroFamilias = numeroFamilias;
	}

	@Column(name = "nome_imovel")
	public String getNomeImovel() {
		return nomeImovel;
	}

	@Column(name = "nome_imovel")
	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}

	@Column(name = "areaha_titulo")
	public Double getAreahaTitulo() {
		return areahaTitulo;
	}

	@Column(name = "areaha_titulo")
	public void setAreahaTitulo(Double areahaTitulo) {
		this.areahaTitulo = areahaTitulo;
	}

	@Column(name = "cartorio")
	public String getCartorio() {
		return cartorio;
	}

	@Column(name = "cartorio")
	public void setCartorio(String cartorio) {
		this.cartorio = cartorio;
	}

	@Column(name = "numero_oficio")
	public String getNumeroOficio() {
		return numeroOficio;
	}

	@Column(name = "numero_oficio")
	public void setNumeroOficio(String numeroOficio) {
		this.numeroOficio = numeroOficio;
	}

	@Column(name = "livro")
	public String getLivro() {
		return livro;
	}

	@Column(name = "livro")
	public void setLivro(String livro) {
		this.livro = livro;
	}

	@Column(name = "folha")
	public String getFolha() {
		return folha;
	}

	@Column(name = "folha")
	public void setFolha(String folha) {
		this.folha = folha;
	}

	@Column(name = "matricula")
	public String getMatricula() {
		return matricula;
	}

	@Column(name = "matricula")
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Column(name = "nome_proprietario")
	public String getNomeProprietario() {
		return nomeProprietario;
	}

	@Column(name = "nome_proprietario")
	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	@Column(name = "cadastro_incra")
	public String getCadastroIncra() {
		return cadastroIncra;
	}

	@Column(name = "cadastro_incra")
	public void setCadastroIncra(String cadastroIncra) {
		this.cadastroIncra = cadastroIncra;
	}

}