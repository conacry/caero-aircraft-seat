package org.conacry.caero.domain.entity.aircraft;

import java.util.Objects;
import java.util.regex.Pattern;

public class Model {

    private static final Pattern FORMAT_PATTERN = Pattern.compile("[^a-zA-Z0-9-]");

    private final String value;

    public static Model from(String value) {
        if (value == null || value.isBlank()) {
            throw AircraftError.errModelValueIsRequired();
        }

        var matcher = FORMAT_PATTERN.matcher(value);
        if (matcher.find()) {
            throw AircraftError.errIllegalModelValue(value);
        }

        return new Model(value);
    }

    private Model(String value) {
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

        var model = (Model) o;
        return Objects.equals(value, model.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
