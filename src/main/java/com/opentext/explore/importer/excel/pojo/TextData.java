package com.opentext.explore.importer.excel.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TextData {
	public String referenceId;
	public String interactionId;
	public String title;
	public String authorName;
	public String id;
	public String type;
	public Date publishedDate;
	public Date dateTime;
	public String content;
	public Map<String, String> fields;
	
	public TextData() {
		fields = new HashMap<String, String>();
	}

	public String getReferenceId() {
		return referenceId;
	}
	
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	
	public String getInteractionId() {
		return interactionId;
	}
	
	public void setInteractionId(String interactionId) {
		this.interactionId = interactionId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Date getPublishedDate() {
		return publishedDate;
	}
	
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getFields() {
		return fields;
	}

	public void addField(String key, String value) {
		fields.put(key, value);
	}

	public String getField(String key) {
		return fields.get(key);
	}				
}
