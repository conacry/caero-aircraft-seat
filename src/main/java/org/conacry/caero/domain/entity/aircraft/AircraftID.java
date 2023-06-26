package org.conacry.caero.domain.entity.aircraft;

import java.util.Objects;
import java.util.UUID;

public final class AircraftID {
    private final UUID value;

    public static AircraftID newID() {
        var value = UUID.randomUUID();
        return new AircraftID(value);
    }

    public static AircraftID from(String valueStr) {
        if (valueStr == null || valueStr.isBlank()) {
            throw AircraftError.errAircraftIDValueIsRequired();
        }

        try {
            var value = UUID.fromString(valueStr);
            return new AircraftID(value);
        } catch (IllegalArgumentException e) {
            throw AircraftError.errIllegalAircraftIDValue(valueStr, e);
        }
    }

    private AircraftID(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var that = (AircraftID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
