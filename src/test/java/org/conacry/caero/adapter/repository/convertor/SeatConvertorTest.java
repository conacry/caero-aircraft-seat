package org.conacry.caero.adapter.repository.convertor;

import org.conacry.caero.adapter.repository.RepositoryError;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.entity.AircraftStub;
import org.conacry.caero.testdouble.entity.SeatStub;
import org.conacry.caero.testdouble.repository.SeatDbModelStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatConvertorTest {

    @Test
    void toEntity_SeatDbModelIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> SeatConvertor.toEntity(null));
        assertEquals(RepositoryError.SEAT_DB_MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntity_AllParamsIsValid_ReturnSeat() {
        var aircraftID = AircraftStub.getFullAircraft().getAircraftID();
        var seatDbModel = SeatDbModelStub.getSeatDbModel(aircraftID);
        var seat = SeatConvertor.toEntity(seatDbModel);
        assertNotNull(seat);
    }

    @Test
    void toEntities_ListSeatsDbModelIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> SeatConvertor.toEntities(null));
        assertEquals(RepositoryError.LIST_SEATS_DB_MODEL_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toEntities_AllParamsIsValid_ReturnListSeat() {
        var aircraftID = AircraftStub.getFullAircraft().getAircraftID();
        var seatsDbModel = SeatDbModelStub.getSeatDbModels(5, aircraftID);
        var seats = SeatConvertor.toEntities(seatsDbModel);
        assertNotNull(seats);
    }

    @Test
    void toModel_SeatIsNull_ThrowEx() {
        var aircraftID = AircraftStub.getFullAircraft().getAircraftID();
        var ex = assertThrows(CodedException.class, () -> SeatConvertor.toModel(null, aircraftID));
        assertEquals(RepositoryError.SEAT_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModel_AircraftIdIsNull_ThrowEx() {
        var seat = SeatStub.getSeat();
        var ex = assertThrows(CodedException.class, () -> SeatConvertor.toModel(seat, null));
        assertEquals(RepositoryError.AIRCRAFT_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModel_AllParamsIsValid_ReturnSeatDbModel() {
        var aircraftID = AircraftStub.getFullAircraft().getAircraftID();
        var seat = SeatStub.getSeat();
        var seatDbModel = SeatConvertor.toModel(seat, aircraftID);
        assertNotNull(seatDbModel);
    }

    @Test
    void toModels_ListSeatIsNull_ThrowEx() {
        var aircraftID = AircraftStub.getFullAircraft().getAircraftID();
        var ex = assertThrows(CodedException.class, () -> SeatConvertor.toModels(null, aircraftID));
        assertEquals(RepositoryError.LIST_SEATS_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModels_AircraftIdIsNull_ThrowEx() {
        var seats = SeatStub.getSeatList(5);
        var ex = assertThrows(CodedException.class, () -> SeatConvertor.toModels(seats, null));
        assertEquals(RepositoryError.AIRCRAFT_ID_IS_REQUIRED, ex.getCode());
    }

    @Test
    void toModels_AllParamsIsValid_ReturnListSeatsDbModel() {
        var aircraftID = AircraftStub.getFullAircraft().getAircraftID();
        var seats = SeatStub.getSeatList(5);
        var seatsDbModel = SeatConvertor.toModels(seats, aircraftID);
        assertNotNull(seatsDbModel);
    }

}
