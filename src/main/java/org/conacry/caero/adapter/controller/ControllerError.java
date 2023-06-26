package org.conacry.caero.adapter.controller;

import org.conacry.caero.domain.primitive.exception.CodedException;

public final class ControllerError {

    public static final String REQUEST_IS_REQUIRED = "05e416d3-001";
    public static final String SEAT_CONFIG_IS_REQUIRED = "05e416d3-002";

    private ControllerError() {
    }

    public static CodedException errRequestIsRequired() {
        var errMsg = "request is required";
        return new CodedException(REQUEST_IS_REQUIRED , errMsg);
    }

    public static CodedException errRequestNullIsRequired() {
        var errMsg = "request null is required";
        return new CodedException(SEAT_CONFIG_IS_REQUIRED, errMsg);
    }
}

