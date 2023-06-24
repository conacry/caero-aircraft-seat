package org.conacry.caero.domain.usecase;

import org.conacry.caero.boundary.model.AircraftCreateInfo;
import org.conacry.caero.boundary.model.SeatConfiguration;
import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.boundary.usecase.CreateAircraftUseCase;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftError;
import org.conacry.caero.domain.entity.aircraft.Model;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.entity.SeatConfigurationStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAircraftUseCaseTest {

    @Mock
    private AircraftRepository aircraftRepo;

    private CreateAircraftUseCase createAircraftUseCase;

    @BeforeEach
    void setUp() {
        this.createAircraftUseCase = new CreateAircraftUseCaseImpl(aircraftRepo);
    }

    @Test
    void execute_InfoIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> createAircraftUseCase.execute(null));
        assertEquals(UseCaseError.AIRCRAFT_CREATE_INFO_REQUIRED, ex.getCode());
    }

    @Test
    void execute_AirCraftIsAlreadyExisted_ThrowEx() {
        var modelStr = "Model";
        var seatConfig = new SeatConfiguration();
        var info = new AircraftCreateInfo(modelStr, seatConfig);

        var model = Model.from(modelStr);
        when(aircraftRepo.existByModel(model)).thenReturn(Boolean.TRUE);

        var ex = assertThrows(CodedException.class, () -> createAircraftUseCase.execute(info));
        assertEquals(UseCaseError.AIRCRAFT_IS_ALREADY_EXISTED, ex.getCode());
    }

    @Test
    void execute_ModelInfoIsIllegal_ThrowEx() {
        var modelStr = "$";
        var seatConfig = new SeatConfiguration();
        var info = new AircraftCreateInfo(modelStr, seatConfig);

        var ex = assertThrows(CodedException.class, () -> createAircraftUseCase.execute(info));
        assertEquals(AircraftError.ILLEGAL_MODEL_VALUE, ex.getCode());
    }

    @Test
    void execute_SeatConfigIsNull_ThrowEx() {
        var modelStr = "Model";
        var info = new AircraftCreateInfo(modelStr, null);

        var ex = assertThrows(CodedException.class, () -> createAircraftUseCase.execute(info));
        assertEquals(AircraftError.SEAT_CONFIGURATION_IS_REQUIRED, ex.getCode());
    }

    @Test
    void execute_SaveAircraftThrewEx_ThrowEx() {
        doThrow(RuntimeException.class).when(aircraftRepo).save(any(Aircraft.class));

        var modelStr = "Model";
        var seatConfig = SeatConfigurationStub.getSeatConfiguration();
        var info = new AircraftCreateInfo(modelStr, seatConfig);

        assertThrows(RuntimeException.class, () -> createAircraftUseCase.execute(info));
    }

    @Test
    void execute_NoExceptionThrown_ReturnAircraft() {
        var modelStr = "Model";
        var seatConfig = SeatConfigurationStub.getSeatConfiguration();
        var info = new AircraftCreateInfo(modelStr, seatConfig);

        var aircraft = createAircraftUseCase.execute(info);
        assertNotNull(aircraft);
        verify(aircraftRepo, times(1)).existByModel(any(Model.class));
        verify(aircraftRepo, times(1)).save(aircraft);
    }
}