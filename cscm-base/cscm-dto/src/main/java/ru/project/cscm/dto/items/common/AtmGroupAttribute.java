package ru.project.cscm.dto.items.common;

public class AtmGroupAttribute {

	private Integer attributeId;
	private Integer groupId;
	private String value;
	private boolean isUsed;

	public AtmGroupAttribute(Integer attributeId, Integer groupId,
			Boolean isUsed, String value) {
		super();
		this.attributeId = attributeId;
		this.groupId = groupId;
		this.value = value;
		this.isUsed = isUsed;
	}

	public Integer getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

}
