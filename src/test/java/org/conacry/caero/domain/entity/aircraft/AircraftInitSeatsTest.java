package org.conacry.caero.domain.entity.aircraft;

import org.conacry.caero.domain.entity.seat.FareCondition;
import org.conacry.caero.domain.entity.seat.Seat;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.entity.AircraftStub;
import org.conacry.caero.testdouble.entity.SeatConfigurationStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftInitSeatsTest {

    @Test
    void initSeats_SeatConfiguration_GetListSeats() {
        var seatConfiguration = SeatConfigurationStub.getSeatConfiguration();
        var aircraft = AircraftStub.getAircraftWithEmptySeats();

        aircraft.initSeats(seatConfiguration);
        var seats = aircraft.getSeats();
        assertFalse(seats.isEmpty());

        var totalSeats = 0;

        var firstClassSeatsOpt = seatConfiguration.getFirstClassSeatInfo();
        if (firstClassSeatsOpt.isPresent()) {
            var firstClassSeatsRow = firstClassSeatsOpt.get().getRowCount();
            var firstClassSeatsPerRow = firstClassSeatsOpt.get().getSeatsPerRow();
            var firstClassSeats = firstClassSeatsRow * firstClassSeatsPerRow;
            var targetFirstClassSeats = 0;
            for (Seat seat : seats) {
                if (seat.getFareCondition() == FareCondition.FIRST_CLASS) {
                    targetFirstClassSeats++;
                }
            }
            totalSeats = totalSeats + targetFirstClassSeats;
            assertEquals(firstClassSeats, targetFirstClassSeats);
        }

        var businessSeatsOpt = seatConfiguration.getBusinessSeatInfo();
        if (businessSeatsOpt.isPresent()) {
            var businessSeatsRow = businessSeatsOpt.get().getRowCount();
            var businessSeatsPerRow = businessSeatsOpt.get().getSeatsPerRow();
            var businessSeats = businessSeatsRow * businessSeatsPerRow;
            var targetBusinessSeats = 0;
            for (Seat seat : seats) {
                if (seat.getFareCondition() == FareCondition.BUSINESS) {
                    targetBusinessSeats++;
                }
            }
            totalSeats = totalSeats + targetBusinessSeats;
            assertEquals(businessSeats, targetBusinessSeats);
        }

        var economySeatsOpt = seatConfiguration.getEconomySeatInfo();
        if (economySeatsOpt.isPresent()) {
            var economySeatsRow = economySeatsOpt.get().getRowCount();
            var economySeatsPerRow = economySeatsOpt.get().getSeatsPerRow();
            var economySeats = economySeatsRow * economySeatsPerRow;
            var targetEconomySeats = 0;
            for (Seat seat : seats) {
                if (seat.getFareCondition() == FareCondition.ECONOMY) {
                    targetEconomySeats++;
                }
            }
            totalSeats = totalSeats + targetEconomySeats;
            assertEquals(economySeats, targetEconomySeats);
        }

        assertEquals(totalSeats, seats.size());

    }

    @Test
    void initSeats_SeatsListIsCreated_ThrowEx() {
        var seatConfiguration = SeatConfigurationStub.getSeatConfiguration();
        var aircraft = AircraftStub.getFullAircraft();
        var ex = assertThrows(CodedException.class, () -> aircraft.initSeats(seatConfiguration));
        assertEquals(AircraftError.AIRCRAFT_SEATS_IS_ALREADY_CREATED, ex.getCode());
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
        var seatConfiguration = SeatConfigurationStub.getSeatConfigurationIllegalPerRow();

        var aircraft = AircraftStub.getAircraftWithEmptySeats();

        var ex = assertThrows(CodedException.class, () -> aircraft.initSeats(seatConfiguration));
        assertEquals(AircraftError.ILLEGAL_SEATS_PER_ROW_VALUE, ex.getCode());
        assertFalse(ex.getMessage().isEmpty());
    }


}
