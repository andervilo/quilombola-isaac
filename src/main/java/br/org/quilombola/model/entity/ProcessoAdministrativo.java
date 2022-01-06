package br.org.quilombola.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.org.quilombola.arquitetura.BaseEntity;
import br.org.quilombola.enums.AmbitoProcessoEnum;

@Entity
@Table(name = "processo_administrativo")
public class ProcessoAdministrativo extends BaseEntity{

	private static final long serialVersionUID = -9140651357038923248L;
	
    @Column(name = "ambito")
	private String ambito;
	
	@Column(name = "numero_processo_reconhecimento")
	private String numeroProcessoReconhecimento;
	
	@Column(name = "link_processo_sei", columnDefinition="text")
	private String linkProcessoSei;
	
	@Column(name = "link_processo_sei2", columnDefinition="text")
	private String linkProcessoSei2;
	
	@Column(name = "link_processo_sei3", columnDefinition="text")
	private String linkProcessoSei3;
	
	@Column(name = "localizacao_acervo_fundiario", columnDefinition="text")
	private String localizacaoAcervoFundiario;

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


	public AmbitoProcessoEnum getAmbito() {
		if(this.ambito == null)
			return null;
		return AmbitoProcessoEnum.valueOf(this.ambito);
	}

//	@JsonDeserialize(converter = AmbitoToStringConverter.class)
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	public String getNumeroProcessoReconhecimento() {
		return numeroProcessoReconhecimento;
	}

	public void setNumeroProcessoReconhecimento(String numeroProcessoReconhecimento) {
		this.numeroProcessoReconhecimento = numeroProcessoReconhecimento;
	}

	public String getLinkProcessoSei() {
		return linkProcessoSei;
	}

	public void setLinkProcessoSei(String linkProcessoSei) {
		this.linkProcessoSei = linkProcessoSei;
	}

	public String getLocalizacaoAcervoFundiario() {
		return localizacaoAcervoFundiario;
	}

	public void setLocalizacaoAcervoFundiario(String localizacaoAcervoFundiario) {
		this.localizacaoAcervoFundiario = localizacaoAcervoFundiario;
	}

	public String getLinkProcessoSei2() {
		return linkProcessoSei2;
	}

	public void setLinkProcessoSei2(String linkProcessoSei2) {
		this.linkProcessoSei2 = linkProcessoSei2;
	}

	public String getLinkProcessoSei3() {
		return linkProcessoSei3;
	}

	public void setLinkProcessoSei3(String linkProcessoSei3) {
		this.linkProcessoSei3 = linkProcessoSei3;
	}
	
	
}
