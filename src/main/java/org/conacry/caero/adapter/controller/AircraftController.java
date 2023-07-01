package org.conacry.caero.adapter.controller;

import org.conacry.caero.adapter.controller.convertor.ResponseConvertor;
import org.conacry.caero.adapter.controller.convertor.RequestConvertor;
import org.conacry.caero.adapter.controller.request.CreateAircraftRequest;
import org.conacry.caero.adapter.controller.request.GetByIDRequest;
import org.conacry.caero.adapter.controller.request.DeleteAircraftRequest;
import org.conacry.caero.adapter.controller.response.CreateAircraftResponse;
import org.conacry.caero.boundary.usecase.CreateAircraftUseCase;
import org.conacry.caero.boundary.usecase.DeleteAircraftUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.conacry.caero.adapter.controller.response.AircraftResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.conacry.caero.boundary.usecase.ReadAircraftDataUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class AircraftController {

    private final CreateAircraftUseCase createAircraftUseCase;
    private final DeleteAircraftUseCase deleteAircraftUseCase;
    private final ReadAircraftDataUseCase readAircraftDataUseCase;

    public AircraftController(
            CreateAircraftUseCase createAircraftUseCase,
            DeleteAircraftUseCase deleteAircraftUseCase,
            ReadAircraftDataUseCase readAircraftDataUseCase) {
        this.createAircraftUseCase = createAircraftUseCase;
        this.deleteAircraftUseCase = deleteAircraftUseCase;
        this.readAircraftDataUseCase = readAircraftDataUseCase;
    }

    @PostMapping(path = "/create-aircraft")
    public CreateAircraftResponse createAircraft(@RequestBody CreateAircraftRequest request) {
        var info = RequestConvertor.createRequestToModel(request);

        var aircraft = createAircraftUseCase.execute(info);

        var response = new CreateAircraftResponse();
        response.setAircraftID(aircraft.getAircraftID().toString());
        return response;
    }

    @PostMapping("/delete-by-id")
    public ResponseEntity<Object> deleteAircraft(@RequestBody DeleteAircraftRequest request) {
        deleteAircraftUseCase.deleteAircraftByID(request.getAircraftID());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping(path = "/get-by-id")
    public AircraftResponse getByID(@RequestBody GetByIDRequest request) {
        var aircraft = readAircraftDataUseCase.findByID(request.getAircraftID());
        return ResponseConvertor.aircraftToResponse(aircraft);
    }
}