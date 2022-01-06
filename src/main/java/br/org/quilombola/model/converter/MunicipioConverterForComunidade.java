package br.org.quilombola.model.converter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.util.StdConverter;

import br.org.quilombola.model.entity.Municipio;

public class MunicipioConverterForComunidade extends StdConverter<List<Municipio>, List<Municipio>> {

	@Override
	public List<Municipio> convert(List<Municipio> listIN) {
		List<Municipio> listOUT = new ArrayList<Municipio>();
		
		listIN.forEach(m -> {
			Municipio municipio = new Municipio();
			municipio.setId(m.getId());
			listOUT.add(municipio);
		});
		return listOUT;
	}


	

    
}
