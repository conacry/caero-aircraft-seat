package org.conacry.caero.domain.entity.seat;

import java.util.UUID;

public final class SeatID {

    private final UUID value;

    public static SeatID newID() {
        var value = UUID.randomUUID();
        return new SeatID(value);
    }

    public static SeatID from(String valueStr) {
        if (valueStr == null || valueStr.isBlank()) {
            throw SeatError.errSeatIDValueIsRequired();
        }

        try {
            var value = UUID.fromString(valueStr);
            return new SeatID(value);
        } catch (IllegalArgumentException e) {
            throw SeatError.errIllegalSeatIDValue(valueStr, e);
        }
    }

    private SeatID(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
}
