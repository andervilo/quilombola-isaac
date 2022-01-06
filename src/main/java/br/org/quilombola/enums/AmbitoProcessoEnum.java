package br.org.quilombola.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AmbitoProcessoEnum {
	FEDERAL(1, "Federal", "FEDERAL"),
	ESTADUAL(2, "Estadual", "ESTADUAL")
	;
	
	private final int codigo;
	private final String nome;
	private final String label;
	
	private AmbitoProcessoEnum(int codigo, String nome, String label) {
		this.codigo = codigo;
		this.nome = nome;
		this.label = label;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}
	
	public String getLabel() {
		return label;
	}
	
	
}
