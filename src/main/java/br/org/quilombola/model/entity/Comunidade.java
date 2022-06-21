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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.org.quilombola.arquitetura.BaseEntity;
import br.org.quilombola.model.converter.ComunidadeRemoveFromMunicipios;
import br.org.quilombola.model.converter.MunicipioConverterForComunidade;
import br.org.quilombola.model.converter.QuilomboConverterForComunidade;
import br.org.quilombola.model.converter.QuilomboConverterSetForComunidade;
import br.org.quilombola.model.entity.jsonentity.ProcessoAdministrativoExtra;

@Entity
@Table(name = "comunidade")
public class Comunidade extends BaseEntity {

	private static final long serialVersionUID = -1475697755981678560L;

	@Column(name = "nome", length = 1000)
	private String nome;

	@Column(name = "numero_comunidades")
	private Integer numeroComunidades;

	@JsonIgnoreProperties(
			value = {
					"comunidade"
			},
			allowSetters = true
	)
	@OneToOne(mappedBy = "comunidade", cascade = CascadeType.ALL, optional = true)
	private DadosAdicionaisComunidade dadosAdicionais = new DadosAdicionaisComunidade();

	@JsonIgnoreProperties(
			value = {
					"comunidades", "territorioList"
			},
			allowSetters = true
	)
	@JoinTable(name = "quilombola_comunidade", joinColumns = {
			@JoinColumn(name = "fk_comunidade", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_quilombo", referencedColumnName = "id") })
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Quilombo> quilombolaList;

	@JsonIgnoreProperties(
			value = {
					"comunidades", "territorioList","superintendencias"
			},
			allowSetters = true
	)
	@JoinTable(name = "comunidade_municipio", joinColumns = {
			@JoinColumn(name = "fk_comunidade", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_municipio", referencedColumnName = "id") })
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Municipio> municipios = new ArrayList<>();

	@JsonIgnoreProperties(
			value = {
					"comunidade"
			},
			allowSetters = true
	)
	@OneToMany(mappedBy = "comunidade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Certificado> certificados = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumeroComunidades() {
		return numeroComunidades;
	}

	public void setNumeroComunidades(Integer numeroComunidades) {
		this.numeroComunidades = numeroComunidades;
	}

	public List<Quilombo> getQuilombos() {
		return quilombolaList;
	}

	public void setQuilombos(List<Quilombo> quilombos) {
		List<Quilombo> listOut = new ArrayList<>();
		quilombos.forEach(q ->{
			Quilombo quilombo = new Quilombo();;
			quilombo.setId(q.getId());
			listOut.add(q);
		});
		this.quilombolaList = listOut;
	}

	public void addQuilombo(Quilombo quilombo) {
		if (!getQuilombos().contains(quilombo)) {
			getQuilombos().add(quilombo);
		}
		if (!quilombo.getComunidades().contains(this)) {
			quilombo.getComunidades().add(this);
		}
	}

	public void removeQuilombo(Quilombo quilombo) {
		if (getQuilombos().contains(quilombo)) {
			getQuilombos().remove(quilombo);
		}
		if (quilombo.getComunidades().contains(this)) {
			quilombo.getComunidades().remove(this);
		}
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public void addMunicipio(Municipio municipio) {
		if (!getMunicipios().contains(municipio)) {
			if (!municipio.getComunidades().contains(this)) {
				municipio.getComunidades().add(this);
			}
			getMunicipios().add(municipio);
		}
	}

	public void removeMunicipio(Municipio municipio) {
		if (getMunicipios().contains(municipio)) {
			if (municipio.getComunidades().contains(this)) {
				municipio.getComunidades().remove(this);
			}
			getMunicipios().remove(municipio);
		}
	}

	public DadosAdicionaisComunidade getDadosAdicionais() {
		return dadosAdicionais;
	}

	public void setDadosAdicionais(DadosAdicionaisComunidade dadosAdicionais) {
		dadosAdicionais.setComunidade(this);
		this.dadosAdicionais = dadosAdicionais;
	}

	public List<Certificado> getCertificados() {
		return certificados;
	}

	public void setCertificados(List<Certificado> certificados) {
		this.certificados = certificados;
	}
	
	@Transient
	public List<Territorio> getTerritorios() {
		List<Territorio> territorios = new ArrayList<Territorio>();
		return territorios;
	}

}
