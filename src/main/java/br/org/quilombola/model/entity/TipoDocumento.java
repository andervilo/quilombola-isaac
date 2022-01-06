package br.org.quilombola.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "tipo_documento")
public class TipoDocumento extends BaseEntity{

	private static final long serialVersionUID = 3100667697346067766L;
	
	@Column(name = "nome")
	private String nome;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="tipoDocumento") 
	private List<Documento> documentos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	
	

}
