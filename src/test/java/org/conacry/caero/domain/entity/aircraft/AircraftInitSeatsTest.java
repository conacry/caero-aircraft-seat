package org.conacry.caero.domain.entity.aircraft;

import org.conacry.caero.boundary.model.SeatConfiguration;
import org.conacry.caero.domain.entity.seat.Seat;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.entity.AircraftStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftInitSeatsTest {

    @Test
    void initSeats_GetListSeats() {
        var firstClassSeatsInfo = new SeatConfiguration.FirstClassSeatInfo();
        firstClassSeatsInfo.setRowCount(5);
        firstClassSeatsInfo.setSeatsPerRow(6);

        var businessSeatsInfo = new SeatConfiguration.BusinessSeatInfo();
        businessSeatsInfo.setRowCount(10);
        businessSeatsInfo.setSeatsPerRow(6);

        var economySeatsInfo = new SeatConfiguration.EconomySeatInfo();
        economySeatsInfo.setRowCount(20);
        economySeatsInfo.setSeatsPerRow(9);

        var seatConfiguration = new SeatConfiguration();
        seatConfiguration.setFirstClassSeatInfo(firstClassSeatsInfo);
        seatConfiguration.setBusinessSeatInfo(businessSeatsInfo);
        seatConfiguration.setEconomySeatInfo(economySeatsInfo);

        var aircraft = AircraftStub.getAircraftWithEmptySeats();

        aircraft.initSeats(seatConfiguration);
        var seats = aircraft.getSeats();
        assertFalse(seats.isEmpty());
        for (Seat seat: seats) {
            System.out.println(seat);
        }
    }

    @Test
    void initSeats_SeatsListIsCreated_ThrowEx() {
        var firstClassSeatsInfo = new SeatConfiguration.FirstClassSeatInfo();
        firstClassSeatsInfo.setRowCount(5);
        firstClassSeatsInfo.setSeatsPerRow(6);

        var businessSeatsInfo = new SeatConfiguration.BusinessSeatInfo();
        businessSeatsInfo.setRowCount(10);
        businessSeatsInfo.setSeatsPerRow(6);

        var economySeatsInfo = new SeatConfiguration.EconomySeatInfo();
        economySeatsInfo.setRowCount(20);
        economySeatsInfo.setSeatsPerRow(9);

        var seatConfiguration = new SeatConfiguration();
        seatConfiguration.setFirstClassSeatInfo(firstClassSeatsInfo);
        seatConfiguration.setBusinessSeatInfo(businessSeatsInfo);
        seatConfiguration.setEconomySeatInfo(economySeatsInfo);

        var aircraft = AircraftStub.getAircraftAllParameters();
        var ex = assertThrows(CodedException.class, () -> aircraft.initSeats(seatConfiguration));
        assertEquals(AircraftError.AIRCRAFT_SEATS_IS_CREATED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void initSeats_SeatConfigurationIsNull_ThrowEx() {
        var aircraft = AircraftStub.getAircraftWithEmptySeats();

        var ex = assertThrows(CodedException.class, () -> aircraft.initSeats(null));
        assertEquals(AircraftError.SEAT_CONFIGURATION_IS_REQUIRED, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    void initSeats_SeatsPerRowIsIllegalValue_ThrowEx() {
        var firstClassSeatsInfo = new SeatConfiguration.FirstClassSeatInfo();
        firstClassSeatsInfo.setRowCount(5);
        firstClassSeatsInfo.setSeatsPerRow(21);

        var businessSeatsInfo = new SeatConfiguration.BusinessSeatInfo();
        businessSeatsInfo.setRowCount(10);
        businessSeatsInfo.setSeatsPerRow(20);

        var economySeatsInfo = new SeatConfiguration.EconomySeatInfo();
        economySeatsInfo.setRowCount(20);
        economySeatsInfo.setSeatsPerRow(19);

        var seatConfiguration = new SeatConfiguration();
        seatConfiguration.setFirstClassSeatInfo(firstClassSeatsInfo);
        seatConfiguration.setBusinessSeatInfo(businessSeatsInfo);
        seatConfiguration.setEconomySeatInfo(economySeatsInfo);

        var aircraft = AircraftStub.getAircraftWithEmptySeats();

        var ex = assertThrows(CodedException.class, () -> aircraft.initSeats(seatConfiguration));
        assertEquals(AircraftError.ILLEGAL_SEATS_PER_ROW_VALUE, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }


}
