package ru.project.cscm.dto.items.enums;

import java.util.Arrays;

public enum AtmState {

	OPENED(1),

	CLOSED(2),

	DISCONNECTED(3);

	private final Integer id;

	private AtmState(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public static AtmState value(Integer id) {
		return Arrays.stream(AtmState.values()).filter(s -> s.getId() == id)
				.findAny().orElseThrow(IllegalArgumentException::new);
	}
}
