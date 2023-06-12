package org.conacry.caero.domain.entity.aircraft;

import org.conacry.caero.domain.entity.seat.SeatError;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.entity.ModelStub;
import org.conacry.caero.util.StringGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftBuilderTest {

    @Test
    void build_AircraftIDIsNull_ThrowEx() {
        var model = ModelStub.getModel();
        var builder = new AircraftBuilder();
        var ex = assertThrows(CodedException.class, () -> builder.model(model).build());
        assertEquals(AircraftError.AIRCRAFT_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void build_ModelIsNull_ThrowEx() {
        var aircraftID = AircraftID.newID();
        var builder = new AircraftBuilder();
        var ex = assertThrows(CodedException.class, () -> builder.aircraftID(aircraftID).build());
        assertEquals(AircraftError.AIRCRAFT_MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void build_SeatsIsNull_ThrowEx() {
        var aircraftID = AircraftID.newID();
        var model = ModelStub.getModel();
        var builder = new AircraftBuilder();

        var aircraft = builder.aircraftID(aircraftID).model(model).build();
        assertNotNull(aircraft);
        assertEquals(aircraftID, aircraft.getAircraftID());
        assertEquals(model, aircraft.getModel());

        var seats = aircraft.getSeats();
        assertNotNull(seats);
        assertTrue(seats.isEmpty());
    }
}