package br.org.quilombola.arquitetura;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.org.quilombola.arquitetura.criteria.Filter;
import br.org.quilombola.arquitetura.criteria.Sorting;




public interface IService<E extends Object, R extends JpaRepository<E, Long>> {
		
	public abstract List<?> findAll();
	
	public abstract Page<?> findAll(Pageable pageable);
	
	public abstract E findById(Long id);
	
	public abstract E create(@Valid E object);
	
	public abstract E update(Long id, E object);
	
	public abstract boolean delete(Long id);	
	
	public JpaRepository<E, Long> getRepository();

	public Page<E> findAll(Pageable pageable, Sorting orderBy, List<Filter> filters);

}
