package org.conacry.caero.adapter.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AircraftControllerTest {

    @Mock
    private DeleteAircraftUseCase deleteAircraftUseCase;

    private AircraftController aircraftController;

    @BeforeEach
    void setUp() {
        this.aircraftController = new AircraftController(deleteAircraftUseCase);
    }

    @Test
    void execute_DeleteAircraftThrewEx_ThrowEx() {
        doThrow(RuntimeException.class).when(deleteAircraftUseCase).deleteAircraftByID(anyString());

        var request = new DeleteAircraftRequest();
        request.setAircraftID(AircraftID.newID().toString());
        assertThrows(RuntimeException.class, () -> aircraftController.deleteAircraft(request));
    }

    @Test
    void execute_DeleteAircraftIsAlreadyExisted_ThrowEx() {
        doNothing().when(deleteAircraftUseCase).deleteAircraftByID(anyString());

        var request = new DeleteAircraftRequest();
        request.setAircraftID(AircraftID.newID().toString());
        var response = aircraftController.deleteAircraft(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

    }
}

