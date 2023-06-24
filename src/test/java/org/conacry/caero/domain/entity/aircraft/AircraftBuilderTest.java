package org.conacry.caero.domain.entity.aircraft;

import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.entity.ModelStub;
import org.conacry.caero.testdouble.entity.SeatStub;
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
    void build_StatusIsNull_ThrowEx() {
        var aircraftID = AircraftID.newID();
        var model = ModelStub.getModel();
        var builder = new AircraftBuilder();
        var ex = assertThrows(CodedException.class, () -> builder.aircraftID(aircraftID).model(model).build());
        assertEquals(AircraftError.AIRCRAFT_STATUS_IS_REQUIRED, ex.getCode());
    }

    @Test
    void build_SeatsDontSettled_ReturnAircraftWithEmptySeats() {
        var aircraftID = AircraftID.newID();
        var model = ModelStub.getModel();
        var builder = new AircraftBuilder();
        var status = AircraftStatus.ACTIVE;

        var aircraft = builder.aircraftID(aircraftID).
                model(model).
                status(status).build();
        assertNotNull(aircraft);
        assertEquals(aircraftID, aircraft.getAircraftID());
        assertEquals(model, aircraft.getModel());
        assertEquals(status, aircraft.getStatus());

        var seats = aircraft.getSeats();
        assertNotNull(seats);
        assertTrue(seats.isEmpty());
    }

    @Test
    void build_AllParamsDontSettled_ReturnAircraft() {
        var aircraftID = AircraftID.newID();
        var model = ModelStub.getModel();
        var seats = SeatStub.getSeatList(10);
        var status = AircraftStatus.ACTIVE;

        var aircraft = new AircraftBuilder().
                aircraftID(aircraftID).
                model(model).
                seats(seats).
                status(status).build();
        assertNotNull(aircraft);
        assertEquals(aircraftID, aircraft.getAircraftID());
        assertEquals(model, aircraft.getModel());
        assertEquals(status, aircraft.getStatus());

        var actualSeats = aircraft.getSeats();
        assertNotNull(actualSeats);
        assertFalse(actualSeats.isEmpty());
        assertIterableEquals(seats, actualSeats);
    }
}