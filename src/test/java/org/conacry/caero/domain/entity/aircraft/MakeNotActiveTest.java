package org.conacry.caero.domain.entity.aircraft;


import org.conacry.caero.testdouble.entity.AircraftStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MakeNotActiveTest {

    @Test
    void makeNotActive_StatusIsChanged() {
        var aircraft = AircraftStub.getAircraftAllParameters();
        assertEquals(AircraftStatus.ACTIVE, aircraft.getStatus());
        aircraft.makeNotActive();
        assertEquals(AircraftStatus.NOT_ACTIVE, aircraft.getStatus());
    }
}
