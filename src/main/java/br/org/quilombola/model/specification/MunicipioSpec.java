package br.org.quilombola.model.specification;

import java.text.Normalizer;

import org.springframework.data.jpa.domain.Specification;

import br.org.quilombola.model.entity.Municipio;
import br.org.quilombola.model.entity.Municipio_;

public class MunicipioSpec {
	private static String textoBusca = null;
	
	public static Specification<Municipio> getMunicipioByNome(String nome){
		textoBusca = Normalizer
				.normalize(nome, Normalizer.Form.NFD)
		        .replaceAll("[^\\p{ASCII}]", "");
		
		return (root, query, cb)->{
			return cb.like(cb.function("unaccent", String.class, cb.upper(root.get(Municipio_.nome))), "%"+textoBusca.toUpperCase()+"%");
		};
	}
	
	public static Specification<Municipio> getMunicipioById(Long id){
		return (root, query, criteriaBuilder)->{
			return criteriaBuilder.equal(root.get(Municipio_.id), id);
		};
	}
	
	public static Specification<Municipio> getMunicipioLikeId(Long id){
		return (root, query, criteriaBuilder)->{
			return criteriaBuilder.like(root.get(Municipio_.id).as(String.class), "%"+String.valueOf(id)+"%");
		};
	}
	
	public static Specification<Municipio> getMunicipioByCodigoIbge(Integer codigoIbge){
		return (root, query, criteriaBuilder)->{
			return criteriaBuilder.equal(root.get(Municipio_.codigoIbge), codigoIbge);
		};
	}
	
	public static Specification<Municipio> getMunicipioByEstado(String estado){
		return (root, query, criteriaBuilder)->{
			return criteriaBuilder.equal(criteriaBuilder.upper(root.get(Municipio_.estado)), estado.toUpperCase());
		};
	}

}
