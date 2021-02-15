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
import java.util.List;

import org.junit.Test;

import com.opentext.explore.importer.excel.pojo.TextData;
import com.opentext.explore.importer.excel.pojo.TextDataImporterMapping;
import com.opentext.explore.util.FileUtil;

import junit.framework.TestCase;

public class TestExcelReader  extends TestCase {
	@Test
	public void testRead() {
		File excelFile = FileUtil.getFileFromResources("input_example.xlsx");
		
		File jsonConfigFile = FileUtil.getFileFromResources("excel_mapping.json");
		JSonMappingConfigReader jsonConfigReader = new JSonMappingConfigReader();
		TextDataImporterMapping mapping = jsonConfigReader.read(jsonConfigFile);		
				
		assertNotNull(excelFile);
		assertNotNull(jsonConfigFile);
		
		ExcelReader reader = new ExcelReader();
		List<TextData> txtDataList = reader.read(excelFile, mapping);
		
		assertNotNull(txtDataList);
		assertTrue(txtDataList.size() > 0);
		assertEquals(3, txtDataList.size());
		
		TextData txtData0 = txtDataList.get(0);
		assertEquals("1583916", txtData0.getReferenceId());		
		assertEquals("Tue Dec 01 00:00:00 CET 2020", txtData0.getPublishedDate().toString());
		assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer finibus leo purus, quis porttitor quam aliquet ut. Curabitur ullamcorper erat at facilisis luctus. Nunc facilisis interdum vestibulum. Nulla auctor ante sed purus scelerisque, tincidunt sollicitudin erat vulputate. Nam risus massa, ullamcorper a aliquam at, blandit in ante. Nam vestibulum, metus eu lacinia fermentum, felis massa venenatis leo, in ornare nibh eros eu odio. Ut sit amet egestas enim. Quisque justo urna, porttitor sed condimentum vitae, malesuada quis arcu. Donec eget lacinia lacus. Duis sagittis id nisl eget porttitor. Aliquam interdum vel ipsum ut blandit. Fusce sed eros a mi tincidunt malesuada.", txtData0.getContent());
		assertEquals("Demora del Reparador", txtData0.getField("MotivoQueja"));
		
		TextData txtData2 = txtDataList.get(2);
		assertEquals("1584006", txtData2.getReferenceId());
		assertEquals("Tue Dec 01 00:00:00 CET 2020", txtData2.getPublishedDate().toString());
		assertEquals("12/3/20", txtData2.getField("FechaCierre"));
		assertEquals("N", txtData2.getField("EsContactar"));
		assertEquals("7011", txtData2.getField("CPI"));
		assertEquals("Protección Salud (No Ofertable)", txtData2.getField("Producto"));
		assertEquals("Quejas de siniestros/prestaciones", txtData2.getField("TipoPeticion"));
		assertEquals("Quisque laoreet scelerisque convallis. Aliquam porttitor finibus diam et mollis. Mauris maximus augue et maximus lacinia. Cras accumsan ullamcorper ex ac lobortis. Nulla vitae quam nulla. Maecenas id mauris augue. Maecenas eget enim euismod, consequat nunc sed, faucibus felis. Aliquam varius blandit semper. Integer pharetra ante turpis, feugiat ultrices arcu varius a. Vivamus sollicitudin ligula molestie, lobortis neque ut, ornare tortor. Nulla auctor varius sodales.", txtData2.getContent());
		assertEquals("NULL", txtData2.getField("MotivoQueja"));
		
	}
}
