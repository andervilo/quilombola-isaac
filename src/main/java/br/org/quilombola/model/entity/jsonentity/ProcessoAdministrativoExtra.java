package br.org.quilombola.model.entity.jsonentity;

import java.io.Serializable;

public class ProcessoAdministrativoExtra implements Serializable {

	private static final long serialVersionUID = 685515421978038379L;

	private String link;

	public ProcessoAdministrativoExtra() {
		super();
	}

	public ProcessoAdministrativoExtra(String link) {
		super();
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
