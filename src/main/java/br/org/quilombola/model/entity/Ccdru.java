package br.org.quilombola.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "ccdru")
public class Ccdru extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "numero_portaria")
	private String numeroPortaria;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "data_portaria")
	private Date dataPortaria;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "dataPortaria_DOU")
	private Date dataPortariaDOU;

	@Column(name = "secao_DOU")
	private String secaoDOU;

	@Column(name = "folha_DOU")
	private String folhaDOU;

	@Column(name = "link_dou")
	private String linkDOU;

	@Column(name = "link_dou_2")
	private String linkDOU2;

	@Column(name = "link_dou_3")
	private String linkDOU3;

	@Column(name = "numero_familias")
	private int numeroFamilias;

	@Column(name = "nome_imovel")
	private String nomeImovel;

	@JsonIgnoreProperties(
			value = {
					"territorioList",
					"comunidades",
					"superintendencias"
			},
			allowSetters = true
	)
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "municipio_id", nullable = true)
	private Municipio municipio;

	@JsonIgnoreProperties(
			value = {
					"municipioList",
					"quilombos",
					"ccdrus",
					"decretosDesapropriatorios",
					"editaisComunicacao",
					"matriculas",
					"portariasReconhecimento",
					"processosAdministrativos",
					"tituloPropriedade",
					"assentamentos",
					"dadosAdicionais"
			},
			allowSetters = true
	)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "territorio_id", nullable = false)
	private Territorio territorio;

	@Column(name = "areaha_titulo", precision = 19, scale = 4)
	@Type(type = "big_decimal")
	private BigDecimal areaHa;

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

	@Column(name = "numero_processo")
	private String numeroProcesso;

	public Ccdru() {
	}

	public String getNumeroPortaria() {
		return this.numeroPortaria;
	}

	public void setNumeroPortaria(String numeroPortaria) {
		this.numeroPortaria = numeroPortaria;
	}

	public Date getDataPortaria() {
		return this.dataPortaria;
	}

	public void setDataPortaria(Date dataPortaria) {
		this.dataPortaria = dataPortaria;
	}

	public Date getDataPortariaDOU() {
		return this.dataPortariaDOU;
	}

	public void setDataPortariaDOU(Date dataPortariaDOU) {
		this.dataPortariaDOU = dataPortariaDOU;
	}

	public String getSecaoDOU() {
		return this.secaoDOU;
	}

	public void setSecaoDOU(String secaoDOU) {
		this.secaoDOU = secaoDOU;
	}

	public String getFolhaDOU() {
		return this.folhaDOU;
	}

	public void setFolhaDOU(String folhaDOU) {
		this.folhaDOU = folhaDOU;
	}

	public String getLinkDOU() {
		return this.linkDOU;
	}

	public void setLinkDOU(String linkDOU) {
		this.linkDOU = linkDOU;
	}

	public String getLinkDOU2() {
		return linkDOU2;
	}

	public void setLinkDOU2(String linkDOU2) {
		this.linkDOU2 = linkDOU2;
	}

	public String getLinkDOU3() {
		return linkDOU3;
	}

	public void setLinkDOU3(String linkDOU3) {
		this.linkDOU3 = linkDOU3;
	}

	public int getNumeroFamilias() {
		return this.numeroFamilias;
	}

	public void setNumeroFamilias(int numeroFamilias) {
		this.numeroFamilias = numeroFamilias;
	}

	public String getNomeImovel() {
		return this.nomeImovel;
	}

	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public BigDecimal getAreaHa() {
		return this.areaHa;
	}

	public void setAreaHa(BigDecimal areaHa) {
		this.areaHa = areaHa;
	}

	public Territorio getTerritorio() {
		return territorio;
	}

	public void setTerritorio(Territorio territorio) {
		this.territorio = territorio;
	}

	public String getCartorio() {
		return cartorio;
	}

	public void setCartorio(String cartorio) {
		this.cartorio = cartorio;
	}

	public String getNumeroOficio() {
		return numeroOficio;
	}

	public void setNumeroOficio(String numeroOficio) {
		this.numeroOficio = numeroOficio;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public String getFolha() {
		return folha;
	}

	public void setFolha(String folha) {
		this.folha = folha;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public String getCadastroIncra() {
		return cadastroIncra;
	}

	public void setCadastroIncra(String cadastroIncra) {
		this.cadastroIncra = cadastroIncra;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

}