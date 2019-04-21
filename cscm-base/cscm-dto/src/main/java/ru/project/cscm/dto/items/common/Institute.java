package ru.project.cscm.dto.items.common;

import org.springframework.util.StringUtils;

import ru.project.cscm.dto.items.core.IdentifiableObject;

public final class Institute implements Comparable<Institute>, IdentifiableObject<String> {

	private final String id;
	private String description;
	
	public Institute(String instId, String description) {
		if (StringUtils.isEmpty(instId)) {
			throw new IllegalArgumentException("Institution ID can't be null");
		}
		this.id = instId;
		this.description = description;
	}

	public Institute(Institute inst) {
		this.id = inst.getId();
		this.description = inst.getDescription();
	}

	@Override
	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Institute obj) {
		return obj != null ? getId().compareTo(obj.getId()) : 1;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Institute && id.equals(((Institute) obj).getId());
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
