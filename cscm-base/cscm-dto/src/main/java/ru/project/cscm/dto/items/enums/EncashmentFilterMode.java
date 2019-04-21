package ru.project.cscm.dto.items.enums;

import ru.project.cscm.dto.items.core.IdentifiableObject;

public enum EncashmentFilterMode implements IdentifiableObject<Integer> {

	ANY(0), NONE(1), STANDARD(2), EMERGENCY(4);

	private EncashmentFilterMode(int id) {
		this.id = id;
	}

	private int id;

	@Override
	public Integer getId() {
		return id;
	}
}
