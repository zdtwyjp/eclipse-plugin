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

	private Boolean columnDisplayable;

	private Boolean unique;

	public FieldModel() {}

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

	public Boolean getColumnDisplayable() {
		return columnDisplayable;
	}

	public void setColumnDisplayable(Boolean columnDisplayable) {
		this.columnDisplayable = columnDisplayable;
	}

	public Boolean getUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
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
		jo.put("columnDisplayable", this.columnDisplayable);
		jo.put("unique", this.unique);
		return jo;
	}
}
