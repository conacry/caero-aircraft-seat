package org.conacry.caero.adapter.repository;

import org.conacry.caero.domain.primitive.exception.CodedException;

public final class RepositoryError {

    public static final String AIRCRAFT_DB_MODEL_IS_REQUIRED = "e5da67d6-001";
    public static final String AIRCRAFT_IS_REQUIRED = "e5da67d6-002";
    public static final String SEAT_DB_MODEL_IS_REQUIRED = "e5da67d6-003";
    public static final String SEAT_IS_REQUIRED = "e5da67d6-004";
    public static final String AIRCRAFT_ID_IS_REQUIRED = "e5da67d6-005";
    public static final String LIST_SEATS_IS_REQUIRED = "e5da67d6-006";
    public static final String LIST_SEATS_DB_MODEL_IS_REQUIRED = "e5da67d6-007";
    public static final String LIST_AIRCRAFT_DB_MODEL_IS_REQUIRED = "e5da67d6-008";
    public static final String LIST_AIRCRAFT_IS_REQUIRED = "e5da67d6-009";
    public static final String MODEL_IS_REQUIRED = "e5da67d6-010";

    private RepositoryError() {}

    public static CodedException errAircraftDbModelIsRequired() {
        var errMsg = "Value to create AircraftDbModel is required";
        return new CodedException(AIRCRAFT_DB_MODEL_IS_REQUIRED, errMsg);
    }

    public static CodedException errAircraftIsRequired() {
        var errMsg = "Value to create Aircraft is required";
        return new CodedException(AIRCRAFT_IS_REQUIRED, errMsg);
    }

    public static CodedException errSeatDbModelIsRequired() {
        var errMsg = "Value to create SeatDbModel is required";
        return new CodedException(SEAT_DB_MODEL_IS_REQUIRED, errMsg);
    }

    public static CodedException errSeatIsRequired() {
        var errMsg = "Value to create Seat is required";
        return new CodedException(SEAT_IS_REQUIRED, errMsg);
    }

    public static CodedException errAircraftIdIsRequired() {
        var errMsg = "Value to create AircraftID is required";
        return new CodedException(AIRCRAFT_ID_IS_REQUIRED, errMsg);
    }

    public static CodedException errListSeatsIsRequired() {
        var errMsg = "Value to create ListSeats is required";
        return new CodedException(LIST_SEATS_IS_REQUIRED, errMsg);
    }

    public static CodedException errListSeatsDbModelIsRequired() {
        var errMsg = "Value to create ListSeatsDbModel is required";
        return new CodedException(LIST_SEATS_DB_MODEL_IS_REQUIRED, errMsg);
    }

    public static CodedException errListAircraftDbModelsIsRequired() {
        var errMsg = "Value to create ListAircraftDbModels is required";
        return new CodedException(LIST_AIRCRAFT_DB_MODEL_IS_REQUIRED, errMsg);
    }

    public static CodedException errListAircraftIsRequired() {
        var errMsg = "Value to create ListAircraft is required";
        return new CodedException(LIST_AIRCRAFT_IS_REQUIRED, errMsg);
    }

    public static CodedException errModelIsRequired() {
        var errMsg = "Model is required";
        return new CodedException(MODEL_IS_REQUIRED, errMsg);
    }
}
