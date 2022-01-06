package br.org.quilombola.arquitetura.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PerfisEnum {
	
	MASTER(1, "Perfil com acesso irrestrito ao sistema."),
	ADMINISTRADOR(2, "Perfil com acesso irrestrito ao sistema, exceto configuração."),
	DIGITADOR(3, "Perfil com acesso apenas para inclusão de dados."),
	PESQUISADOR(4, "Perfil com aceeso apenas para consultar dados.");
	
	private Integer id;
	private String descricao;
	
	private PerfisEnum(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}


	public String getDescricao() {
		return descricao;
	}

	
}
