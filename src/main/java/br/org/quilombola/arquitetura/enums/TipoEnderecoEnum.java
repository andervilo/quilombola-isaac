package br.org.quilombola.arquitetura.enums;

import br.org.quilombola.arquitetura.interfaces.IEnumModel;

public enum TipoEnderecoEnum implements IEnumModel<Integer>{
	RESIDENCIAL(0,"Residencial"),
	COMERCIAL(1,"Comercial")
	;
	
	private Integer valor;

	private String descricao;

	private TipoEnderecoEnum(Integer valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	@Override
	public Integer getValor() {
		return this.valor;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

}
