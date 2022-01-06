package br.org.quilombola.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "contato")
public class Contato extends BaseEntity{

	private static final long serialVersionUID = 1767331882524651979L;
	
	private String nome;
	private String email;
	private String assunto;
	@Column(name = "mensagem", columnDefinition = "TEXT")
	private String mensagem;
	private Boolean respondido = false;
	private Date created_at = new Date();
	
	public Contato(String nome, String email, String assunto, String mensagem, Boolean respondido, Date created_at) {
		super();
		this.nome = nome;
		this.email = email;
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.respondido = respondido;
		this.created_at = created_at;
	}

	public Contato() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Boolean getRespondido() {
		return respondido;
	}

	public void setRespondido(Boolean respondido) {
		this.respondido = respondido;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Contato [nome=" + nome + ", email=" + email + ", assunto=" + assunto + ", mensagem=" + mensagem
				+ ", respondido=" + respondido + ", created_at=" + created_at + "]";
	}
}
