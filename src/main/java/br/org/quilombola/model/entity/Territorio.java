package br.org.quilombola.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "territorio")
public class Territorio extends BaseEntity {

	private static final long serialVersionUID = -3622623516438368648L;

	@Column(name = "nome", length = 1000)
	private String nome;

	@JsonIgnoreProperties(
			value = {
					"territorioList"
			},
			allowSetters = true
	)
	@JoinTable(name = "territorio_municipio", joinColumns = {
			@JoinColumn(name = "fk_territorio", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_municipio", referencedColumnName = "id") })
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Municipio> municipioList = new ArrayList<>();

	@JsonIgnoreProperties(
			value = {
					"territorioList"
			},
			allowSetters = true
	)
	@JoinTable(name = "quilombo_territorio", joinColumns = {
			@JoinColumn(name = "fk_territorio", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_quilombo", referencedColumnName = "id") })
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Quilombo> quilombos = new ArrayList<>();

	@Column(name = "numero_comunidades")
	private Integer numeroComunidades;

	@JsonIgnoreProperties(
			value = {
					"territorio"
			},
			allowSetters = true
	)
	@OneToMany(mappedBy = "territorio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ccdru> ccdrus = new ArrayList<>();

	@JsonIgnoreProperties(
			value = {
					"territorio"
			},
			allowSetters = true
	)
	@OneToMany(mappedBy = "territorio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DecretoDesapropriatorio> decretosDesapropriatorios = new ArrayList<>();

	@JsonIgnoreProperties(
			value = {
					"territorio"
			},
			allowSetters = true
	)
	@OneToMany(mappedBy = "territorio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EditalComunicacao> editaisComunicacao = new ArrayList<>();

	@JsonIgnoreProperties(
			value = {
					"territorio"
			},
			allowSetters = true
	)
	@OneToMany(mappedBy = "territorio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Matricula> matriculas = new ArrayList<>();

	@JsonIgnoreProperties(
			value = {
					"territorio"
			},
			allowSetters = true
	)
	@OneToMany(mappedBy = "territorio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PortariaReconhecimento> portariasReconhecimento = new ArrayList<>();

	@JsonIgnoreProperties(
			value = {
					"territorio"
			},
			allowSetters = true
	)
	@OneToMany(mappedBy = "territorio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProcessoAdministrativo> processosAdministrativos = new ArrayList<>();

//	@JsonIgnoreProperties(
//			value = {
//					"territorio"
//			},
//			allowSetters = true
//	)
	@OneToMany(mappedBy = "territorio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TituloPropriedade> tituloPropriedade = new ArrayList<>();

	@OneToMany(mappedBy = "territorio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AssentamentoQuilombola> assentamentos = new ArrayList<>();

	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIgnoreProperties(
			value = {
					"territorio","comunidade"
			},
			allowSetters = true
	)
	@OneToOne(mappedBy = "territorio", optional = true)
	private DadosAdicionaisComunidade dadosAdicionais = new DadosAdicionaisComunidade();

	public Territorio(String nome, List<Municipio> municipioList, Integer numeroComunidades) {
		super();
		this.nome = nome;
		this.municipioList = municipioList;
		this.numeroComunidades = numeroComunidades;
	}

	public Territorio() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Municipio> getMunicipioList() {
		return municipioList;
	}

	public void setMunicipioList(List<Municipio> municipioList) {
		this.municipioList = municipioList;
	}

	public Integer getNumeroComunidades() {
		return ObjectUtils.isEmpty(numeroComunidades)? 0 :numeroComunidades;
	}

	public void setNumeroComunidades(Integer numeroComunidades) {
		this.numeroComunidades = numeroComunidades;
	}

	public List<Quilombo> getQuilombos() {
		return quilombos;
	}

	public void setQuilombos(List<Quilombo> quilombos) {
		this.quilombos = quilombos;
	}

	public List<Ccdru> getCcdrus() {
		return ccdrus;
	}

	public void setCcdrus(List<Ccdru> ccdrus) {
		this.ccdrus = ccdrus;
	}

	public List<DecretoDesapropriatorio> getDecretosDesapropriatorios() {
		return decretosDesapropriatorios;
	}

	public void setDecretosDesapropriatorios(List<DecretoDesapropriatorio> decretosDesapropriatorios) {
		this.decretosDesapropriatorios = decretosDesapropriatorios;
	}

	public List<EditalComunicacao> getEditaisComunicacao() {
		return editaisComunicacao;
	}

	public void setEditaisComunicacao(List<EditalComunicacao> editaisComunicacao) {
		this.editaisComunicacao = editaisComunicacao;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public List<PortariaReconhecimento> getPortariasReconhecimento() {
		return portariasReconhecimento;
	}

	public void setPortariasReconhecimento(List<PortariaReconhecimento> portariasReconhecimento) {
		this.portariasReconhecimento = portariasReconhecimento;
	}

	public List<ProcessoAdministrativo> getProcessosAdministrativos() {
		return processosAdministrativos;
	}

	public void setProcessosAdministrativos(List<ProcessoAdministrativo> processosAdministrativos) {
		this.processosAdministrativos = processosAdministrativos;
	}

	public List<TituloPropriedade> getTituloPropriedade() {
		return tituloPropriedade;
	}

	public void setTituloPropriedade(List<TituloPropriedade> tituloPropriedade) {
		this.tituloPropriedade = tituloPropriedade;
	}

	public List<AssentamentoQuilombola> getAssentamentos() {
		return assentamentos;
	}

	public void setAssentamentos(List<AssentamentoQuilombola> assentamentos) {
		this.assentamentos = assentamentos;
	}

	public DadosAdicionaisComunidade getDadosAdicionais() {
		return dadosAdicionais;
	}

	public void setDadosAdicionais(DadosAdicionaisComunidade dadosAdicionais) {
		this.dadosAdicionais = dadosAdicionais;
	}

}
