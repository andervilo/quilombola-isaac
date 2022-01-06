package br.org.quilombola.arquitetura;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.quilombola.arquitetura.criteria.BodyFIlterAndSort;
import br.org.quilombola.model.entity.Certificado;
import br.org.quilombola.response.Response;


@SuppressWarnings("rawtypes")
public abstract class AbstractRestController<E extends Object, S extends IService>  implements IRestController<E>{
	@Autowired
	private S service;

	@SuppressWarnings("unchecked")
	@GetMapping("")
	@Override
	public ResponseEntity<?> listAll(Pageable pageable, @RequestBody Optional<BodyFIlterAndSort> bodyFIlter) {
		
		if(bodyFIlter.isPresent()) {
			pageable = PageRequest.of(bodyFIlter.get().getPage(), bodyFIlter.get().getSize());
			Page<E> page = service.findAll(pageable, bodyFIlter.get().getSorting(), bodyFIlter.get().getFilters());
			return ResponseEntity.ok().body(page);			
		}
		
		Page<E> page = service.findAll(pageable);
		return ResponseEntity.ok().body(page);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<?> showById(@PathVariable Long id) {
		E entity = (E) service.findById(id);
		if(entity==null) {
			String nullResponse = "{\"message\":\"Recurso não encontrado!\", \"statusCode\":"+HttpStatus.NOT_FOUND.value()+"}";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nullResponse);
		}
		return (ResponseEntity<?>) ResponseEntity.ok().body(entity);
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping
	@Override
	public ResponseEntity<?> create(@Valid @RequestBody E object, BindingResult result) {
		
		Map<Object, Object> map = new HashMap<Object, Object>();

//        if (errors.hasErrors()) {	
//            return ResponseEntity.badRequest().body(UtilsValidator.formatarErros(errors));
//        }
		
		Response<Certificado> response = new Response<Certificado>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		if(object == null) {
			map.put("success", false);
			map.put("message", "Requisição feita com objeto nulo!");
			return ResponseEntity.badRequest().body(map);
		}
		
		E entity = (E) service.create(object);
		
		if(entity == null) {
			map.put("success", false);
			map.put("message", "Não foi possível executar esta operação!");
			return ResponseEntity.badRequest().body(map);
		}
		
		map.put("success", true);
		map.put("data", entity);
		map.put("message", "Operação realizada com sucesso!");
		
		return ResponseEntity.ok().body(map);
	}
	
	@SuppressWarnings("unchecked")
	@PutMapping(value="/{id}", consumes = "application/json")
	@Override
	public ResponseEntity<?> update(Long id, @Valid E object, Errors errors) {
		Map<Object, Object> map = new HashMap<Object, Object>();

        if (errors.hasErrors()) {	
            return ResponseEntity.badRequest().body(UtilsValidator.formatarErros(errors));
        }
		
		if(object == null) {
			map.put("success", false);
			map.put("message", "Requisição feita com objeto nulo!");
			return ResponseEntity.badRequest().body(map);
		}
		
		
		E entity = (E) service.update(id, object);
		
		if(entity == null) {
			map.put("success", false);
			map.put("message", "Não foi possível executar esta operação!");
			return ResponseEntity.badRequest().body(map);
		}
		
		map.put("success", true);
		map.put("data", entity);
		map.put("message", "Operação realizada com sucesso!");
		
		return ResponseEntity.ok().body(map);
	}
	
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Map<Object, Object>> delete(@PathVariable Long id) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		if(service.delete(id)) {
			map.put("success", true);
			map.put("message", "Operação realizada com sucesso!");
			return ResponseEntity.ok().body(map);
		}
		
		map.put("success", false);
		return ResponseEntity.ok().body(map);
	}

	public S getService() {
		return service;
	}	
	
	
}
