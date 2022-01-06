package br.org.quilombola.model.entity;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "documento")
public class Documento extends BaseEntity{

	private static final long serialVersionUID = 5084401182652240239L;
	
	@Column(name = "nome_documento")
	private String nomeDocumento;
	
	@Column(name = "caminho")
	private String caminho;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tipo_documento")
	private TipoDocumento tipoDocumento;
	
	@JsonIgnore
	@JoinColumn(name = "fk_quilombola", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Quilombo quilombo;

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public TipoDocumento getTipoDocumento() {
		tipoDocumento.setDocumentos(new ArrayList<Documento>());
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Quilombo getQuilombo() {
		return quilombo;
	}

	public void setQuilombola(Quilombo quilombo) {
		this.quilombo = quilombo;
	}
}
