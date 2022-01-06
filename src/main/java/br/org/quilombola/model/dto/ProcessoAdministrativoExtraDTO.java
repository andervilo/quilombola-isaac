package br.org.quilombola.model.dto;

import java.io.Serializable;

public class ProcessoAdministrativoExtraDTO implements Serializable {

	private static final long serialVersionUID = 685515421978038379L;

	private String link;

	public ProcessoAdministrativoExtraDTO() {
		super();
	}

	public ProcessoAdministrativoExtraDTO(String link) {
		super();
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "ProcessoAdministrativoExtraDTO [link=" + link + "]";
	}

}
