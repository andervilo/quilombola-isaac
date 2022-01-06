package br.org.quilombola.arquitetura;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.quilombola.arquitetura.criteria.BodyFIlterAndSort;


public interface IRestController<E> {
		
//	public ResponseEntity<?> listAll();	
	public ResponseEntity<?> listAll(Pageable pageable, @RequestBody Optional<BodyFIlterAndSort> bodyFIlter);
	public ResponseEntity<?> showById(@PathVariable(value = "id") Long id);	
	public ResponseEntity<?> create(@Valid @RequestBody E object, BindingResult result);	
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody E object, Errors errors);	
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id);

}
