package ru.project.cscm.ws.core.resources;

import javax.validation.constraints.NotNull;

import ru.project.cscm.dto.items.common.AtmGroupAttributeDescription;
import ru.project.cscm.dto.items.enums.AtmGroupType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtmGroupAttributeDescriptionResource {

	private final Integer attrId;
	private final String description;
	private final boolean required;
	private final AtmGroupType type;

	@JsonCreator
	public AtmGroupAttributeDescriptionResource(
			@JsonProperty("attrId") final Integer attrId,
			@JsonProperty("description") final String description,
			@JsonProperty("required") final boolean required,
			@JsonProperty("type") final AtmGroupType type) {
		this.attrId = attrId;
		this.description = description;
		this.required = required;
		this.type = type;
	}

	public AtmGroupAttributeDescriptionResource(
			@NotNull final AtmGroupAttributeDescription item) {
		this.attrId = item.getAttrId();
		this.description = item.getDescx();
		this.required = item.isRequired();
		this.type = item.getType();
	}

	public Integer getAttrId() {
		return attrId;
	}

	public String getDescription() {
		return description;
	}

	public boolean isRequired() {
		return required;
	}

	public AtmGroupType getType() {
		return type;
	}

}
