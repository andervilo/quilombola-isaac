package br.org.quilombola.model.converter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.util.StdConverter;

import br.org.quilombola.model.entity.Comunidade;

public class ListComuniadeExcludeDadosAdicionaisConverter extends StdConverter<List<Comunidade>, List<Comunidade>> {

	@Override
	public List<Comunidade> convert(List<Comunidade> listIn) {
		List<Comunidade> listOut = new ArrayList<Comunidade>();
		
		listIn.forEach(comunidade -> {
			comunidade.getDadosAdicionais().setComunidade(comunidade);
			listOut.add(comunidade);
		});
		
		return listOut;
	}

	
 
}
