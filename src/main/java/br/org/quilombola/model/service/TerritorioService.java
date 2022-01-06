package br.org.quilombola.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.org.quilombola.arquitetura.GenericService;
import br.org.quilombola.arquitetura.criteria.Filter;
import br.org.quilombola.arquitetura.criteria.Sorting;
import br.org.quilombola.arquitetura.enums.Estado;
import br.org.quilombola.model.entity.Territorio;
import br.org.quilombola.model.repository.TerritorioRepository;
import br.org.quilombola.model.specification.TerritorioSpec;

@Service
public class TerritorioService extends GenericService<TerritorioRepository, Territorio>{

	@Autowired
	private TerritorioRepository repository;
	
	public List<Territorio> getTerritoriosByUfCodeAndSearchText(int codUf, @Nullable String busca) {
		List<Territorio> territorios = new ArrayList<Territorio>();
		
		String estado = Estado.getEstadoByCodigoIbge(codUf).getSigla();
		
		if(!StringUtils.isEmpty(busca) ) {
			territorios = repository.findAll(TerritorioSpec.getTerritorioByTerritorioAndEstado(busca, estado));
		}else {
			territorios = repository.findAll(TerritorioSpec.getTerritoriosByEstado(estado));
		}
		return territorios;
	}
	
	public List<Territorio> getTerritoriosByNome(String nome) {
		List<Territorio> territorios = new ArrayList<Territorio>();
		
		if(!StringUtils.isEmpty(nome) ) {
			//territorios = repository.findAll(TerritorioSpec.getTerritorioByNomeTirandoAcento(nome));
			territorios = repository.findAll(TerritorioSpec.getTerritorioByNome(nome));
		}

		return territorios;
	}
	
	public Page<Territorio> findByCriteria(Pageable pageable, Sorting orderBy, List<Filter> filters) {
		
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
		
		Page<Territorio> page = repository.findAll(
				TerritorioSpec.getTerritorioByIdOrNome(id, nome), pageable);
//		Page<Territorio> page = repository.findAll(
//				TerritorioSpec.getTerritorioById(id)
//				.or(TerritorioSpec.getTerritorioByNome(nome)), 
//				pageable);
		return page;
	}
}
