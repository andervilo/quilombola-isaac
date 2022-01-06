package br.org.quilombola.model.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.StdConverter;

public class EstadoEnumToSetCodIbgeConverter extends StdConverter<JsonNode, Integer> {

	@Override
	public Integer convert(JsonNode value) {
		if(value.has("codigoIbge")) {
			return value.get("codigoIbge").asInt();
		}
		System.out.println(value);
		return value.asInt();
	}

	  
}
