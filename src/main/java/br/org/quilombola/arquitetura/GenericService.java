package br.org.quilombola.arquitetura;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.EnumerationUtils;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

import br.org.quilombola.arquitetura.criteria.DirectionEnum;
import br.org.quilombola.arquitetura.criteria.Filter;
import br.org.quilombola.arquitetura.criteria.OperatorEnum;
import br.org.quilombola.arquitetura.criteria.Sorting;
import br.org.quilombola.arquitetura.enums.Estado;
import br.org.quilombola.arquitetura.enums.Regiao;
import br.org.quilombola.arquitetura.interfaces.IRepository;
import br.org.quilombola.model.entity.Municipio;



public class GenericService<R extends IRepository<E, Long> & JpaRepository<E, Long>, E extends Object> implements IService<E,R>{
	
	@Autowired
	private R repository;

	@Override
	public Page<E> findAll(Pageable pageable, Sorting orderBy, List<Filter> filters) {
		Page<E> page = repository.findAll(new Specification<E>() {

			private static final long serialVersionUID = 5595198371552438726L;
			private static final String FIELD_SEPARATOR = ".";
		    private static final String REGEX_FIELD_SPLITTER = "\\.";

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				for (Filter filter : filters) {
					switch (filter.getOperator()) {
					case IGUAL:
						predicates.add(criteriaBuilder
									.or(criteriaBuilder.equal(root.get(filter.getField()).as(String.class), (Comparable) filter.getValue().toString() )));
						break;

					case MAIOR:
						predicates.add(criteriaBuilder.or(criteriaBuilder.greaterThan(root.get(filter.getField()),
								(Comparable) filter.getValue())));
						break;

					case MAIOR_IGUAL:
						predicates.add(criteriaBuilder.or(criteriaBuilder
								.greaterThanOrEqualTo(root.get(filter.getField()), (Comparable) filter.getValue())));
						break;

					case MENOR:
						predicates.add(criteriaBuilder.or(
								criteriaBuilder.lessThan(root.get(filter.getField()), (Comparable) filter.getValue())));
						break;

					case MENOR_IGUAL:
						predicates.add(criteriaBuilder.or(criteriaBuilder
								.greaterThanOrEqualTo(root.get(filter.getField()), (Comparable) filter.getValue())));
						break;

					case CONTENDO_STR:
						predicates.add(criteriaBuilder
								.or(criteriaBuilder.like(criteriaBuilder.upper(root.get(filter.getField())), "%" + filter.getValue().toString().toUpperCase()  + "%")));
						break;
					case CONTENDO:
						predicates.add(criteriaBuilder
								.or(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get(filter.getField())), "%" + filter.getValue().toString().toUpperCase()  + "%")));
						break;

					case COMECANDO_COM:
						predicates.add(criteriaBuilder
								.or(criteriaBuilder.like(root.get(filter.getField()), filter.getValue() + "%")));
						break;

					case FALSO:
						predicates.add(criteriaBuilder.or(criteriaBuilder.isFalse(root.get(filter.getField()))));
						break;

					case VERDADEIRO:
						predicates.add(criteriaBuilder.or(criteriaBuilder.isTrue(root.get(filter.getField()))));
						break;

					case NULO:
						predicates.add(criteriaBuilder.or(criteriaBuilder.isNull(root.get(filter.getField()))));
						break;

					case ENTRE:
						predicates.add(criteriaBuilder.or(criteriaBuilder.between(root.get(filter.getField()),
								(Comparable) filter.getValue(), (Comparable) filter.getValueTwo())));
						break;

					default:
						break;
					}
				}

				if (orderBy.getDirection().equals(DirectionEnum.ASC)) {
					query.orderBy(criteriaBuilder.asc(root.get(orderBy.getField())));
				} else if (orderBy.getDirection().equals(DirectionEnum.DESC)) {
					query.orderBy(criteriaBuilder.desc(root.get(orderBy.getField())));
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		}, pageable);
		return page;
	}
	
	@Override
	public List<?> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<?> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public E findById(Long id) {
		Optional<E> model = repository.findById(id);
		if(model.isPresent()) return model.get();
		return null;
	}

	@Override
	public E create(E object) {
		return repository.saveAndFlush(object);
	}

	@Override
	public E update(Long id, E object) {
		if(repository.existsById(id)) {
			return repository.saveAndFlush(object);			
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		Optional<E> model = repository.findById(id);
		if(model.isPresent()) {
			repository.delete(model.get());			
			return true;			
		}
		return false;
	}

	@Override
	public JpaRepository<E, Long> getRepository() {
		return this.repository;
	}

}
