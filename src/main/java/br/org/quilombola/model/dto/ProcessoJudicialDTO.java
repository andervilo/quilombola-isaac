package br.org.quilombola.model.dto;

import java.io.Serializable;

public class ProcessoJudicialDTO implements Serializable {

	private static final long serialVersionUID = 7629877860089463387L;

	private String link;

	public ProcessoJudicialDTO() {
		super();
	}

	public ProcessoJudicialDTO(String link) {
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
		return "ProcessoJudicialDTO [link=" + link + "]";
	}

}
