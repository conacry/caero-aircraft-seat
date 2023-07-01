package org.conacry.caero.domain.usecase;

import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.boundary.usecase.ReadAircraftDataUseCase;
import org.conacry.caero.domain.entity.aircraft.AircraftError;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.entity.AircraftStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadAircraftDataUseCaseImplTest {

    @Mock
    private AircraftRepository aircraftRepository;

    private ReadAircraftDataUseCase readAircraftDataUseCase;

    @BeforeEach
    void setUp() {
        readAircraftDataUseCase = new ReadAircraftDataUseCaseImpl(aircraftRepository);
    }

    @Test
    void execute_AircraftIDStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> readAircraftDataUseCase.findByID(null));
        assertEquals(AircraftError.AIRCRAFT_ID_VALUE_IS_REQUIRED, ex.getCode());
    }

    @Test
    void execute_AircraftIsNotFoundByID_ThrowEx() {
        var aircraftID = AircraftID.newID().toString();
        when(aircraftRepository.findByID(any(AircraftID.class))).thenReturn(Optional.empty());

        var ex = assertThrows(CodedException.class, () -> readAircraftDataUseCase.findByID(aircraftID));
        assertEquals(UseCaseError.AIRCRAFT_IS_NOT_FOUND, ex.getCode());
    }

    @Test
    void execute_AircraftExists_ReturnAircraft() {
        var aircraft = AircraftStub.getFullAircraft();
        var aircraftID = AircraftID.newID().toString();
        when(aircraftRepository.findByID(any(AircraftID.class))).thenReturn(Optional.of(aircraft));

        var actualAircraft = readAircraftDataUseCase.findByID(aircraftID);
        assertNotNull(actualAircraft);
        assertEquals(aircraft, actualAircraft);
    }

    @Test
    void execute_AircraftRepoThrewEx_ReturnAircraftList() {
        when(aircraftRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> readAircraftDataUseCase.findAll());
    }

    @Test
    void execute_AircraftListExists_ReturnAircraftList() {
        var aircraftList = AircraftStub.getListFullAircraft(5);
        when(aircraftRepository.findAll()).thenReturn(aircraftList);
        var actualAircraftList = readAircraftDataUseCase.findAll();
        assertEquals(actualAircraftList, aircraftList);
    }

    @Test
    void execute_AircraftListIsEmpty_ReturnNull() {
        var aircraftList = AircraftStub.getListFullAircraft(0);
        when(aircraftRepository.findAll()).thenReturn(aircraftList);
        var actualAircraftList = readAircraftDataUseCase.findAll();
        assertNotNull(actualAircraftList);
        assertTrue(actualAircraftList.isEmpty());
    }
}