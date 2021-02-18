/*
 *   (C) Copyright 2021 OpenText and others.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   Contributors:
 *     Joaquín Garzón - initial implementation
 *
 */
package com.opentext.explore.importer.excel.configgenerator;

import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;

import com.opentext.explore.importer.excel.pojo.Field;
import com.opentext.explore.importer.excel.pojo.TextDataImporterMapping;
import com.opentext.explore.util.TextUtil;

public class SolrSchemaXMLGenerator extends AbstractConfigGenerator{

	/**
	 * Generates an XML called <strong>Explore.Configuration.xml"</strong> with the 
	 * new content that must be added to the Explore config file with the same name.
	 * 
	 * Let's see and example:
	 *  <Explore>
	 *    <DocTypes>
	 *  
	 *      ...
	 *      
	 *      <DocType>
	 *        <Name>Reddit</Name>
	 *        <GridFields>
	 *          <Field column="Source">
	 *            <Name>Subreddit</Name>
	 *            <Tag>subreddit</Tag>
	 *          </Field>
	 *          <Field column="Source">
	 *            <Name>Score</Name>
	 *            <Tag>score</Tag>
	 *          </Field>
	 *          <Field column="Source">
	 *            <Name>Permalink</Name>
	 *            <Tag>permalink</Tag>
	 *          </Field>
	 *        </GridFields>
	 *      </DocType>	
	 *    
	 *      ...
	 *    
	 *    </DocTypes> 
	 *  </Explore>
	 */
	@Override
	protected Document textDataToDoc(TextDataImporterMapping mapping, String docType) {
		Document doc = null;

		if (mapping != null && mapping.getFields() != null && mapping.getFields().size() > 0) {

			doc = new Document();
			// Root Element
			Element eExplore = new Element("Explore");
			Element eDocTypes = new Element("DocTypes");
			Element eDocType = new Element("DocType");
			Element eName = createBasicElement("name", new CDATA(docType));
			eDocType.addContent(eName);
			
			Element eGridFields = new Element("GridFields");
			
			Element eCriteriaItems = new Element("CriteriaItems");
			Element eGroup  = createElementWith1Attribute("Group", "name", docType);
			
			for (Field field : mapping.getFields()) {
				if(field.getSkip() == false) {										
					eGridFields.addContent(createElementField(field));					
				}
			}
			
			eDocType.addContent(eGridFields);						
			eDocTypes.addContent(eDocType);
			eExplore.addContent(eDocTypes);
			
			eCriteriaItems.addContent(eGroup);
			eExplore.addContent(eCriteriaItems);			
			
			doc.addContent(eExplore);
		}

		return doc;
	}

	private Element createElementField(Field field) {		
		Element eField = createElementWith1Attribute("Field", "column", "Source");
						
		Element eName2 = createBasicElement("name", new CDATA(strToHumanReadable(field.getSolrName())));					
		Element eTag = createBasicElement("tag", new CDATA(field.getSolrName()));
		
		eField.addContent(eName2);
		eField.addContent(eTag);
		
		return eField;
	}
	
	private String strToHumanReadable(String str) {
		if(str!= null) {
			str = TextUtil.camelCaseToHumanReadable(str);
			str = TextUtil.snakeCaseToHumanReadable(str);
		}
		return str;
	}
}
