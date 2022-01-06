package br.org.quilombola.model.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import br.org.quilombola.arquitetura.enums.Estado;

public class CodigoIbgeToEstadoConverter extends StdConverter<Integer, Estado> {

	@Override
	public Estado convert(Integer codIbge) {
		return Estado.getEstadoByCodigoIbge(codIbge);
	}

	
    
}
