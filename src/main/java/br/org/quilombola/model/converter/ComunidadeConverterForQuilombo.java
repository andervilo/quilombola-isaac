package br.org.quilombola.model.converter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.util.StdConverter;

import br.org.quilombola.model.entity.Comunidade;

public class ComunidadeConverterForQuilombo extends StdConverter<List<Comunidade>, List<Comunidade>> {

	@Override
	public List<Comunidade> convert(List<Comunidade> listIn) {
		List<Comunidade> listOut = new ArrayList<>();
		listIn.forEach(item ->{
			item.setQuilombos(new ArrayList<>());
			item.getMunicipios().forEach(municipio -> {
				municipio.setComunidades(new ArrayList<>());
				municipio.setTerritorioList(new ArrayList<>());
			});
			listOut.add(item);
		});
		return listOut;
	}
    
}
