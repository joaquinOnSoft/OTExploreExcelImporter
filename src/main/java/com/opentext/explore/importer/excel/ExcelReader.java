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

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.opentext.explore.importer.excel.pojo.Field;
import com.opentext.explore.importer.excel.pojo.TextData;
import com.opentext.explore.importer.excel.pojo.TextDataImporterMapping;
import com.opentext.explore.util.DateUtil;

/**
 * Read excel files in Java using a very simple yet powerful open source library called Apache POI.
 * @see https://www.callicoder.com/java-read-excel-file-apache-poi/ 
 * @author Joaquín Garzón
 */
public class ExcelReader implements ITextDataReader{
	private static final String SOLR_FIELD_REFERENCE_ID ="reference_id";
	private static final String SOLR_FIELD_INTERACTION_ID = "interaction_id";
	private static final String SOLR_FIELD_TITLE = "title";
	private static final String SOLR_FIELD_AUTHOR_NAME = "author_name";
	private static final String SOLR_FIELD_ID = "ID";
	private static final String SOLR_FIELD_TYPE = "type";
	private static final String SOLR_FIELD_PUBLISHED_DATE = "published_date";
	private static final String SOLR_FIELD_DATE_TIME = "date_time";
	private static final String SOLR_FIELD_CONTENT = "content";
	
	protected static final Logger log = LogManager.getLogger(ExcelReader.class);

	@Override
	public List<TextData> read(String filePath, TextDataImporterMapping config) {
		if(filePath != null) {
			return read(new File(filePath), config);	
		}
		else {
			log.warn("File path was null");
			return null;
		}		
	}

	@Override
	public List<TextData> read(File file, TextDataImporterMapping config) {
		List<TextData> textDataList = new LinkedList<TextData>();
		TextData txtData = null;

		List<Field> fields = config.getField();
		Field field = null;
		
		String cellValue = null;

		try {
			// Creating a Workbook from an Excel file (.xls or .xlsx)
			Workbook workbook = WorkbookFactory.create(file);

			// Create a DataFormatter to format and get each cell's value as String
	        DataFormatter dataFormatter = new DataFormatter();			
			
			// Getting the Sheet at index zero
			Sheet sheet = workbook.getSheetAt(0);

			// 2. Or you can use a for-each loop to iterate over the rows and columns
			log.debug("Iterating over Rows and Columns using for-each loop");
			boolean firstRow = true;
			boolean allCellValuesInRowAreEmpty = true;
			int column = 0;

			for (Row row: sheet) {
				if(firstRow) {
					log.debug("Skipping first row (header)");
					firstRow = false;
					continue;
				}
				
				column = 0;
				txtData = new TextData();
				allCellValuesInRowAreEmpty = true;

				for(Cell cell: row) {
					field = fields.get(column);

					if(field.getSkip()) {
						log.info("Excel field " + field.getExcelName() + " skipped");
					}
					else {
						cellValue = dataFormatter.formatCellValue(cell);
						
						if(cellValue != null && cellValue.compareTo("") != 0) {
							allCellValuesInRowAreEmpty = false;
						}
												
						switch (field.getSolrName()) {
						case SOLR_FIELD_REFERENCE_ID:
							txtData.setReferenceId(cellValue);
							break;
						case SOLR_FIELD_INTERACTION_ID:
							txtData.setInteractionId(cellValue);
							break;
						case SOLR_FIELD_TITLE:
							txtData.setTitle(cellValue);
							break;
						case SOLR_FIELD_AUTHOR_NAME:
							txtData.setAuthorName(cellValue);
							break;
						case SOLR_FIELD_ID:
							txtData.setId(cellValue);
							break;
						case SOLR_FIELD_TYPE:
							txtData.setType(cellValue);
							break;
						case SOLR_FIELD_PUBLISHED_DATE:
							if(cellValue != null && cellValue.compareTo("") != 0) {
								txtData.setPublishedDate(DateUtil.strToDate(cellValue, field.getFormat()));	
							}							
							break;
						case SOLR_FIELD_DATE_TIME:
							if(cellValue != null && cellValue.compareTo("") != 0) {
								txtData.setDateTime(DateUtil.strToDate(cellValue, field.getFormat()));	
							}							
							break;
						case SOLR_FIELD_CONTENT:
							txtData.setContent(cellValue);
							break;
						default:
							txtData.addField(field.getSolrName(), cellValue);							
						}
					}

					column++;					
				}

				if(allCellValuesInRowAreEmpty == false) {
					textDataList.add(txtData);	
				}				
			}
			
	        // Closing the workbook
	        workbook.close();			
		} catch (EncryptedDocumentException | IOException e) {
			log.error("Error reading excel file. ", e);
		} catch (ParseException e) {
			log.error("Error formating date. ", e);
		}

		return textDataList;
	}
}
