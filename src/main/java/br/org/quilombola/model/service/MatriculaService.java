package br.org.quilombola.model.service;

import org.springframework.stereotype.Service;

import br.org.quilombola.arquitetura.GenericService;
import br.org.quilombola.model.entity.Matricula;
import br.org.quilombola.model.repository.MatriculaRepository;

@Service
public class MatriculaService extends GenericService<MatriculaRepository, Matricula>{

}
