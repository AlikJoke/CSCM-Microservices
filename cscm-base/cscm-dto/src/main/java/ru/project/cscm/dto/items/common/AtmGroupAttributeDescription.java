package ru.project.cscm.dto.items.common;

import ru.project.cscm.dto.items.enums.AtmGroupType;

public class AtmGroupAttributeDescription {

	private Integer attrId;
	private String descx;
	private boolean required;
	private AtmGroupType type;

	public AtmGroupAttributeDescription(Integer attrId, String descx,
			Boolean required, Integer type) {
		super();
		this.attrId = attrId;
		this.descx = descx;
		this.required = required;
		this.type = AtmGroupType.getAtmGroupType(type);
	}

	public Integer getAttrId() {
		return attrId;
	}

	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}

	public String getDescx() {
		return descx;
	}

	public void setDescx(String descx) {
		this.descx = descx;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public AtmGroupType getType() {
		return type;
	}

	public void setType(AtmGroupType type) {
		this.type = type;
	}

}
