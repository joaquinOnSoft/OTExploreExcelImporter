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
package com.opentext.explore.importer.excel;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.opentext.explore.importer.AbstractTransformer;
import com.opentext.explore.importer.excel.pojo.TextData;

public class ExcelTransformer extends AbstractTransformer {
	protected static final Logger log = LogManager.getLogger(ExcelTransformer.class);

	private static Document textDataToDoc(List<TextData> txtDatas, String tag) {
		Document doc = null;
		
		if(txtDatas != null && txtDatas.size() > 0) {
			
			doc=new Document();
			//Root Element
			Element root=new Element("add");
			
			for (TextData txtData : txtDatas) {
				Element eDoc = new Element("doc");
							
				eDoc.addContent(createElementField("reference_id", txtData.getReferenceId()));
				eDoc.addContent(createElementField("interaction_id", txtData.getInteractionId()));
				eDoc.addContent(createElementField("title", txtData.getTitle()));
				eDoc.addContent(createElementField("author_name", txtData.getAuthorName()));
				eDoc.addContent(createElementField("ID", txtData.getId()));
				eDoc.addContent(createElementField("type", txtData.getType()));	
				eDoc.addContent(createElementField("published_date", txtData.getPublishedDate()));
				//eDoc.addContent(createElementField("date_time", txtData.getCreated()));
				eDoc.addContent(createElementField("content", new CDATA(txtData.getContent())));				

				
			
				Map<String, String> fields = txtData.getFields();
				if(fields != null) {
					for (Map.Entry<String, String> entry : fields.entrySet()) {
					    log.debug(entry.getKey() + " / " + entry.getValue());
					    eDoc.addContent(createElementField(entry.getKey() , entry.getValue()));
					}					
				}
				
				eDoc.addContent(createElementField("etag", tag));

				root.addContent(eDoc);
			}
			
			doc.addContent(root);
		}
		
		return doc;
	}	
	
	
	/**
	 * Generate a XML string using the given Text data (coming from a Excel or CSV file) 
	 * SEE: How to create XML file with specific structure in Java 
	 * https://stackoverflow.com/questions/23520208/how-to-create-xml-file-with-specific-structure-in-java
	 * @param txtDatas
	 * @return
	 */
	public static String textDataToString(List<TextData> txtDatas, String tag) {
		String xml = null;
		Document doc = textDataToDoc(txtDatas, tag);

		if(doc != null) {
			//Create the XML
			XMLOutputter outter=new XMLOutputter();
			outter.setFormat(Format.getPrettyFormat());			
			xml = outter.outputString(doc);
		}
		
		return xml;
	}	
}
