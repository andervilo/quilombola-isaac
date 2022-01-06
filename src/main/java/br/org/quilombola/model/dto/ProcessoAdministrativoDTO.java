package br.org.quilombola.model.dto;

public class ProcessoAdministrativoDTO {

	private Long id;
	
	private String ambito;
	
	private String numeroProcessoReconhecimento;
	
	private String linkProcessoSei;
	
	private String linkProcessoSei2;
	
	private String linkProcessoSei3;
	
	private String localizacaoAcervoFundiario;
	
	private Long comunidade;
	
	
	public ProcessoAdministrativoDTO() {
		super();
	}

	private String secaoDO;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getComunidade() {
        return comunidade;
    }

    public void setComunidade(Long comunidade) {
        this.comunidade = comunidade;
    }


	public String getAmbito() {
		return this.ambito;
	}

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

	@Override
	public String toString() {
		return "ProcessoAdministrativoDTO [id=" + id + ", ambito=" + ambito + ", numeroProcessoReconhecimento="
				+ numeroProcessoReconhecimento + ", linkProcessoSei=" + linkProcessoSei + ", linkProcessoSei2="
				+ linkProcessoSei2 + ", linkProcessoSei3=" + linkProcessoSei3 + ", localizacaoAcervoFundiario="
				+ localizacaoAcervoFundiario + ", comunidade=" + comunidade + "]";
	}

	
	
}
