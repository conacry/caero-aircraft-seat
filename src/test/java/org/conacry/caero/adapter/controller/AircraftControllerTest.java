package org.conacry.caero.adapter.controller;

import org.conacry.caero.adapter.controller.convertor.RequestConvertor;
import org.conacry.caero.adapter.controller.request.CreateAircraftRequest;
import org.conacry.caero.adapter.controller.response.CreateAircraftResponse;
import org.conacry.caero.boundary.model.AircraftCreateInfo;
import org.conacry.caero.boundary.usecase.CreateAircraftUseCase;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.controller.RequestStub;
import org.conacry.caero.testdouble.entity.AircraftStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AircraftControllerTest {

    private AircraftController aircraftController;

    @Mock
    private CreateAircraftUseCase createAircraftUseCase;

    @BeforeEach
    void setUp() {
        aircraftController = new AircraftController(createAircraftUseCase);
    }

    @Test
    void createAircraft_RequestIsNull_ThrowEx() {
        var ex = assertThrows(CodedException.class, () -> aircraftController.createAircraft(null));
        assertEquals(ControllerError.REQUEST_IS_REQUIRED, ex.getCode());
    }

    @Test
    void createAircraft_UsecaseThrewEx_ThrowEx() {
        var request = RequestStub.getFullCreateAircraftRequest();

        when(createAircraftUseCase.execute(any(AircraftCreateInfo.class))).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> aircraftController.createAircraft(request));
    }

    @Test
    void createAircraft_NoExceptionsOccurred_ReturnResponse() {
        var aircraft = AircraftStub.getAircraftAllParameters();
        when(createAircraftUseCase.execute(any(AircraftCreateInfo.class))).thenReturn(aircraft);

        var request = RequestStub.getFullCreateAircraftRequest();
        var response = aircraftController.createAircraft(request);
        assertEquals(aircraft.getAircraftID().toString(), response.getAircraftID());
    }
}