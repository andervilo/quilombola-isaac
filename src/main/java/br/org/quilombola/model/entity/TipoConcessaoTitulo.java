package br.org.quilombola.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.org.quilombola.arquitetura.BaseEntity;
@Entity
@Table(name = "tipo_concessao_titulo")
public class TipoConcessaoTitulo extends BaseEntity{

	private static final long serialVersionUID = -1568317102724176895L;
	
	@Column(name = "nome")
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
