package ru.project.cscm.monitoring.model.enums;

import java.util.Arrays;

public enum AtmMalfunction {

	CARD_READER(1),

	RECEIPT_PRINTER(2),

	DISPENSER(3),

	BILL_ACCEPTOR(4),

	JOURNAL_PRINTER(5),

	IS_FULL(6),

	FIREWALL(7),

	NONE(0);

	private final Integer id;

	private AtmMalfunction(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public static AtmMalfunction value(Integer id) {
		return Arrays.stream(AtmMalfunction.values())
				.filter(s -> s.getId() == id).findAny()
				.orElseThrow(IllegalArgumentException::new);
	}
}
