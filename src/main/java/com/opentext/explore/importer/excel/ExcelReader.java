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
 *     Joaqu�n Garz�n - initial implementation
 *
 */
package com.opentext.explore.importer.excel;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Read excel files in Java using a very simple yet powerful open source library called Apache POI.
 * @see https://www.callicoder.com/java-read-excel-file-apache-poi/ 
 * @author Joaqu�n Garz�n
 */
public class ExcelReader {
	private Workbook workbook;
	protected static final Logger log = LogManager.getLogger(ExcelReader.class);

	public ExcelReader(String fileName) throws EncryptedDocumentException, IOException {
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		workbook = WorkbookFactory.create(new File(fileName));

		// Retrieving the number of sheets in the Workbook
		log.debug("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
	}

	

}
