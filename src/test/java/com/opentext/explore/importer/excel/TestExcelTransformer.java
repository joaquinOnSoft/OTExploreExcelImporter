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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.opentext.explore.importer.excel.pojo.TextData;
import com.opentext.explore.util.DateUtil;

import junit.framework.TestCase;


public class TestExcelTransformer extends TestCase {

	List<TextData> txtDatas;
	
	private String docXMLFragment = 
			"  <doc>\r\n" + 
			"    <field name=\"reference_id\"><![CDATA[h10u12]]></field>\r\n" +
			"    <field name=\"interaction_id\"><![CDATA[h10u12]]></field>\r\n" +
			"    <field name=\"title\"><![CDATA[Any lawyers here willing to help the Canadian public mount a class action against Canada Post ?]]></field>\r\n" +
			"    <field name=\"author_name\"><![CDATA[Imaproholdmybeer]]></field>\r\n" + 
			"    <field name=\"ID\"><![CDATA[h10u12]]></field>\r\n" + 
			"    <field name=\"type\"><![CDATA[Reddit]]></field>\r\n" +
			"    <field name=\"published_date\"><![CDATA[2020-06-11T02:55:40Z]]></field>\r\n" + 
			"    <field name=\"date_time\"><![CDATA[2020-06-11T02:55:40Z]]></field>\r\n" + 				
			"    <field name=\"content\"><![CDATA[Hi,\n\nAs you may be aware of, millions of Canadians are experiencing absurd delays with their postal service.\n\nDuring this crisis it became obvious to me that a lot of packages were being delivered on time, while others that are following the same route are accruing late days.\n\nI believe that Canada Post performance management is directly responsible for this situation. It is a way to reduce the charge backs from corporate clients and normal consumers.\n\nA better performance management process would include multiple KPIs and would adapt to ensure that the average wait time of late packs does not exceed reasonable expectations. Canada Post choose not to have such measures in place. And now we're all paying the price.\n\nFurthermore, because of their improper measurement of their network, they had to offer overtime and proceed with new \"emergency hires\". I believe this, along with the fact that their network is overloaded and therefor their surveillance capacity probably diminished, will result in an unprecedented amount of missing / stolen packages.\n\nI believe the Canadian public deserves answer, and that Canada Post management must be made accountable.\n\nManaging Canada Post on behalf of Canadians is a privilege. This is OUR postal service and we entrusted it in the care of these people, now it's no longer working and their management is creating unfair situations and making the delays worse for everybody.\n\nLets do something.\n\n&amp;#x200B;\n\nEDIT: We should send them a legal notice that they need to deliver the packs in order or face legal actions for violation of Canada Post Corporation Act. You can see the laws I believe were violated in the comments below.\n\n&amp;#x200B;]]></field>\r\n" +
			"    <field name=\"subreddit\"><![CDATA[CanadaPost]]></field>\r\n" +
			"    <field name=\"score\"><![CDATA[196]]></field>\r\n" +
			"    <field name=\"permalink\"><![CDATA[/r/CanadaPost/comments/h10u12/any_lawyers_here_willing_to_help_the_canadian/]]></field>\r\n" +
			"    <field name=\"url\"><![CDATA[https://www.reddit.com/r/CanadaPost/comments/h10u12/any_lawyers_here_willing_to_help_the_canadian/]]></field>\r\n" +
			"    <field name=\"thumbnail\"><![CDATA[self]]></field>\r\n" +
			"    <field name=\"rtag\"><![CDATA[Canada Post]]></field>\r\n" +
			"  </doc>\r\n"; 
	
	@Before
	public void setUp() {
		TextData txtData = mock(TextData.class);
		when(txtData.getId()).thenReturn("1583916");
		when(txtData.getReferenceId()).thenReturn("1583916");	
		when(txtData.getInteractionId()).thenReturn("1583916");
		when(txtData.getAuthorName()).thenReturn("ExcelImporter");
		when(txtData.getTitle()).thenReturn("1583916 Quejas de siniestros/prestaciones");
		when(txtData.getType()).thenReturn("Ticketing");
		when(txtData.getContent()).thenReturn("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer finibus leo purus, quis porttitor quam aliquet ut. Curabitur ullamcorper erat at facilisis luctus. Nunc facilisis interdum vestibulum. Nulla auctor ante sed purus scelerisque, tincidunt sollicitudin erat vulputate. Nam risus massa, ullamcorper a aliquam at, blandit in ante. Nam vestibulum, metus eu lacinia fermentum, felis massa venenatis leo, in ornare nibh eros eu odio. Ut sit amet egestas enim. Quisque justo urna, porttitor sed condimentum vitae, malesuada quis arcu. Donec eget lacinia lacus. Duis sagittis id nisl eget porttitor. Aliquam interdum vel ipsum ut blandit. Fusce sed eros a mi tincidunt malesuada.");	
		when(txtData.getAuthorName()).thenReturn("");	
		try {
			when(txtData.getPublishedDate()).thenReturn(DateUtil.utcToDate("2020-12-01T00:00:00Z"));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
		
		when(txtData.getContent()).thenReturn("");	
		
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("reference_id", "1583916");
		fields.put("published_date", "01/12/2020");
		fields.put("FechaCierre", "01/12/2020");
		fields.put("EsContactar", "S");
		fields.put("CPI", "201");
		fields.put("Producto", "Protección Hogar (OFERTABLE)");
		fields.put("TipoPeticion", "Quejas de siniestros/prestaciones");
		fields.put("ComentariosOficina", "Fusce lobortis massa at volutpat vulputate. Praesent vulputate quam vel turpis pharetra, dignissim sodales leo lobortis. Etiam diam ex, mollis eu fringilla eget, finibus at lorem. Vivamus posuere neque magna, sodales maximus ligula placerat eu. Maecenas diam orci, viverra vitae aliquam id, egestas et lorem. Sed ut ultricies eros, id ullamcorper urna. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse ac mauris vel metus porta faucibus. Sed gravida est sit amet sapien venenatis, at fermentum lacus bibendum. Nullam quis magna et augue tempor semper sit amet eu nulla. Sed at elementum sapien. Sed lacinia vulputate mi. Sed metus risus, mattis vel vehicula ut, pellentesque vel dui.");
		fields.put("Departamento", "Quejas y reclamaciones Siniestros BSSG");
		fields.put("Estado", "finalizada");
		fields.put("CPI", "201");
		fields.put("Familia", "Protección Diversos");
		fields.put("Producto", "Protección Hogar (OFERTABLE)");		
		fields.put("TipoPeticion", "Quejas de siniestros/prestaciones");
		fields.put("MotivoQueja", "Demora del Reparador");
		fields.put("OrigenQueja", "NULL");
									
		when(txtData.getFields()).thenReturn(fields);	
				
		txtDatas= new LinkedList<TextData>();	
		txtDatas.add(txtData);
	}
	
	@Test
	public void testStatusToString() {
		
		String xml = ExcelTransformer.textDataToString(txtDatas, "Insurance");
		
		String expectedXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<add>\r\n" +
				docXMLFragment +
				"</add>\r\n";
		
		assertEquals(expectedXML, xml);
	}
	
	/*
	 * @Test public void testStatusToXMLFile() { String outputXML =
	 * "test_h10u12.xml";
	 * 
	 * try { RedditTransformer.submissionsToXMLFile(posts, outputXML,
	 * "Canada Post"); } catch (IOException e) { fail(e.getMessage()); }
	 * 
	 * File xml = new File(outputXML); assertTrue(xml.exists());
	 * 
	 * //Remove test XML xml.delete(); }
	 */
}
