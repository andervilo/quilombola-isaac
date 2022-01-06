package br.org.quilombola.model.specification;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import br.org.quilombola.model.entity.Comunidade;
import br.org.quilombola.model.entity.Comunidade_;
import br.org.quilombola.model.entity.Municipio;
import br.org.quilombola.model.entity.Municipio_;
import br.org.quilombola.model.entity.Territorio;
import br.org.quilombola.model.entity.Territorio_;

public class ComunidadeSpec {
	
	private static String texto_Busca = null;

	public static Specification<Comunidade> getComunidadeByNome(String nome) {
		if(!StringUtils.isEmpty(nome)) {
			texto_Busca = "%"+ Normalizer
					.normalize(nome, Normalizer.Form.NFD)
			        .replaceAll("[^\\p{ASCII}]", "").toUpperCase() + "%";
		}else {
			texto_Busca = "%%";
		}
		
		return (root, query, cb) -> {
			return cb.like(cb.function("unaccent", String.class, cb.upper(root.get(Comunidade_.nome))), texto_Busca);
		};
	}

	public static Specification<Comunidade> getComunidadeById(Long id) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get(Comunidade_.id), id);
		};
	}


	public static Specification<Comunidade> getComunidadeByComunidadeAndEstado(String textoBusca, String estado) {
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
			Join<Comunidade, Municipio> comunidadeMunicipio = root.join(Comunidade_.municipios, JoinType.LEFT);
			
			predicates.add(cb.like(cb.function("unaccent", String.class, cb.upper(root.get(Comunidade_.nome))), texto_Busca));
			
			predicates.add(cb.equal(comunidadeMunicipio.get(Municipio_.estado), estado));
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

	public static Specification<Comunidade> getComunidadeByTerritorioAndEstado(String textoBusca, String estado) {
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
			Join<Comunidade, Municipio> comunidadeMunicipio = root.join(Comunidade_.municipios, JoinType.LEFT);
			Join<Municipio, Territorio> municipioTerritorio = comunidadeMunicipio.join(Municipio_.territorioList, JoinType.LEFT);
			
			predicates.add(cb.like(cb.function("unaccent", String.class, cb.upper(municipioTerritorio.get(Territorio_.nome))), texto_Busca));
			
			predicates.add(cb.equal(comunidadeMunicipio.get(Municipio_.estado), estado));
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
	
	public static Specification<Comunidade> getComunidadeByNomeTirandoAcento(String textoBusca) {
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
			predicates.add(cb.like(cb.function("unaccent", String.class, cb.upper(root.get(Comunidade_.nome))), texto_Busca));
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
	
	public static Specification<Comunidade> getComunidadeByEstado(String estado) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			query.distinct(true);
			Join<Comunidade, Municipio> comunidadeMunicipio = root.join(Comunidade_.municipios, JoinType.LEFT);
			predicates.add(criteriaBuilder.equal(comunidadeMunicipio.get(Municipio_.estado), estado));
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

	public static Specification<Comunidade> getComunidadeByComunidadeOrTerritorioAndEstado(String textoBusca, String estado) {
		return Specification.where(getComunidadeByComunidadeAndEstado(textoBusca, estado))
				.or(getComunidadeByTerritorioAndEstado(textoBusca, estado))
				;
	}
}
