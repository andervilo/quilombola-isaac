package br.org.quilombola.model.specification;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import br.org.quilombola.model.entity.Municipio;
import br.org.quilombola.model.entity.Municipio_;
import br.org.quilombola.model.entity.Territorio;
import br.org.quilombola.model.entity.Territorio_;

public class TerritorioSpec {
	
	private static String textoBusca = null;
	
	private static String texto_Busca = null;
	
	public static Specification<Territorio> getTerritorioByNome(String nome){
		textoBusca = Normalizer
				.normalize(nome, Normalizer.Form.NFD)
		        .replaceAll("[^\\p{ASCII}]", "");
		
		return (root, query, cb)->{
			return cb.like(cb.function("unaccent", String.class, cb.upper(root.get(Territorio_.nome))), "%"+textoBusca.toUpperCase()+"%");
		};
	}
	
	public static Specification<Territorio> getTerritorioById(Long id){
		return (root, query, cb)->{
			return cb.equal(root.get(Territorio_.id), id);
		};
	}
	
	public static Specification<Territorio> getTerritorioByIdOrNome(Long id, String nome){
		return Specification.where(getTerritorioByNome(nome)).or(getTerritorioById(id));
	}
	
	public static Specification<Territorio> getTerritorioByNomeTirandoAcento(String textoBusca) {
		if(!StringUtils.isEmpty(textoBusca)) {
			texto_Busca = "%"+ Normalizer
					.normalize(textoBusca, Normalizer.Form.NFD)
			        .replaceAll("[^\\p{ASCII}]", "").toUpperCase() + "%";
		}else {
			texto_Busca = "%%";
		}		
		
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			query.distinct(true);			
			predicates.add(cb.like(cb.function("unaccent", String.class, cb.upper(root.get(Territorio_.nome))), texto_Busca));
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
	
	public static Specification<Territorio> getTerritorioByTerritorioAndEstado(String textoBusca, String estado) {
		if(!StringUtils.isEmpty(textoBusca)) {
			texto_Busca = "%"+ Normalizer
					.normalize(textoBusca, Normalizer.Form.NFD)
			        .replaceAll("[^\\p{ASCII}]", "").toUpperCase() + "%";
		}else {
			texto_Busca = "%%";
		}
		
		return (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<>();
			query.distinct(true);
			Join<Territorio, Municipio> territorioMunicipio = root.join(Territorio_.municipioList, JoinType.LEFT);
			
			predicates.add(cb.like(cb.function("unaccent", String.class, cb.upper(root.get(Territorio_.nome))), texto_Busca));
			
			predicates.add(cb.equal(territorioMunicipio.get(Municipio_.estado), estado));
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
	
	public static Specification<Territorio> getTerritoriosByEstado(String estado) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			query.distinct(true);
			Join<Territorio, Municipio> territorioMunicipio = root.join(Territorio_.municipioList, JoinType.LEFT);
			predicates.add(criteriaBuilder.equal(territorioMunicipio.get(Municipio_.estado), estado));
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

}
