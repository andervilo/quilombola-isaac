package br.org.quilombola.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.org.quilombola.arquitetura.BaseEntity;
import br.org.quilombola.model.converter.ComunidadeConverterForQuilombo;
import br.org.quilombola.model.converter.TerritorioConverterForQuilombo;

@Entity
@Table(name = "quilombola")
public class Quilombo extends BaseEntity{

	private static final long serialVersionUID = 7357413308783628123L;
	
	@Column(name = "identificacao_quilombola")
	private String identificacaoQuilombola;

	@JsonIgnore
	@JoinTable(name = "quilombola_comunidade", 
			joinColumns = {
					@JoinColumn(name = "fk_quilombo", referencedColumnName = "id") 
			}, 
			inverseJoinColumns = {
					@JoinColumn(name = "fk_comunidade", referencedColumnName = "id") 
			})
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Comunidade> comunidades = new ArrayList<Comunidade>();

	@JsonIgnore
	@JoinTable(name = "quilombo_territorio", 
			joinColumns = {
					@JoinColumn(name = "fk_quilombo", referencedColumnName = "id")
	        }, 
			inverseJoinColumns = {
					@JoinColumn(name = "fk_territorio", referencedColumnName = "id")
	        })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Territorio> territorioList = new ArrayList<>();

	public String getIdentificacaoQuilombola() {
		return identificacaoQuilombola;
	}

	public void setIdentificacaoQuilombola(String identificacaoQuilombola) {
		this.identificacaoQuilombola = identificacaoQuilombola;
	}
	
	//@Transactional
	//@JsonSerialize(converter = ComunidadeConverterForQuilombo.class)
	@JsonIgnoreProperties(
			value = {
					"quilombolaList"
			}
	)
	public List<Comunidade> getComunidades() {
		return comunidades;
	}

	public void setComunidades(List<Comunidade> comunidades) {
		this.comunidades = comunidades;
	}

	//@JsonSerialize(converter = TerritorioConverterForQuilombo.class)
	@JsonIgnoreProperties(
			value = {
					"quilombos"
			}
	)
	public List<Territorio> getTerritorioList() {
		return territorioList;
	}

	public void setTerritorioList(List<Territorio> territorioList) {
		this.territorioList = territorioList;
	}
	
}
