package com.opentext.explore.importer.excel.configgenerator;

public class TestSolrSchemaXMLGenerator  extends TestAbstractXMLGenerator {

	@Override
	protected String getXMLOutputFileName() {		
		return "schema.xml";
	}
	
	@Override
	protected AbstractConfigGenerator getXMLGenerator() {
		return new SolrSchemaXMLGenerator();
	}	
}