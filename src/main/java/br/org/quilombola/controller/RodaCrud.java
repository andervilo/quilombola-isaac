package br.org.quilombola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.org.quilombola.arquitetura.enums.Estado;
import br.org.quilombola.model.entity.Municipio;
import br.org.quilombola.model.service.MunicipioService;

//@Configuration
public class RodaCrud implements CommandLineRunner {
	
	@Autowired
	private MunicipioService service;
	
	
	private Municipio municipio = new Municipio();

    @Override
    public void run(String... args) throws Exception {
//    	municipio.setAmazoniaLegal(true);
//    	municipio.setCodigoIbge(111);
//    	municipio.setNome("Belém");
//    	municipio.setEstado(Estado.PA);
//    	municipio.setSr(1);
//    	
//    	service.create(municipio);
    	
    	municipio = service.findById(new Long(2));
    	
    	System.out.println("ID: "+municipio.getId());
    	System.out.println("Nome: "+municipio.getNome());
    	System.out.println("Estado: "+municipio.getEstado().getNome());
    	System.out.println("Região: "+municipio.getEstado().getRegiao().getNome());
    }
}
