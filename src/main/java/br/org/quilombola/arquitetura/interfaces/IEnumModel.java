package br.org.quilombola.arquitetura.interfaces;

import java.io.Serializable;

public interface IEnumModel<T extends Serializable> {

	T getValor();

	String getDescricao();

}