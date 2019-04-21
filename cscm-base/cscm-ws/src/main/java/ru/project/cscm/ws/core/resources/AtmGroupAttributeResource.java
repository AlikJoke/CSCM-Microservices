package ru.project.cscm.ws.core.resources;

import javax.validation.constraints.NotNull;

import ru.project.cscm.dto.items.common.AtmGroupAttribute;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtmGroupAttributeResource {

	private final Integer attrId;
	private final Integer groupId;
	private final boolean isUsed;
	private final String value;

	@JsonCreator
	public AtmGroupAttributeResource(
			@JsonProperty("attrId") final Integer attrId,
			@JsonProperty("groupId") final Integer groupId,
			@JsonProperty("isUsed") final boolean isUsed,
			@JsonProperty("value") final String value) {
		this.attrId = attrId;
		this.groupId = groupId;
		this.isUsed = isUsed;
		this.value = value;
	}

	public AtmGroupAttributeResource(@NotNull final AtmGroupAttribute item) {
		this.attrId = item.getAttributeId();
		this.groupId = item.getGroupId();
		this.isUsed = item.isUsed();
		this.value = item.getValue();
	}

	public Integer getAttrId() {
		return attrId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public String getValue() {
		return value;
	}

}
