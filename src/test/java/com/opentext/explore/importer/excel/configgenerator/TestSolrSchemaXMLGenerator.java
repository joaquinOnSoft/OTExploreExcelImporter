package com.opentext.explore.importer.excel.configgenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.opentext.explore.importer.excel.JSonMappingConfigReader;
import com.opentext.explore.importer.excel.pojo.TextDataImporterMapping;
import com.opentext.explore.util.FileUtil;

import junit.framework.TestCase;

public class TestSolrSchemaXMLGenerator extends TestCase {

	private TextDataImporterMapping mapping = null;
	
	@Override
	protected void setUp() {
		File jsonConfigFile = FileUtil.getFileFromResources("excel_mapping.json");
		JSonMappingConfigReader jsonConfigReader = new JSonMappingConfigReader();
		mapping = jsonConfigReader.read(jsonConfigFile);				
	}		
	
	@Test
	public void testGenerateConfigFile() {				
		assertNotNull(mapping);
		
		SolrSchemaXMLGenerator generator = new SolrSchemaXMLGenerator();
		String path = null;
		
		try {
			path = generator.generateConfigFile(mapping, "Explore.Configuration.xml", "Ticket");
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		assertNotNull(path);
		
		File f = new File(path);
		Path p = Paths.get(f.getAbsolutePath());
		assertTrue(Files.exists(p));
	}
}
