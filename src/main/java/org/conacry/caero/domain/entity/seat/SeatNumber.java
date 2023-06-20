package org.conacry.caero.domain.entity.seat;

import java.util.Objects;
import java.util.regex.Pattern;

public class SeatNumber {

    private static final Pattern FORMAT_PATTERN = Pattern.compile("[^a-zA-Z0-9]");

    private final String value;

    public static SeatNumber from(String value) {
        if (value == null || value.isBlank()) {
            throw SeatError.errSeatNumberValueIsRequired();
        }

        var matcher = FORMAT_PATTERN.matcher(value);
        if (matcher.find()) {
            throw SeatError.errIllegalSeatNumberValue(value);
        }

        return new SeatNumber(value);
    }

    private SeatNumber(String value) {
        this.value = value;
    }

    public String getValue() {
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
        SeatNumber that = (SeatNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "SeatNumber{" +
                "value='" + value + '\'' +
                '}';
    }
}
