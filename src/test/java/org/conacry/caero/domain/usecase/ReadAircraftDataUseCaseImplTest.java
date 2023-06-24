package org.conacry.caero.domain.usecase;

import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.boundary.usecase.ReadAircraftDataUseCase;
import org.conacry.caero.domain.entity.aircraft.AircraftError;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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

        when(aircraftRepository.findByID(any(AircraftID.class))).thenReturn(Optional.empty());
    }
}