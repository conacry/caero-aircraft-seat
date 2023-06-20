package org.conacry.caero.boundary.model;

import lombok.Data;

@Data
public class AircraftCreateInfo {

    private final String model;
    private SeatConfiguration seatConfiguration;
}
