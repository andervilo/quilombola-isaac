package br.org.quilombola.model.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.StdConverter;

public class TipoAssentamentoToStringConverter extends StdConverter<JsonNode, String> {

	@Override
	public String convert(JsonNode value) {
		if(value.has("label")) {
			return value.get("label").asText();
		}
		return value.asText();
	}

	
 
}
