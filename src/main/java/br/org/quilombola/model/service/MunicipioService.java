package br.org.quilombola.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.org.quilombola.arquitetura.GenericService;
import br.org.quilombola.arquitetura.criteria.Filter;
import br.org.quilombola.arquitetura.criteria.Sorting;
import br.org.quilombola.model.entity.Municipio;
import br.org.quilombola.model.repository.MunicipioRepository;
import br.org.quilombola.model.specification.MunicipioSpec;

@Service
public class MunicipioService extends GenericService<MunicipioRepository, Municipio> {

	@Autowired
	private MunicipioRepository repository;

	public List<Municipio> findByEstado(String estado) {
		return repository.findByEstado(estado);
	}
	
	public List<Municipio> getMunicipiosPorNome(String municipio) {
		List<Municipio> municipios = new ArrayList<Municipio>();
		
		if(!StringUtils.isEmpty(municipio) ) {
			municipios = repository.findAll(MunicipioSpec.getMunicipioByNome(municipio));
		}

		return municipios;
	}

	public Page<Municipio> findByCriteria(Pageable pageable, Sorting orderBy, List<Filter> filters) {

		Long id = 0L;
		String nome = "";
		String estado = "";
		Integer codigoIbge = 0;

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
	
					case "codigoIbge":
						try {
							codigoIbge = Integer.parseInt((String) filter.getValue());
						} catch (Exception e) {
							// TODO: handle exception
						}
						break;
						
					case "estado":
						estado = (String)filter.getValue();
						break;
	
					default:
						break;
				}

				break;

			case MAIOR:
				break;

			case MAIOR_IGUAL:
				break;

			case MENOR:
				break;

			case MENOR_IGUAL:
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
	
					case "codigoIbge":						
						try {
							codigoIbge = Integer.parseInt((String) filter.getValue());
						} catch (Exception e) {
							// TODO: handle exception
						}
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
	
					case "codigoIbge":						
						try {
							codigoIbge = Integer.parseInt((String) filter.getValue());
						} catch (Exception e) {
							// TODO: handle exception
						}
						break;
	
					default:
						break;
				}
				break;

			case COMECANDO_COM:
				break;

			case FALSO:
				break;

			case VERDADEIRO:
				break;

			case NULO:
				break;

			case ENTRE:
				break;

			default:
				break;
			}
		}

		
		Page<Municipio> page = repository.findAll(
				MunicipioSpec.getMunicipioByNome(nome)
				.or(MunicipioSpec.getMunicipioById(id))
				.or(MunicipioSpec.getMunicipioByCodigoIbge(codigoIbge))
				.or(MunicipioSpec.getMunicipioByEstado(estado)), pageable);
		return page;
	}
}
