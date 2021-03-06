package br.org.quilombola.arquitetura.security.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
public class Permissao {

	@Id
//	@GeneratedValue( strategy = GenerationType.)
	private Long id;
	
	private String descricao;
	

	public Permissao(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public Permissao() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getCodigo() {
		return id;
	}

	public void setCodigo(Long codigo) {
		this.id = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permissao other = (Permissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
