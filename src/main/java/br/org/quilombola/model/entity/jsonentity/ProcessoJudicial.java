package br.org.quilombola.model.entity.jsonentity;

import java.io.Serializable;

public class ProcessoJudicial implements Serializable {

	private static final long serialVersionUID = 7629877860089463387L;
	
	private String link;

	public ProcessoJudicial() {
		super();
	}

	public ProcessoJudicial(String link) {
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
