package br.org.quilombola.model.converter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.util.StdConverter;

import br.org.quilombola.model.entity.Territorio;

public class TerritorioConverterForQuilombo extends StdConverter<List<Territorio>, List<Territorio>> {

	@Override
	public List<Territorio> convert(List<Territorio> listIn) {
		List<Territorio> listOut = new ArrayList<>();
		listIn.forEach(q ->{
			q.setQuilombos(new ArrayList<>());
			listOut.add(q);
		});
		return listOut;
	}
}
