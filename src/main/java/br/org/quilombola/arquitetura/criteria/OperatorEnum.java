package br.org.quilombola.arquitetura.criteria;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OperatorEnum {
	IGUAL           ("Igual", "IGUAL"),
	MAIOR           ("Maior que", "MAIOR"),
	MAIOR_IGUAL     ("Maior ou igual a", "MAIOR_IGUAL"),
	MENOR           ("Menor", "MENOR"),
	MENOR_IGUAL     ("Menor ou igual a", "MENOR_IGUAL"),
	CONTENDO        ("Contendo", "CONTENDO"),
	CONTENDO_STR    ("Contendo String", "CONTENDO_STR"),
	COMECANDO_COM   ("Começando com", "COMECANDO_COM"),
	TERMINANDO_COM  ("Terminando com", "TERMINANDO_COM"),
	FALSO           ("Não", "FALSO"),
	VERDADEIRO      ("Sim", "VERDADEIRO"),
	NULO            ("Nulo", "NULO"),
	ENTRE     ("Período", "ENTRE_DATAS")
	;
	
	private final String label;
	private final String comparador;
	
	private OperatorEnum(String label, String comparador) {
		this.label = label;
		this.comparador = comparador;
	}
	
	public static OperatorEnum getOperation(Object anObject) {
		for (OperatorEnum operation : values()) {
			if(operation.name().equals(anObject)) {
				return operation;
			}
		}
		return null;
	}

	public String getLabel() {
		return label;
	}

	public String getComparador() {
		return comparador;
	}
	
	
	
	
}
