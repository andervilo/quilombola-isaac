package br.org.quilombola.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.*;

import br.org.quilombola.arquitetura.BaseEntity;

@Entity
@Table(name = "decreto_desapropriatorio")
public class DecretoDesapropriatorio extends BaseEntity{

	private static final long serialVersionUID = -7970879153249653298L;
	
	@Column(name = "numero_decreto_desapropriacao")
	private String numeroDecretoDesapropriacao;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "data_decreto_deaproriacaoa")
	private Date dataDecretoDeaproriacaoa;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "decreto_desapropriacao_dou")
	private Date dataDecretoDesapropriacaoDiarioOficialUniao;

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "territorio_id", nullable = false)
    private Territorio territorio;
	
	private String nomeDocumento;
	
	@JsonIgnore
	private String extensaoDocumento;
	
	@JsonIgnore
    private byte[] binarioDocumento;
	
	@Column(name = "link_publicacao_1")
	private String linkPublicacao_1;
	
	@Column(name = "link_publicacao_2")
	private String linkPublicacao_2;
	
	@Column(name = "link_publicacao_3")
	private String linkPublicacao_3;
	
	@Column(name = "secao_do")
	private String secaoDO;
	
	@Column(name = "folha_do")
	private String folhaDO;
	
	
	public String getSecaoDO() {
		return secaoDO;
	}

	public void setSecaoDO(String secaoDO) {
		this.secaoDO = secaoDO;
	}

	public String getFolhaDO() {
		return folhaDO;
	}

	public void setFolhaDO(String folhaDO) {
		this.folhaDO = folhaDO;
	}
	
	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public String getExtensaoDocumento() {
		return extensaoDocumento;
	}

	public void setExtensaoDocumento(String extensaoDocumento) {
		this.extensaoDocumento = extensaoDocumento;
	}

	public byte[] getBinarioDocumento() {
		return binarioDocumento;
	}

	public void setBinarioDocumento(byte[] binarioDocumento) {
		this.binarioDocumento = binarioDocumento;
	}
	
	public Territorio getTerritorio() {
        return territorio;
    }

    public void setTerritorio(Territorio territorio) {
        this.territorio = territorio;
    }

	public String getNumeroDecretoDesapropriacao() {
		return numeroDecretoDesapropriacao;
	}

	public void setNumeroDecretoDesapropriacao(String numeroDecretoDesapropriacao) {
		this.numeroDecretoDesapropriacao = numeroDecretoDesapropriacao;
	}

	public Date getDataDecretoDeaproriacaoa() {
		return dataDecretoDeaproriacaoa;
	}

	public void setDataDecretoDeaproriacaoa(Date dataDecretoDeaproriacaoa) {
		this.dataDecretoDeaproriacaoa = dataDecretoDeaproriacaoa;
	}

	public Date getDataDecretoDesapropriacaoDiarioOficialUniao() {
		return dataDecretoDesapropriacaoDiarioOficialUniao;
	}

	public void setDataDecretoDesapropriacaoDiarioOficialUniao(Date dataDecretoDesapropriacaoDiarioOficialUniao) {
		this.dataDecretoDesapropriacaoDiarioOficialUniao = dataDecretoDesapropriacaoDiarioOficialUniao;
	}

	public String getLinkPublicacao_1() {
		return linkPublicacao_1;
	}

	public void setLinkPublicacao_1(String linkPublicacao_1) {
		this.linkPublicacao_1 = linkPublicacao_1;
	}

	public String getLinkPublicacao_2() {
		return linkPublicacao_2;
	}

	public void setLinkPublicacao_2(String linkPublicacao_2) {
		this.linkPublicacao_2 = linkPublicacao_2;
	}

	public String getLinkPublicacao_3() {
		return linkPublicacao_3;
	}

	public void setLinkPublicacao_3(String linkPublicacao_3) {
		this.linkPublicacao_3 = linkPublicacao_3;
	}

	
}
