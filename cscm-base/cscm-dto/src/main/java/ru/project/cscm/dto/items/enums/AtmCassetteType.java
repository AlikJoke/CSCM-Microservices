package ru.project.cscm.dto.items.enums;

import ru.project.cscm.dto.items.core.IdentifiableObject;

public enum AtmCassetteType implements IdentifiableObject<Integer> {

    CASH_OUT_CASS(1), CASH_IN_CASS(2), CASH_IN_R_CASS(3);

    private int id;

    private AtmCassetteType(int id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public static AtmCassetteType valueOf(Integer id) {
        for (AtmCassetteType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }

        throw new IllegalArgumentException();
    }
}
