package br.org.quilombola.model.dto;

import br.org.quilombola.model.entity.DadosComunidadeVw;
import br.org.quilombola.model.entity.DadosTerritorioVw;

public class DadosConsolidadosDTO {

	private DadosComunidadeVw dadosComunidadeVw;

	private DadosTerritorioVw dadosTerritorioVw;

	public DadosConsolidadosDTO() {
		super();
	}

	public DadosConsolidadosDTO(DadosComunidadeVw comunidadeVw, DadosTerritorioVw dadosTerritorioVw) {
		super();
		this.dadosComunidadeVw = comunidadeVw;
		this.dadosTerritorioVw = dadosTerritorioVw;
	}

	public DadosComunidadeVw getDadosComunidadeVw() {
		return dadosComunidadeVw;
	}

	public void setDadosComunidadeVw(DadosComunidadeVw comunidadeVw) {
		this.dadosComunidadeVw = comunidadeVw;
	}

	public DadosTerritorioVw getDadosTerritorioVw() {
		return dadosTerritorioVw;
	}

	public void setDadosTerritorioVw(DadosTerritorioVw dadosTerritorioVw) {
		this.dadosTerritorioVw = dadosTerritorioVw;
	}

}
