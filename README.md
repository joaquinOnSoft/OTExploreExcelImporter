# OTExploreExcelImporter
Excel importer for OpenText Explore (Voice of the customer solution)
This command-line application read an Excel file to ingest the columns of our interest, usually text messages. 

These text messages are inserted into the Solr Server used by **OpenText Explore**. 

Once the messages are available in **OpenText Explore** you can create your owns dashboards to analyze the information listened.


> [OpenText™ Explore](https://www.opentext.com/products-and-solutions/products/customer-experience-management/contact-center-workforce-optimization/opentext-explore) is a business discovery solution that allows business and call center professionals to view cross-channel interactions collectively for a comprehensive picture of customer behaviors and relationships. 

## Command line execution 

This utility is distributed as a runnable .jar file.

These are the accepted parameters:

usage: java -jar OTExploreExcelImporter-21.1.jar
 * -h, --host			(Optional)		Solr host URL (used by OpenText Explore). Default value: http://localhost:8983
 * -t, --tag			(Optional)		Explore Importer tag. Added to each article importer. Default value: "Excel Importer"
 * -e, --excel			(Mandatory)		Excel file to be imported  
 * -c, --config			(Mandatory)		JSON file that defines the mapping between excel columns and Solr fields

### Examples of invocation

```
$ java -jar OTExploreExcelImporter.21.1.jar --excel input_example.xlsx --config excel_mapping.json 

$ java -jar OTExploreExcelImporter.21.1.jar --excel input_example.xlsx --config excel_mapping.json --host http://localhost:8983 

$ java -jar OTExploreExcelImporter.21.1.jar --excel input_example.xlsx --config excel_mapping.json --host http://localhost:8983 --tag "Excel Importer"

$ java -jar OTExploreExcelImporter.21.1.jar --excel input_example.xlsx --config excel_mapping.json --tag "Excel Importer"
```


## Configuration file: excel_mapping.json

Configuration file that define the mapping between excel fields and Solr fields:

There are two main section on the configuration file: 
   * **fields**: Define the mapping between an excel field and a Solr field
   * **fieldHandlers**: Define which field handler must be used to set a specific Solr field
      
Each *field* supports theses properties:

 * **excelName**: Excel column name
 * **solrName**: Solr field name


> These are the key Solr fields that must be mapped in order to import the information into Explore:
> 
> - **reference_id**: Reference identifier 
> - **interaction_id**: Interaction identifier
> - **title**: Interaction title
> - **author_name**: Author name (usually an Explore user)
> - **ID**: Identifier
> - **type**: Content type. You can reuse pre-existing types or you can create your own
> - **published_date**: Interactio's publication date
> - **date_time**:  Interactio's creation date
> - **content**: Interaction content (call transcription, Twitter message, e-mail...)
>
> You can reference your owns Solr fields. It will require additional configuration on Explore
    
 * **type**: Data type. Supported values: integer, string, date
 * **skip**: Flag to indicate that this file must be ignored or not. Valid values: true, false
 * **format**: (Optional) Date format to apply on a data fields. [Check valid formats](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html)

This **excel_mapping.json** file shows an example: 

```json

{
	"fields": [
		{
			"excelName": "Identificadorticket",
			"solrName": "reference_id",
			"type": "integer",
			"skip": false			
		},
		{
			"excelName": "FechaApertura",
			"solrName": "published_date",
			"type": "date",			
			"skip": false,
			"format": "MM/dd/yy"					
		},
		{
			"excelName": "FechaCierre",
			"solrName": "FechaCierre",
			"type": "date",					
			"skip": false,
			"format": "MM/dd/yy"								
		},
		{
			"excelName": "EsContactar",
			"solrName": "EsContactar",
			"type": "string",
			"skip": false					
		},
		{
			"excelName": "CPI",
			"solrName": "CPI",
			"type": "integer",
			"skip": false					
		},
		{
			"excelName": "Producto",
			"solrName": "Producto",
			"type": "string",
			"skip": false					
		},		
		{
			"excelName": "TipoPeticion",
			"solrName": "TipoPeticion",
			"type": "string",
			"skip": false					
		},
		{
			"excelName": "Comentarios del back office tramitador",
			"solrName": "content",
			"type": "string",
			"skip": false					
		},
		{
			"excelName": "Comentarios_Oficina sobre la queja",
			"solrName": "ComentariosOficina",
			"type": "string",
			"skip": false					
		},
		{
			"excelName": "Departamento",
			"solrName": "Departamento",
			"type": "string",
			"skip": false					
		},
		{
			"excelName": "Estado",
			"solrName": "Estado",
			"type": "string",
			"skip": false					
		},
		{
			"excelName": "CPI",
			"solrName": "CPI",
			"type": "integer",
			"skip": true					
		},
		{
			"excelName": "Familia",
			"solrName": "Familia",
			"type": "string",
			"skip": false					
		},
		{
			"excelName": "Producto",
			"solrName": "Producto",
			"type": "integer",
			"skip": true					
		},		
		{
			"excelName": "TipoPeticion",
			"solrName": "TipoPeticion",
			"type": "string",
			"skip": true					
		},
		{
			"excelName": "Motivo_Queja",
			"solrName": "MotivoQueja",
			"type": "string",
			"skip": false					
		},
		{
			"excelName": "Origen_Queja_2",
			"solrName": "OrigenQueja",
			"type": "string",
			"skip": false					
		}																												
	],
	"fieldHandlers":[
		{
			"inputSolrNames": ["reference_id"],
			"outputSolrNames": ["interaction_id", "ID"],
			"javaClass": "com.opentext.explore.importer.excel.fieldhandlers.FieldHandlerCopy"
		},
		{
			"inputSolrNames": ["content", "ComentariosOficina"],
			"outputSolrNames": ["content"],
			"javaClass": "com.opentext.explore.importer.excel.fieldhandlers.FieldHandlerConcat"
		},
		{
			"inputSolrNames": ["reference_id", "Producto", "TipoPeticion"],
			"outputSolrNames": ["title"],
			"javaClass": "com.opentext.explore.importer.excel.fieldhandlers.FieldHandlerConcat"
		}
	]
}
```

### Solr fields to have in mind

These are the key Solr fields that must be mapped in order to import the information into Explore:
