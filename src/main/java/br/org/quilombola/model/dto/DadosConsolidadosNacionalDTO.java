package br.org.quilombola.model.dto;

import java.io.Serializable;
import java.util.List;

import br.org.quilombola.model.entity.DadosComunidadeVw;
import br.org.quilombola.model.entity.DadosTerritorioVw;

public class DadosConsolidadosNacionalDTO implements Serializable {

	static final long serialVersionUID = -3720266500928482043L;

	private List<DadosComunidadeVw> dadosComunidadeVw;

	private List<DadosTerritorioVw> dadosTerritorioVw;

	public DadosConsolidadosNacionalDTO() {
		super();
	}

	public DadosConsolidadosNacionalDTO(List<DadosComunidadeVw> dadosComunidadeVw,
			List<DadosTerritorioVw> dadosTerritorioVw) {
		super();
		this.dadosComunidadeVw = dadosComunidadeVw;
		this.dadosTerritorioVw = dadosTerritorioVw;
	}

	public List<DadosComunidadeVw> getDadosComunidadeVw() {
		return dadosComunidadeVw;
	}

	public void setDadosComunidadeVw(List<DadosComunidadeVw> dadosComunidadeVw) {
		this.dadosComunidadeVw = dadosComunidadeVw;
	}

	public List<DadosTerritorioVw> getDadosTerritorioVw() {
		return dadosTerritorioVw;
	}

	public void setDadosTerritorioVw(List<DadosTerritorioVw> dadosTerritorioVw) {
		this.dadosTerritorioVw = dadosTerritorioVw;
	}

}
