package br.org.quilombola.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.org.quilombola.arquitetura.GenericService;
import br.org.quilombola.arquitetura.criteria.Filter;
import br.org.quilombola.arquitetura.criteria.Sorting;
import br.org.quilombola.model.entity.Quilombo;
import br.org.quilombola.model.repository.QuilomboRepository;
import br.org.quilombola.model.specification.QuilomboSpec;

@Service
public class QuilomboService extends GenericService<QuilomboRepository, Quilombo>{
	
	@Autowired
	private QuilomboRepository quilomboRepository;
	
	public List<Quilombo> getQuilombosByComunidadesId(Long id){
		return quilomboRepository.findByComunidades_Id(id);
	}
	
public Page<Quilombo> findByCriteria(Pageable pageable, Sorting orderBy, List<Filter> filters) {
		
		Long id = 0L;
		String nome = "";

		for (Filter filter : filters) {
			switch (filter.getOperator()) {
				case IGUAL:
					switch (filter.getField()) {
						case "id":
							try {
								id = Long.parseLong((String) filter.getValue());
							} catch (Exception e) {
								// TODO: handle exception
							}
							
							break;
		
						case "nome":
							nome = (String)filter.getValue();
							break;
		
						default:
							break;
					}
	
					break;
	
				case CONTENDO_STR:
					switch (filter.getField()) {
						case "id":
							try {
								id = Long.parseLong((String) filter.getValue());
							} catch (Exception e) {
								// TODO: handle exception
							}
							break;
		
						case "nome":
							nome = (String)filter.getValue();
							break;
		
						default:
							break;
					}
					break;
				case CONTENDO:
					switch (filter.getField()) {
						case "id":
							try {
								id = Long.parseLong((String) filter.getValue());
							} catch (Exception e) {
								// TODO: handle exception
							}
							break;
		
						case "nome":
							nome = (String)filter.getValue();
							break;
			
						default:
							break;
					}
					break;
	
				default:
					break;
			}
		}
		
		Page<Quilombo> page = quilomboRepository.findAll(
				QuilomboSpec.getQuilomboById(id)
				.or(QuilomboSpec.getQuilomboByNome(nome)), 
				pageable);
		return page;
	}

	
}
