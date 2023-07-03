package org.conacry.caero.adapter.controller;

import org.conacry.caero.adapter.controller.convertor.RequestConvertor;
import org.conacry.caero.adapter.controller.request.CreateAircraftRequest;
import org.conacry.caero.adapter.controller.response.CreateAircraftResponse;
import org.conacry.caero.boundary.model.AircraftCreateInfo;
import org.conacry.caero.boundary.usecase.CreateAircraftUseCase;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.testdouble.controller.RequestStub;
import org.conacry.caero.testdouble.entity.AircraftStub;
import org.conacry.caero.adapter.controller.request.DeleteAircraftRequest;
import org.conacry.caero.boundary.model.AircraftCreateInfo;
import org.conacry.caero.boundary.model.SeatConfiguration;
import org.conacry.caero.boundary.repository.AircraftRepository;
import org.conacry.caero.boundary.usecase.DeleteAircraftUseCase;
import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftID;
import org.conacry.caero.domain.entity.aircraft.Model;
import org.conacry.caero.domain.primitive.exception.CodedException;
import org.conacry.caero.domain.usecase.UseCaseError;
import org.conacry.caero.testdouble.entity.SeatConfigurationStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AircraftControllerTest {

    @Mock
    private CreateAircraftUseCase createAircraftUseCase;

    @Mock
    private DeleteAircraftUseCase deleteAircraftUseCase;

    private AircraftController aircraftController;

    @BeforeEach
    void setUp() {
        this.aircraftController = new AircraftController(createAircraftUseCase, deleteAircraftUseCase);
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
        var aircraft = AircraftStub.getFullActiveAircraft();
        when(createAircraftUseCase.execute(any(AircraftCreateInfo.class))).thenReturn(aircraft);

        var request = RequestStub.getFullCreateAircraftRequest();
        var response = aircraftController.createAircraft(request);
        assertEquals(aircraft.getAircraftID().toString(), response.getAircraftID());
    }

    @Test
    void deleteAircraft_DeleteAircraftThrewEx_ThrowEx() {
        doThrow(RuntimeException.class).when(deleteAircraftUseCase).deleteAircraftByID(anyString());

        var request = new DeleteAircraftRequest();
        request.setAircraftID(AircraftID.newID().toString());
        assertThrows(RuntimeException.class, () -> aircraftController.deleteAircraft(request));
    }

    @Test
    void deleteAircraft_DeleteAircraftIsAlreadyExisted_ThrowEx() {
        doNothing().when(deleteAircraftUseCase).deleteAircraftByID(anyString());

        var request = new DeleteAircraftRequest();
        request.setAircraftID(AircraftID.newID().toString());
        var response = aircraftController.deleteAircraft(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
}