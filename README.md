# OTExploreExcelImporter
Excel importer for OpenText Explore (Voice of the customer solution)
This command-line application read an Excel file to ingest the columns of our interest, usually text messages. 

These text messages are inserted into the Solr Server used by **OpenText Explore**. 

Once the messages are available in **OpenText Explore** you can create your owns dashboards to analyze the information listened.


> [OpenText™ Explore](https://www.opentext.com/products-and-solutions/products/customer-experience-management/contact-center-workforce-optimization/opentext-explore) is a business discovery solution that allows business and call center professionals to view cross-channel interactions collectively for a comprehensive picture of customer behaviors and relationships. 

### Solr fields to have in mind

These are the key Solr fields that must be mapped in order to import the information into Explore:

   - **reference_id**
   - **interaction_id**
   - **title**
   - **author_name**
   - **ID**
   - **type**
   - **published_date**
   - **date_time**
   - **content**