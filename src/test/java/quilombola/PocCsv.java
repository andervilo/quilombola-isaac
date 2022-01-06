package quilombola;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

import br.org.quilombola.model.entity.relatorios.RelatorioComunidade;

class PocCsv {

	@Test
	void test() throws JsonProcessingException {
		List<RelatorioComunidade> comunidades = new ArrayList<RelatorioComunidade>();
		RelatorioComunidade comunidadeA = new RelatorioComunidade();
		comunidadeA.setId(1L);
		comunidadeA.setComunidade("TESTE1");
		comunidadeA.setEstado("PA");
		
		RelatorioComunidade comunidadeB = new RelatorioComunidade();
		comunidadeB.setId(2L);
		comunidadeB.setComunidade("TESTE2");
		comunidadeB.setEstado("SP");
		
		RelatorioComunidade comunidadeC = new RelatorioComunidade();
		comunidadeC.setId(3L);
		comunidadeC.setComunidade("TESTE3");
		comunidadeC.setEstado("CE");
		
		comunidades.add(comunidadeA);
		comunidades.add(comunidadeB);
		comunidades.add(comunidadeC);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(comunidades);
		JsonNode jsonTree = new ObjectMapper().readTree(json);
		
		Builder csvSchemaBuilder = CsvSchema.builder();
		JsonNode firstObject = jsonTree.elements().next();
		firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
		CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
		
		CsvMapper csvMapper = new CsvMapper();
		String csv = csvMapper.writerFor(JsonNode.class)
		  .with(csvSchema)
		  .writeValueAsString(jsonTree);
		
		System.out.println(csv);
	}

}
