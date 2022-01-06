package br.org.quilombola.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.org.quilombola.arquitetura.BaseEntity;
import br.org.quilombola.arquitetura.enums.Estado;
import br.org.quilombola.model.converter.EstadoEnumToSetCodIbgeConverter;


@Entity
@Table(name = "superintendencia")
public class Superintendencia  extends BaseEntity{
    
	private static final long serialVersionUID = -911781035392670498L;

	@Column(name = "nome")
    private String nome;
	
	@JsonIgnoreProperties(
			value = {
					"superintendencias"
			},
			allowSetters = true
	)
	@JoinTable(name = "municipio_superintendencia", 
			joinColumns = {
					@JoinColumn(name = "fk_superintendencia", referencedColumnName = "id")
	        }, 
			inverseJoinColumns = {
					@JoinColumn(name = "fk_municipio", referencedColumnName = "id")
	        })
    @ManyToMany(fetch = FetchType.LAZY)
	private List<Municipio> municipios;
	
	private Integer uf;

    public Superintendencia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public Estado getUf() {
		if(Estado.getEstadoByCodigoIbge(uf) != null) {
			return Estado.getEstadoByCodigoIbge(uf);
		}
		return null;
	}

	@JsonDeserialize(converter = EstadoEnumToSetCodIbgeConverter.class)
	public void setUf(Integer uf) {
		this.uf = uf;
	}
	
	
}

