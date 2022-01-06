package br.org.quilombola.utils.relatorios.csv;

import java.io.PrintWriter;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import br.org.quilombola.model.entity.relatorios.RelatorioComunidade;

public class WriteComunidadeToCsv {

	public static void writeComunidade(PrintWriter writer, List<RelatorioComunidade> comunidades) throws Exception {	
		
			ColumnPositionMappingStrategy<RelatorioComunidade> mapStrategy = new ColumnPositionMappingStrategy<>();
			mapStrategy.setType(RelatorioComunidade.class);
			String[] columns = new String[] { "id", "comunidade", "municipios", "codigo_ibge", "estado",
					"superintendencia_codigo", "superintendencia_nome", "regiao", "amazonia_legal", "certificada",
					"numero_processo_fcp", "numero_processo_fcp2", "data_abertura_processo_certificacao", "livro",
					"numero", "numero_portaria", "data_portaria", "secoes_diario_oficial", "folhas_diario_oficial",
					"link1", "link2", "retificacao_fcp_observacao", "observacao" };
			
			mapStrategy.setColumnMapping(columns);
			
			StatefulBeanToCsv<RelatorioComunidade> btcsv = new StatefulBeanToCsvBuilder<RelatorioComunidade>(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withMappingStrategy(mapStrategy)
					.withSeparator(',')
					.build();
			btcsv.write(comunidades);
	}
}
