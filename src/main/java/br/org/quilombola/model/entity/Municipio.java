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

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.org.quilombola.arquitetura.BaseEntity;
import br.org.quilombola.arquitetura.enums.Estado;
import br.org.quilombola.model.converter.EstadoEnumToSiglaConverter;
import br.org.quilombola.model.converter.SuperintendenciaConverter;

@Entity
@Table(name = "municipio")
public class Municipio extends BaseEntity{

	private static final long serialVersionUID = 8609380594975131746L;
	
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "codigo_ibge")
	private Integer codigoIbge;
	
    @Column(name = "estado")
	private String estado;
	
	@Column(name = "amazonia_legal") 
	private Boolean amazoniaLegal;

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
	@JoinTable(name = "territorio_municipio",
			joinColumns = {
					@JoinColumn(name = "fk_municipio", referencedColumnName = "id")
	        }, 
			inverseJoinColumns = {
					@JoinColumn(name = "fk_territorio", referencedColumnName = "id")
	        })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Territorio> territorioList = new ArrayList<>();

	@JsonIgnoreProperties(
			value = {
					"municipios"
			},
			allowSetters = true
	)
	@JoinTable(name = "comunidade_municipio", 
			joinColumns = {
					@JoinColumn(name = "fk_municipio", referencedColumnName = "id")
	        }, 
			inverseJoinColumns = {
					@JoinColumn(name = "fk_comunidade", referencedColumnName = "id")
	        })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Comunidade> comunidades = new ArrayList<>();
	
	@JsonIgnoreProperties(
			value = {
					"municipios"
			},
			allowSetters = true
	)
	@JoinTable(name = "municipio_superintendencia", 
			joinColumns = {
					@JoinColumn(name = "fk_municipio", referencedColumnName = "id")
	        }, 
			inverseJoinColumns = {
					@JoinColumn(name = "fk_superintendencia", referencedColumnName = "id")
	        })
    @ManyToMany(fetch = FetchType.LAZY)
	private List<Superintendencia> superintendencias;
		
	public Municipio() {
	}
	
	public List<Comunidade> getComunidades() {
		return comunidades;
	}

	public void setComunidades(List<Comunidade> comunidades) {
		this.comunidades = comunidades;
	}
	
	public void removeComunidade(Comunidade comunidade) {
		if(!this.comunidades.isEmpty() && this.comunidades.contains(comunidade)){
			this.comunidades.remove(comunidade);
		}
	}
	
	public void addComunidade(Comunidade comunidade) {
		if(!this.comunidades.contains(comunidade)) {
			this.comunidades.add(comunidade);
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}
	
	public Estado getEstado() {
		return estado == null ? null : Estado.valueOf(estado);
	}

	@JsonDeserialize(converter = EstadoEnumToSiglaConverter.class)
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getAmazoniaLegal() {
		return amazoniaLegal;
	}

	public void setAmazoniaLegal(Boolean amazoniaLegal) {
		this.amazoniaLegal = amazoniaLegal;
	}
		
	public List<Territorio> getTerritorioList() {
		return territorioList;
	}

    public void setTerritorioList(List<Territorio> territorioList) {
        this.territorioList = territorioList;
    }
   
	public void removeTerritorio(Territorio territorio) {
		if(!this.territorioList.isEmpty() && this.territorioList.contains(territorio)){
			this.territorioList.remove(territorio);
		}
	}
	
	public void addTerritorio(Territorio territorio) {
		if(!this.territorioList.contains(territorio)) {
			this.territorioList.add(territorio);
		}
	}

//	@JsonSerialize(converter = SuperintendenciaConverter.class)
	public List<Superintendencia> getSuperintendencias() {
		return superintendencias;
	}

	public void setSuperintendencias(List<Superintendencia> superintendencias) {
		this.superintendencias = superintendencias;
	}
	
}
