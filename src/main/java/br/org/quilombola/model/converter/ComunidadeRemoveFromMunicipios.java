package br.org.quilombola.model.converter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.util.StdConverter;

import br.org.quilombola.model.entity.Municipio;

public class ComunidadeRemoveFromMunicipios extends StdConverter<List<Municipio>, List<Municipio>> {

	@Override
	public List<Municipio> convert(List<Municipio> listIn) {
		List<Municipio> listOut = new ArrayList<Municipio>();

		listIn.forEach(municipio -> {
			municipio.setComunidades(new ArrayList<>());
			listOut.add(municipio);
		});
		return listOut;
	}

	
 
}
