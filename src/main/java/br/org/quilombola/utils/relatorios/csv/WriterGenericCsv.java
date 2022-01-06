package br.org.quilombola.utils.relatorios.csv;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.hibernate.internal.util.io.CharSequenceReader;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class WriterGenericCsv {
	
	public static String writerCsv(List<?> list) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		JsonNode jsonTree = new ObjectMapper().readTree(json);
		
		Builder csvSchemaBuilder = CsvSchema.builder();
		JsonNode firstObject = jsonTree.elements().next();
		firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
		CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
		
		CsvMapper csvMapper = new CsvMapper();
		String csv = csvMapper.writerFor(JsonNode.class)
		  .with(csvSchema)
		  .writeValueAsString(jsonTree);
		
		return csv;
	}
	
	public static InputStream writerXls2(List<?> list) throws NumberFormatException, CsvValidationException, IOException {
		SXSSFSheet sheet = null;
        CSVReader reader = null;
        Workbook workBook = null;
        List<String> columns = new ArrayList<>();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        workBook = new SXSSFWorkbook();
        sheet = (SXSSFSheet) workBook.createSheet("Sheet");
        
        if(CollectionUtils.isEmpty(list)) {
        	workBook.write(out);            
            ByteArrayInputStream inStream = new ByteArrayInputStream(out.toByteArray());            
            return inStream;        	
        }
        
        ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		JsonNode jsonTree = new ObjectMapper().readTree(json);
		
		JsonNode firstObject = jsonTree.elements().next();
		firstObject.fieldNames().forEachRemaining(fieldName -> {columns.add(fieldName);} );
        
        Reader readerCsv = new CharSequenceReader(writerCsv(list));
        
        String[] nextLine;
        reader = new CSVReader(readerCsv);
        
        
        
     // Create a Font for styling header cells
        Font headerFont = workBook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workBook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);
        
        for(int i = 0; i < columns.toArray().length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue((String) columns.toArray()[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        
        int rowNum = 1;
        
        for(Object object: list) {
        	PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(object);
        	Row row = sheet.createRow(rowNum++);
        	for(int i = 0 ; i < columns.toArray().length ; i++) {
        		row.createCell(i).setCellValue(String.valueOf(myAccessor.getPropertyValue((String) columns.toArray()[i])));
        	}
        	
        }
        
        
        workBook.write(out);
        
        ByteArrayInputStream inStream = new ByteArrayInputStream(out.toByteArray());
        
        return inStream;
	}
	
	public static InputStream writerXls(List<?> list) throws NumberFormatException, CsvValidationException, IOException {
		SXSSFSheet sheet = null;
        CSVReader reader = null;
        Workbook workBook = null;
        
        Reader readerCsv = new CharSequenceReader(writerCsv(list));
        
        String[] nextLine;
        reader = new CSVReader(readerCsv);
        
        workBook = new SXSSFWorkbook();
        sheet = (SXSSFSheet) workBook.createSheet("Sheet");
        
        int rowNum = 0;
        
        while((nextLine = reader.readNext()) != null) {
            Row currentRow = sheet.createRow(rowNum++);
            for(int i=0; i < nextLine.length; i++) {
                if(NumberUtils.isDigits(nextLine[i])) {
                    currentRow.createCell(i).setCellValue(Integer.parseInt(nextLine[i]));
                } else if (NumberUtils.isNumber(nextLine[i])) {
                    currentRow.createCell(i).setCellValue(Double.parseDouble(nextLine[i]));
                } else {
                    currentRow.createCell(i).setCellValue(nextLine[i]);
                }
            }
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workBook.write(out);
        
        ByteArrayInputStream inStream = new ByteArrayInputStream(out.toByteArray());
        
        return inStream;
	}

}
