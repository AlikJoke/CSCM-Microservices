package ru.project.cscm.ws.core.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import ru.project.cscm.dto.items.common.AtmGroup;
import ru.project.cscm.dto.items.enums.AtmGroupType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AtmGroupResource {

	private final Integer id;
	private final String name;
	private final String description;
	private final AtmGroupType type;
	private final List<AtmResource> atms;
	private final List<AtmResource> atmsNotInGroup;
	
	public final AtmGroupType[] groupTypes = AtmGroupType.getAtmAttributeGroupTypes();

	@JsonCreator
	public AtmGroupResource(@JsonProperty("id") final Integer id,
			@JsonProperty("description") final String description,
			@JsonProperty("name") final String name,
			@JsonProperty("atms") final List<AtmResource> atms,
			@JsonProperty("type") final AtmGroupType type) {
		this.id = id;
		this.description = description;
		this.name = name;
		this.atms = atms;
		this.atmsNotInGroup = null;
		this.type = type;
	}

	public AtmGroupResource(@NotNull final AtmGroup item) {
		this.id = item.getId();
		this.name = item.getName();
		this.description = item.getDescription();
		this.atms = item.getAtms().stream().map(a -> new AtmResource(a))
				.collect(Collectors.toList());
		this.atmsNotInGroup = new ArrayList<>();
		this.type = item.getType();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<AtmResource> getAtms() {
		return atms;
	}

	public List<AtmResource> getAtmsNotInGroup() {
		return atmsNotInGroup;
	}

	public AtmGroupType getType() {
		return type;
	}

}
