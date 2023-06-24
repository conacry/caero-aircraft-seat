package org.conacry.caero.domain.usecase;

import org.conacry.caero.domain.primitive.exception.CodedException;

public final class UseCaseError {

    public static final String AIRCRAFT_CREATE_INFO_REQUIRED = "73e33f9e-001" ;
    public static final String AIRCRAFT_IS_ALREADY_EXISTED = "73e33f9e-002";

    private UseCaseError() {
    }

    public static CodedException errAircraftCreateInfoRequired(){
        var errMsg = "Aircraft create info is required";
        return new CodedException(AIRCRAFT_CREATE_INFO_REQUIRED , errMsg);
    }

    public static CodedException errAircraftIsAlreadyExisted() {
        var errMsg = "Aircraft is already existed";
        return new CodedException(AIRCRAFT_IS_ALREADY_EXISTED, errMsg);
    }
}