package org.conacry.caero.domain.entity.aircraft;


import org.conacry.caero.domain.primitive.exception.CodedException;

public final class AircraftError {
    public static final String AIRCRAFT_ID_VALUE_IS_REQUIRED = "01b85bf5-001";
    public static final String ILLEGAL_AIRCRAFT_ID_VALUE = "01b85bf5-002";
    public static final String MODEL_VALUE_IS_REQUIRED = "01b85bf5-003";
    public static final String ILLEGAL_MODEL_VALUE = "01b85bf5-004";
    public static final String AIRCRAFT_ID_IS_REQUIRED = "01b85bf5-005";
    public static final String AIRCRAFT_MODEL_IS_REQUIRED = "01b85bf5-006";

    private AircraftError() {
    }

    public static CodedException errAircraftIDValueIsRequired() {
        var errMsg = "Value to create AircraftID is required";
        return new CodedException(AIRCRAFT_ID_VALUE_IS_REQUIRED, errMsg);
    }

    public static CodedException errIllegalAircraftIDValue(String valueStr, Throwable cause) {
        var errMsg = String.format("Illegal value = '%s' format to create AircraftID", valueStr);
        return new CodedException(ILLEGAL_AIRCRAFT_ID_VALUE, errMsg, cause);
    }

    public static CodedException errModelValueIsRequired() {
        var errMsg = "Value to create Model is required";
        return new CodedException(MODEL_VALUE_IS_REQUIRED, errMsg);
    }

    public static CodedException errIllegalModelValue(String value) {
        var errMsg = String.format("Illegal value = '%s' format to create Model", value);
        return new CodedException(ILLEGAL_MODEL_VALUE, errMsg);
    }
/*--------------------------------------------------------------------------------------------------------------------*/
    public static CodedException errAircraftIDIsRequired() {
        var errMsg = "Aircraft ID is required";
        return new CodedException(AIRCRAFT_ID_IS_REQUIRED, errMsg);
    }

    public static CodedException errModelIsRequired() {
        var errMsg = "Aircraft Model is required";
        return new CodedException(AIRCRAFT_MODEL_IS_REQUIRED, errMsg);
    }
}
