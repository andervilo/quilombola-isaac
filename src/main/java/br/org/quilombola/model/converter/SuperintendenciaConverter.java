package br.org.quilombola.model.converter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.util.StdConverter;

import br.org.quilombola.model.entity.Superintendencia;

public class SuperintendenciaConverter extends StdConverter<List<Superintendencia>, List<Superintendencia>> {

	@Override
	public List<Superintendencia> convert(List<Superintendencia> listIn) {
		List<Superintendencia> listOut = new ArrayList<>();
		listIn.forEach(s ->{
			s.setMunicipios(new ArrayList<>());
			listOut.add(s);
		});
		return listOut;
	}
    
}
