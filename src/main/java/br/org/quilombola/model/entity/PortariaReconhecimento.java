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
@Table(name = "portaria_reconhecimento")
public class PortariaReconhecimento extends BaseEntity{

	private static final long serialVersionUID = 355112975485011684L;

	@Column(name = "numero_portaria_reconhecimento")
	private String numeroPortariaReconhecimento;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "data_portaria_reconhecimento")
	private Date dataPortariaReconhecimento;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
	@Column(name = "portaria_reconhecimento_dou")
	private Date portariaReconhecimentoDiarioOficialUniao;

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
	
	@Column(name = "link_publicacao_5")
	private String linkPublicacao_5;
	
	@Column(name = "link_publicacao_6")
	private String linkPublicacao_6;
	
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
	
	public String getNumeroPortariaReconhecimento() {
		return numeroPortariaReconhecimento;
	}

	public void setNumeroPortariaReconhecimento(String numeroPortariaReconhecimento) {
		this.numeroPortariaReconhecimento = numeroPortariaReconhecimento;
	}

	public Date getDataPortariaReconhecimento() {
		return dataPortariaReconhecimento;
	}

	public void setDataPortariaReconhecimento(Date dataPortariaReconhecimento) {
		this.dataPortariaReconhecimento = dataPortariaReconhecimento;
	}

	public Date getPortariaReconhecimentoDiarioOficialUniao() {
		return portariaReconhecimentoDiarioOficialUniao;
	}

	public void setPortariaReconhecimentoDiarioOficialUniao(Date portariaReconhecimentoDoudiarioOficialUniao) {
		this.portariaReconhecimentoDiarioOficialUniao = portariaReconhecimentoDoudiarioOficialUniao;
	}

	public Territorio getTerritorio() {
        return territorio;
    }

    public void setTerritorio(Territorio territorio) {
        this.territorio = territorio;
    }

	public String getLinkPublicacao_5() {
		return linkPublicacao_5;
	}

	public void setLinkPublicacao_5(String linkPublicacao_5) {
		this.linkPublicacao_5 = linkPublicacao_5;
	}

	public String getLinkPublicacao_6() {
		return linkPublicacao_6;
	}

	public void setLinkPublicacao_6(String linkPublicacao_6) {
		this.linkPublicacao_6 = linkPublicacao_6;
	}

}
