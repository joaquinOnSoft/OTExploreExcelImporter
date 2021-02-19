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

public abstract class TestAbstractXMLGenerator extends TestCase {

	protected TextDataImporterMapping mapping = null;

	@Override
	protected void setUp() {
		File jsonConfigFile = FileUtil.getFileFromResources("excel_mapping.json");
		JSonMappingConfigReader jsonConfigReader = new JSonMappingConfigReader();
		mapping = jsonConfigReader.read(jsonConfigFile);				
	}

	protected abstract String getXMLOutputFileName();
	
	protected abstract AbstractConfigGenerator getXMLGenerator();
	
	@Test
	public void testGenerateConfigFile() {				
		assertNotNull(mapping);
		
		AbstractConfigGenerator generator = getXMLGenerator();
		String path = null;
		
		try {
			path = generator.generateConfigFile(mapping, getXMLOutputFileName(), "Ticket");
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		assertNotNull(path);
		
		File f = new File(path);
		Path p = Paths.get(f.getAbsolutePath());
		assertTrue(Files.exists(p));
	}	
}
