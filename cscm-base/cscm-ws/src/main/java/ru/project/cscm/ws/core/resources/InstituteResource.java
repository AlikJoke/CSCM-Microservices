package ru.project.cscm.ws.core.resources;

import javax.validation.constraints.NotNull;

import ru.project.cscm.dto.items.common.Institute;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstituteResource {

	private final String id;
	private final String description;

	@JsonCreator
	public InstituteResource(@JsonProperty("id") final String id,
			@JsonProperty("description") final String description) {
		this.id = id;
		this.description = description;
	}

	public InstituteResource(@NotNull final Institute item) {
		this.id = item.getId();
		this.description = item.getDescription();
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}
