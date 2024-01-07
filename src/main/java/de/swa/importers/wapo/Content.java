package de.swa.importers.wapo;

import java.util.HashMap;
import java.util.Map;

/** data structure to represent a RSS item **/
public class Content {
	private String content;
	private String mime;
	private String type;
	private String subtype;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
