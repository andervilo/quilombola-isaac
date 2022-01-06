package br.org.quilombola.utils.relatorios.csv;

import java.io.PrintWriter;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import br.org.quilombola.model.entity.relatorios.RelatorioTerritorioCcdru;

public class WriteCcdruToCsv {

	public static void writeCcdru(PrintWriter writer, List<RelatorioTerritorioCcdru> ccdru) throws Exception {

		ColumnPositionMappingStrategy<RelatorioTerritorioCcdru> mapStrategy = new ColumnPositionMappingStrategy<>();
		mapStrategy.setType(RelatorioTerritorioCcdru.class);
		String[] columns = new String[] { "id", "id_territorio", "territorio", "municipio", "estado", "numero_processo",
				"numero_portaria", "data_portaria", "secao_dou", "folha_dou", "link_dou", "link_dou_2",
				"numero_familias", "nome_imovel", "areaha_titulo", "cartorio", "numero_oficio", "livro", "folha",
				"matricula", "nome_proprietario", "cadastro_incra" };

		mapStrategy.setColumnMapping(columns);

		StatefulBeanToCsv<RelatorioTerritorioCcdru> btcsv = new StatefulBeanToCsvBuilder<RelatorioTerritorioCcdru>(
				writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
				.withMappingStrategy(mapStrategy)
				.withSeparator(',')
				.build();
		btcsv.write(ccdru);
	}
}
