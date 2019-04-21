package ru.project.cscm.dto.items.enums;

import ru.project.cscm.dto.items.core.IdentifiableObject;

public enum EncashmentActionType implements IdentifiableObject<Integer> {

	UNLOAD(1), LOAD(2);

	private int id;

	private EncashmentActionType(int id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}

}
