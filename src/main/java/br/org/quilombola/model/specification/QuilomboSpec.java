package br.org.quilombola.model.specification;

import java.text.Normalizer;

import org.springframework.data.jpa.domain.Specification;

import br.org.quilombola.model.entity.Quilombo;
import br.org.quilombola.model.entity.Quilombo_;

public class QuilomboSpec {
	
	private static String textoBusca = null;
	
	public static Specification<Quilombo> getQuilomboByNome(String nome){
		textoBusca = Normalizer
				.normalize(nome, Normalizer.Form.NFD)
		        .replaceAll("[^\\p{ASCII}]", "");
		
		return (root, query, cb)->{
			return cb.like(cb.function("unaccent", String.class, cb.upper(root.get(Quilombo_.identificacaoQuilombola))), "%"+textoBusca.toUpperCase()+"%");
		};
	}
	
	public static Specification<Quilombo> getQuilomboById(Long id){
		return (root, query, criteriaBuilder)->{
			return criteriaBuilder.equal(root.get(Quilombo_.id), id);
		};
	}

}
