package org.conacry.caero.adapter.controller.convertor;

import org.conacry.caero.adapter.controller.ControllerError;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.entity.AircraftStub;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ResponseConvertorTest {

    @Test
    void aircraftToResponse_Aircraft_ReturnAircraftResponse() {
        var aircraft = AircraftStub.getFullActiveAircraft();
        var seatMap = aircraft.getSeats().stream()
                .collect(Collectors.toMap(seat -> seat.getSeatID().toString(), Function.identity()));

        var aircraftResponse = ResponseConvertor.aircraftToResponse(aircraft);

        assertEquals(aircraft.getAircraftID().toString(), aircraftResponse.getAircraftID());
        assertEquals(aircraft.getModel().toString(), aircraftResponse.getModel());
        assertEquals(aircraft.getStatus().toString(), aircraftResponse.getStatus());

        for (var seatResponse : aircraftResponse.getSeats()) {
            var seat = seatMap.get(seatResponse.getSeatID());
            assertEquals(seat.getSeatID().toString(), seatResponse.getSeatID());
            assertEquals(seat.getNumber().toString(), seatResponse.getNumber());
            assertEquals(seat.getFareCondition().toString(), seatResponse.getFareCondition());
        }
    }

    @Test
    void aircraftToResponse_AircraftIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ResponseConvertor.aircraftToResponse(null));
        assertEquals(ControllerError.AIRCRAFT_IS_NULL, ex.getCode());
    }

    @Test
    void aircraftsToResponses_AircraftListIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> ResponseConvertor.aircraftsToResponses(null));
        assertEquals(ControllerError.AIRCRAFT_LIST_IS_NULL, ex.getCode());
    }

    @Test
    void aircraftsToResponses_AircraftListIsEmpty_ReturnEmptyList() {
        var aircrafts = AircraftStub.getListFullAircraft(0);
        assertTrue(ResponseConvertor.aircraftsToResponses(aircrafts).isEmpty());
    }

    @Test
    void aircraftsToResponses_AircraftList_ReturnAircraftResponse() {
        var aircrafts = AircraftStub.getListFullAircraft(5);
        var aircraftResponses = ResponseConvertor.aircraftsToResponses(aircrafts);
        assertNotNull(aircraftResponses);
        assertEquals(aircrafts.size(), aircraftResponses.size());
    }
}