package com.opentext.explore.importer.excel;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.opentext.explore.importer.excel.pojo.TextDataImporterMapping;
import com.opentext.explore.importer.excel.pojo.Field;
import com.opentext.explore.util.FileUtil;

import junit.framework.TestCase;

public class TestJSonMappingConfigReader  extends TestCase {
	
	@Test
	public void testRead() {
		File jsonConfigFile = FileUtil.getFileFromResources("excel_mapping.json");
		JSonMappingConfigReader jsonConfigReader = new JSonMappingConfigReader();
		TextDataImporterMapping mapping = jsonConfigReader.read(jsonConfigFile);
		
		assertNotNull(mapping);
		List<Field> fields = mapping.getField();
		assertNotNull(fields);
		assertEquals(17, fields.size());
		
		Field f0 = fields.get(0);
		assertEquals("Identificadorticket", f0.getExcelName());
		assertEquals("IdentificadorTicket", f0.getSolrName());
		assertEquals("integer", f0.getType());
		assertEquals(false, f0.getSkip().booleanValue());
		
		Field f16 = fields.get(16);
		assertEquals("Origen_Queja_2", f16.getExcelName());
		assertEquals("OrigenQueja", f16.getSolrName());
		assertEquals("string", f16.getType());
		assertEquals(false, f16.getSkip().booleanValue());			
	}
}
