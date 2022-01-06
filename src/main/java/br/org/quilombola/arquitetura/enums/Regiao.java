package br.org.quilombola.arquitetura.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Regiao {
	NORTE(1,"Norte"),
	NORDESTE(2,"Nordeste"),
	CENTROOESTE(3,"Centro-Oeste"),
	SUDESTE(4,"Suldeste"),
	SUL(5,"Sul");
	
	private final int codigo;
	private final String nome;

	private Regiao(int codigoIbge,String nome) {
		this.codigo = codigoIbge;
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
	
	public int getCodigo(){
		return codigo;
	}
}
