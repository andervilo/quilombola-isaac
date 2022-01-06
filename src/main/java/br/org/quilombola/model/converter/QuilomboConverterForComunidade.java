package br.org.quilombola.model.converter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.util.StdConverter;

import br.org.quilombola.model.entity.Quilombo;

public class QuilomboConverterForComunidade extends StdConverter<List<Quilombo>, List<Quilombo>> {

	@Override
	public List<Quilombo> convert(List<Quilombo> listIn) {
		List<Quilombo> listOut = new ArrayList<>();
		listIn.forEach(q ->{
			q.setComunidades(new ArrayList<>());
			listOut.add(q);
		});
		return listOut;
	}
    
}
