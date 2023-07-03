package org.conacry.caero.domain.usecase;

import org.conacry.caero.boundary.model.AircraftCreateInfo;
import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.boundary.usecase.CreateAircraftUseCase;
import org.conacry.caero.boundary.usecase.DeleteAircraftUseCase;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftError;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.entity.AircraftStub;
import org.conacry.caero.testdouble.entity.SeatConfigurationStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteAircraftUseCaseImplTest {

    @Mock
    private AircraftRepository aircraftRepo;

    private DeleteAircraftUseCase deleteAircraftUseCase;

    @BeforeEach
    void setUp() {
        this.deleteAircraftUseCase = new DeleteAircraftUseCaseImpl(aircraftRepo);
    }

    // id is null
    @Test
    void execute_AircraftIDStrIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> deleteAircraftUseCase.deleteAircraftByID(null));
        assertEquals(AircraftError.AIRCRAFT_ID_VALUE_IS_REQUIRED, ex.getCode());
    }

    // find by id throw ex
    @Test
    void execute_AircraftFoundByID_ThrowEx() {
        var aircraftID = AircraftID.newID().toString();
        when(aircraftRepo.findByID(any(AircraftID.class))).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> deleteAircraftUseCase.deleteAircraftByID(aircraftID));
    }

    // find by id return empty opt
    @Test
    void execute_AircraftIsNotFoundByID_ThrowEx() {
        var aircraftID = AircraftID.newID().toString();
        when(aircraftRepo.findByID(any(AircraftID.class))).thenReturn(Optional.empty());

        var ex = assertThrows(CodedException.class, () -> deleteAircraftUseCase.deleteAircraftByID(aircraftID));
        assertEquals(UseCaseError.AIRCRAFT_IS_NOT_FOUND, ex.getCode());
    }


    // update throw ex
    @Test
    void execute_UpdateAircraftThrewEx_ThrowEx() {
        var aircraft = AircraftStub.getFullActiveAircraft();
        when(aircraftRepo.findByID(any(AircraftID.class))).thenReturn(Optional.of(aircraft));

        doThrow(RuntimeException.class).when(aircraftRepo).update(any(Aircraft.class));
        var aircraftIDStr = AircraftID.newID().toString();

        assertThrows(RuntimeException.class, () -> deleteAircraftUseCase.deleteAircraftByID(aircraftIDStr));
    }
}