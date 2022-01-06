package br.org.quilombola.arquitetura.enums;

import br.org.quilombola.arquitetura.interfaces.IEnumModel;

public enum DiasSemanaEnum implements IEnumModel<Integer> {
	SEGUNDA(1,"Segunda-Feira"),
	TERCA(2,"Terça-Feira"),
	QUATA(3,"Quarta-Feira"),
	QUINTA(4,"Quinta-Feira"),
	SEXTA(5,"Sexta-Feira"),
	SABADO(6,"Sábado"),
	DOMINGO(7,"Domingo")	;
	
	private Integer id;
	private String nomeDia;
	
	DiasSemanaEnum(Integer _id, String _nomeDia){
		id = _id;
		nomeDia = _nomeDia;
	}

	@Override
	public Integer getValor() {
		return id;
	}

	@Override
	public String getDescricao() {
		return nomeDia;
	}
	
	public String getDescricaoCurta() {
		return getDescricao().split("-")[0];
	}

}
