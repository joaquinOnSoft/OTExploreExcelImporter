package com.opentext.explore.importer.excel;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opentext.explore.importer.excel.pojo.TextDataImporterMapping;

public class JSonMappingConfigReader {
	private static final Logger log = LogManager.getLogger(JSonMappingConfigReader.class);

	/**
	 * Read the mapping JSON file that defines the mapping between the 
	 * excel headers and the Solr fields used in Qfiniti/Explore
	 * @see JSON to Java Object. 
	 * https://www.baeldung.com/jackson-object-mapper-tutorial#2-json-to-java-object
	 * @param file - JSON File object
	 * @return Object with the field mapping
	 */
	public TextDataImporterMapping read(File file) {
		TextDataImporterMapping config = null;
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			config = objectMapper.readValue(file, TextDataImporterMapping.class);
		} catch (IOException e) {
			log.error(e.getMessage());			
		}
		
		return config;
	}	
	
	/**
	 * Read the configuration JSON file that defines the mapping between the 
	 * 3rd party excel including metadata and the input file required by 
	 * OpenText Qfiniti Data Importer
	 * @see JSON to Java Object. 
	 * https://www.baeldung.com/jackson-object-mapper-tutorial#2-json-to-java-object
	 * @param filename - File Name
	 * @return Object with the field mapping
	 */
	public TextDataImporterMapping read(String filename) {
		return read(new File(filename));
	}
}
