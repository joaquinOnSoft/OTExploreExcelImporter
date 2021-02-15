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
package com.opentext.explore.importer.excel.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "excelName",
    "solrName",
    "type",
    "skip"
})
public class Field {

    @JsonProperty("excelName")
    private String excelName;
    @JsonProperty("solrName")
    private String solrName;
    @JsonProperty("type")
    private String type;
    @JsonProperty("format")
    private String format;    
    @JsonProperty("skip")
    private Boolean skip;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Field() {
    }

    /**
     * 
     * @param excelName
     * @param skip
     * @param solrName
     * @param type
     */
    public Field(String excelName, String solrName, String type, String format, Boolean skip) {
        super();
        this.excelName = excelName;
        this.solrName = solrName;
        this.type = type;
        this.format = format;
        this.skip = skip;
    }

    @JsonProperty("excelName")
    public String getExcelName() {
        return excelName;
    }

    @JsonProperty("excelName")
    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    @JsonProperty("solrName")
    public String getSolrName() {
        return solrName;
    }

    @JsonProperty("solrName")
    public void setSolrName(String solrName) {
        this.solrName = solrName;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }
        
    @JsonProperty("format")
    public String getFormat() {
		return format;
	}

    @JsonProperty("format")    
	public void setFormat(String format) {
		this.format = format;
	}

	@JsonProperty("skip")
    public Boolean getSkip() {
        return skip;
    }

    @JsonProperty("skip")
    public void setSkip(Boolean skip) {
        this.skip = skip;
    }
}
