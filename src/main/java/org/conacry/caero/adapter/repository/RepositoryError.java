package org.conacry.caero.adapter.repository;

import org.conacry.caero.domain.primitive.exception.CodedException;

public final class RepositoryError {

    public static final String AIRCRAFT_DB_MODEL_VALUE_IS_REQUIRED = "e5da67d6-001";
    public static final String AIRCRAFT_VALUE_IS_REQUIRED = "e5da67d6-002";

    private RepositoryError() {}

    public static CodedException errAircraftDbModelIsRequired() {
        var errMsg = "Value to create AircraftDbModel is required";
        return new CodedException(AIRCRAFT_DB_MODEL_VALUE_IS_REQUIRED, errMsg);
    }

    public static CodedException errAircraftIsRequired() {
        var errMsg = "Value to create Aircraft is required";
        return new CodedException(AIRCRAFT_VALUE_IS_REQUIRED, errMsg);
    }
}
