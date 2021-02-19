package com.opentext.explore.importer.excel.configgenerator;

public class TestExploreConfigurationXMLGenerator extends TestAbstractXMLGenerator {

	@Override
	protected String getXMLOutputFileName() {		
		return "Explore.Configuration.xml";
	}

	@Override
	protected AbstractConfigGenerator getXMLGenerator() {
		return new ExploreConfigurationXMLGenerator();
	}
	
	@Override
	protected String getReferenceXMLFilePath() {
		return "Explore.Configuration.xml";
	}		
}
