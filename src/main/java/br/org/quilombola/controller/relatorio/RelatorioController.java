package br.org.quilombola.controller.relatorio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.exceptions.CsvValidationException;

import br.org.quilombola.model.entity.relatorios.RelatorioComunidade;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioAssentamento;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioCcdru;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioDecreto;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioEdital;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioPortaria;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioProcesso;
import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioTitulo;
import br.org.quilombola.model.repository.RelatorioComunidadeRepository;
import br.org.quilombola.model.repository.RelatorioTerritorioAssentamentoRepository;
import br.org.quilombola.model.repository.RelatorioTerritorioCcdruRepository;
import br.org.quilombola.model.repository.RelatorioTerritorioDecretoRepository;
import br.org.quilombola.model.repository.RelatorioTerritorioEditalRepository;
import br.org.quilombola.model.repository.RelatorioTerritorioPortariaRepository;
import br.org.quilombola.model.repository.RelatorioTerritorioProcessoRepository;
import br.org.quilombola.model.repository.RelatorioTerritorioTituloRepository;
import br.org.quilombola.utils.relatorios.csv.WriterGenericCsv;

@RestController
@RequestMapping("/api/v1/relatorios")
public class RelatorioController {
	
	@Autowired
	private RelatorioComunidadeRepository relComRepo;
	
	@Autowired
	private RelatorioTerritorioCcdruRepository relCcdruRepo;
	
	@Autowired
	private RelatorioTerritorioAssentamentoRepository relAssentRepo;
	
	@Autowired
	private RelatorioTerritorioDecretoRepository relDecretoRepo;
	
	@Autowired
	private RelatorioTerritorioEditalRepository relEditalRepo;
	
	@Autowired
	private RelatorioTerritorioPortariaRepository relPortariaRepo;
	
	@Autowired
	private RelatorioTerritorioProcessoRepository relProcessoRepo;
	
	@Autowired
	private RelatorioTerritorioTituloRepository relTituloRepo;

	//---Comunidade
	@GetMapping("/comunidade.xls")
	public ResponseEntity<?> getComunidadeCsv(HttpServletResponse response) throws Exception{
		response.setHeader("Content-Disposition", "attachment; filename=comunidade_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioComunidade> list = relComRepo.findAll();
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/comunidade.xls/estado/{uf}")
	public ResponseEntity<?> getComunidadeCsv(HttpServletResponse response, @PathVariable String uf) throws Exception{
		response.setHeader("Content-Disposition", "attachment; filename=comunidade_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioComunidade> list = relComRepo.findByEstado(uf);
		return getCsvFromObjectList(list);
	}
	
	//---CCDRU
	@GetMapping("/ccdru.xls")
	public ResponseEntity<?> getCcdruCsv(HttpServletResponse response) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=ccdru_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioCcdru> list = relCcdruRepo.findAll();
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/ccdru.xls/estado/{uf}")
	public ResponseEntity<?> getCcdruCsv(HttpServletResponse response, @PathVariable String uf) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=ccdru_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioCcdru> list = relCcdruRepo.findByEstado(uf);
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/ccdru.xls/territorio/{id}")
	public ResponseEntity<?> getCcdruCsv(HttpServletResponse response, @PathVariable Integer id) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=ccdru_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioCcdru> list = relCcdruRepo.findAll();
		return getCsvFromObjectList(list);
	}
	
	//---Assentamento
	@GetMapping("/assentamento.xls")
	public ResponseEntity<?> getAssentamentoCsv(HttpServletResponse response) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=assentamento_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioAssentamento> list = relAssentRepo.findAll();
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/assentamento.xls/estado/{uf}")
	public ResponseEntity<?> getAssentamentoCsvByUf(HttpServletResponse response, @PathVariable String uf) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=assentamento_por_estado_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioAssentamento> list = relAssentRepo.findByEstado(uf);
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/assentamento.xls/territorio/{id}")
	public ResponseEntity<?> getAssentamentoCsvByTerritorio(HttpServletResponse response, @PathVariable Integer id) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=assentamento_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioAssentamento> list = relAssentRepo.findByIdTerritorio(id);
		return getCsvFromObjectList(list);
	}
	
	//--Decreto
	@GetMapping("/decreto.xls")
	public ResponseEntity<?> getDecretoCsv(HttpServletResponse response) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=decreto_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioDecreto> list = relDecretoRepo.findAll();
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/decreto.xls/estado/{uf}")
	public ResponseEntity<?> getDecretoCsv(HttpServletResponse response, @PathVariable String uf) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=decreto_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioDecreto> list = relDecretoRepo.findByEstado(uf);
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/decreto.xls/territorio/{id}")
	public ResponseEntity<?> getDecretoCsv(HttpServletResponse response, @PathVariable Integer id) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=decreto_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioDecreto> list = relDecretoRepo.findByIdTerritorio(id);
		return getCsvFromObjectList(list);
	}
	
	//---Edital
	@GetMapping("/edital.xls")
	public ResponseEntity<?> getEditalCsv(HttpServletResponse response) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=edital_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioEdital> list = relEditalRepo.findAll();
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/edital.xls/estado/{uf}")
	public ResponseEntity<?> getEditalCsv(HttpServletResponse response, @PathVariable String uf) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=edital_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioEdital> list = relEditalRepo.findByEstado(uf);
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/edital.xls/territorio/{id}")
	public ResponseEntity<?> getEditalCsv(HttpServletResponse response, @PathVariable Integer id) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=edital_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioEdital> list = relEditalRepo.findByIdTerritorio(id);
		return getCsvFromObjectList(list);
	}
	
	//---Portaria
	@GetMapping("/portaria.xls")
	public ResponseEntity<?> getPortariaCsv(HttpServletResponse response) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=portaria_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioPortaria> list = relPortariaRepo.findAll();
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/portaria.xls/estado/{uf}")
	public ResponseEntity<?> getPortariaCsv(HttpServletResponse response, @PathVariable String uf) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=portaria_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioPortaria> list = relPortariaRepo.findByEstado(uf);
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/portaria.xls/territorio/{id}")
	public ResponseEntity<?> getPortariaCsv(HttpServletResponse response, @PathVariable Integer id) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=portaria_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioPortaria> list = relPortariaRepo.findByIdTerritorio(id);
		return getCsvFromObjectList(list);
	}
	
	//---Processo
	@GetMapping("/processo.xls")
	public ResponseEntity<?> getProcessoCsv(HttpServletResponse response) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=processo_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioProcesso> list = relProcessoRepo.findAll();
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/processo.xls/territorio/{id}")
	public ResponseEntity<?> getProcessoCsv(HttpServletResponse response, @PathVariable Integer id) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=processo_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioProcesso> list = relProcessoRepo.findByIdTerritorio(id);
		return getCsvFromObjectList(list);
	}
	
	//---Titulo
	@GetMapping("/titulo.xls")
	public ResponseEntity<?> getTituloCsv(HttpServletResponse response) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=titulo_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioTitulo> list = relTituloRepo.findAll();
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/titulo.xls/estado/{uf}")
	public ResponseEntity<?> getTituloCsv(HttpServletResponse response, @PathVariable String uf) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=titulo_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioTitulo> list = relTituloRepo.findAll();
		return getCsvFromObjectList(list);
	}
	
	@GetMapping("/titulo.xls/territorio/{id}")
	public ResponseEntity<?> getTituloCsv(HttpServletResponse response, @PathVariable Integer id) throws Exception{  			
		response.setHeader("Content-Disposition", "attachment; filename=titulo_"+String.valueOf(new Date().toString())+".xlsx");
		List<RelatorioTerritorioTitulo> list = relTituloRepo.findByIdTerritorio(id);
		return getCsvFromObjectList(list);
	}
	
	private ResponseEntity<?> getCsvFromObjectList(List<?> list) throws NumberFormatException, CsvValidationException, IOException{
		HttpHeaders headers = new HttpHeaders(); 			
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate"); 			
		headers.add("Pragma", "no-cache"); 			
		headers.add("Expires", "0");  
		InputStream inputStream = WriterGenericCsv.writerXls2(list); 
		return ResponseEntity.ok().headers(headers) 					
		.contentType(MediaType.parseMediaType("application/vnd.ms-excel")) 					
		.body(new InputStreamResource(inputStream));
	}
}
