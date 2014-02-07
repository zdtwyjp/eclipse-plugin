package org.framework.cg.internal.ui.vo;

import org.framework.cg.internal.ui.utils.Constants;

import net.sf.json.JSONObject;

public class FieldModel {
	private Long no;

	private String fieldName;

	private String fieldType;

	private Boolean nullable;

	private String label;

	private String tagType;

	private Boolean queryable;

	public FieldModel() {}

	public FieldModel(Long no, String fieldName, String fieldType,
			Boolean nullable, String label, String tagType) {
		super();
		this.no = no;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.nullable = nullable;
		this.label = label;
		this.tagType = tagType;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public Boolean getQueryable() {
		return queryable;
	}

	public void setQueryable(Boolean queryable) {
		this.queryable = queryable;
	}

	public JSONObject toJSONObject() {
		JSONObject jo = new JSONObject();
		jo.put("no", this.no);
		jo.put("fieldName", this.fieldName);
		jo.put("label", this.label);
		jo.put("fieldType", this.fieldType);
		jo.put("tagType", this.tagType);
		jo.put("nullable", this.nullable);
		jo.put("queryable", this.queryable);
		return jo;
	}
}
