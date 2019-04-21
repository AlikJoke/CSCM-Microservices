package ru.project.cscm.ws.core.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterResource {

	private final List<Integer> groupsIds;
	private final List<Integer> atmsIds;

	public List<Integer> getGroupsIds() {
		return groupsIds;
	}

	public List<Integer> getAtmsIds() {
		return atmsIds;
	}

	@JsonCreator
	public FilterResource(@JsonProperty("groupsIds") List<Integer> groupIds,
			@JsonProperty("atmsIds") List<Integer> atmIds) {
		super();
		this.groupsIds = groupIds;
		this.atmsIds = atmIds;
	}

}
