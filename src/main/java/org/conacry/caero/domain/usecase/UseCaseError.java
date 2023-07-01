package org.conacry.caero.domain.usecase;

import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.primitive.exception.CodedException;

public final class UseCaseError {

    public static final String AIRCRAFT_CREATE_INFO_REQUIRED = "73e33f9e-001";
    public static final String AIRCRAFT_IS_ALREADY_EXISTED = "73e33f9e-002";
    public static final String AIRCRAFT_IS_NOT_FOUND = "73e33f9e-003";



    private UseCaseError() {
    }

    public static CodedException errAircraftCreateInfoRequired() {
        var errMsg = "Aircraft create info is required";
        return new CodedException(AIRCRAFT_CREATE_INFO_REQUIRED, errMsg);
    }

    public static CodedException errAircraftIsAlreadyExisted() {
        var errMsg = "Aircraft is already existed";
        return new CodedException(AIRCRAFT_IS_ALREADY_EXISTED, errMsg);
    }

    public static CodedException errAircraftNotFound(AircraftID aircraftID) {
        var errMsg = String.format("Aircraft is not found by ID: %s", aircraftID.getValue().toString());
        return new CodedException(AIRCRAFT_IS_NOT_FOUND, errMsg);
    }
}