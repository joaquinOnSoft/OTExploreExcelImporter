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

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "field"
})
public class TextDataImporterMapping {

    @JsonProperty("field")
    private List<Field> field = new ArrayList<Field>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public TextDataImporterMapping() {
    }

    /**
     * 
     * @param field
     */
    public TextDataImporterMapping(List<Field> field) {
        super();
        this.field = field;
    }

    @JsonProperty("field")
    public List<Field> getField() {
        return field;
    }

    @JsonProperty("field")
    public void setField(List<Field> field) {
        this.field = field;
    }

}
