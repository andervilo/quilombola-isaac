package br.org.quilombola.model.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.StdConverter;

public class EstadoEnumToSiglaConverter extends StdConverter<JsonNode, String> {

	@Override
	public String convert(JsonNode value) {
		if(value.has("sigla")) {
			return value.get("sigla").asText();
		}
		return value.asText();
	}

	
 
}
