package ru.project.cscm.dto.items.enums;

import java.util.Arrays;

import ru.project.cscm.dto.items.core.IdentifiableObject;

public enum AtmGroupType implements IdentifiableObject<Integer> {

	FORECAST_REGION(1), ATM_MODEL(2), ENCASHMENT_PRICE(4), USER_TO_ATM(5);

	private int id;

	private AtmGroupType(int groupTypeId) {
		this.id = groupTypeId;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public static AtmGroupType[] getAtmAttributeGroupTypes() {
		return new AtmGroupType[] { FORECAST_REGION, ATM_MODEL,
				ENCASHMENT_PRICE };
	}

	public static AtmGroupType getAtmGroupType(Integer typeId) {
		return Arrays.stream(AtmGroupType.values())
				.filter(v -> v.getId() == typeId).findAny()
				.orElseThrow(() -> new IllegalArgumentException());
	}
}
