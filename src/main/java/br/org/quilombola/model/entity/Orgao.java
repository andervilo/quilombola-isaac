package br.org.quilombola.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.org.quilombola.arquitetura.BaseEntity;


@Entity
@Table(name = "orgao")
public class Orgao  extends BaseEntity{
    
	private static final long serialVersionUID = -3948749590967784970L;
    
    @Column(name = "nome")
    private String nome;

    public Orgao() {
    }

    public Orgao(Long id) {
        this.id = id;
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
}

