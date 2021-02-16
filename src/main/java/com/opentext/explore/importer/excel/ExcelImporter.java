package com.opentext.explore.importer.excel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.opentext.explore.connector.SolrAPIWrapper;
import com.opentext.explore.importer.excel.pojo.TextData;
import com.opentext.explore.util.FileUtil;

import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;

public class ExcelImporter {
	/** Solr URL (this Solr instance is used by Explore) */
	private String host = null; 

	protected static final Logger log = LogManager.getLogger(ExcelImporter.class);
	
	/**
	 * 
	 * @param host - Solr URL (this Solr instance is used by Explore)
	 */
	public ExcelImporter(String host) {
		this.host = host;
	}
	
	/**
	 * Call to the /solr/interaction/otcaBatchUpdate 
	 * method provided by Solr in order to insert new content
	 * @param tag - Excel Importer tag (used to filter content in Explore)
	 * @param txtDatas - List of Text Data (Excel or CSV row)
	 * @return true if the insertion in Solr was ok, false in other case. 
	 */
	protected boolean solrBatchUpdate(String tag, List<TextData> txtDatas) {
		boolean updated = true;
		
		String xmlPath = null;
		String xmlFileName = FileUtil.getRandomFileName(".xml");
		try {
			
			xmlPath = ExcelTransformer.textDatasToXMLFile(txtDatas, xmlFileName, tag);
			
			SolrAPIWrapper wrapper = null;
			if(host == null)
				wrapper = new SolrAPIWrapper();
			else {
				wrapper = new SolrAPIWrapper(host);
			}
			wrapper.otcaBatchUpdate(new File(xmlPath));	
		} catch (IOException e) {
			log.error(e.getMessage());
			updated = false;
		}
		finally {
			if(xmlPath != null) {
				FileUtil.deleteFile(xmlPath);	
			}
		}
		
		return updated;
	}
	
}
