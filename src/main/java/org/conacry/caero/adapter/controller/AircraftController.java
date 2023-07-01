package org.conacry.caero.adapter.controller;

import org.conacry.caero.adapter.controller.convertor.ResponseConvertor;
import org.conacry.caero.adapter.controller.request.CreateAircraftRequest;
import org.conacry.caero.adapter.controller.request.GetByIDRequest;
import org.conacry.caero.adapter.controller.response.CreateAircraftResponse;
import org.conacry.caero.adapter.controller.response.AircraftResponse;
import org.conacry.caero.boundary.usecase.ReadAircraftDataUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class AircraftController {

    private final ReadAircraftDataUseCase readAircraftDataUseCase;

    public AircraftController(ReadAircraftDataUseCase readAircraftDataUseCase) {
        this.readAircraftDataUseCase = readAircraftDataUseCase;
    }

    @PostMapping(path = "/create-aircraft")
    public CreateAircraftResponse createAircraft(CreateAircraftRequest request) {
        //преоброзовать request create info
        //вызвать usecase создание aircraft
        //вернуть результат (AircraftId)

        var response = new CreateAircraftResponse();
        response.setAircraftID("Hello world");
        return response;

    }

    @GetMapping(path = "/get-by-id")
    public AircraftResponse getByID(@RequestBody GetByIDRequest request) {
        var aircraft = readAircraftDataUseCase.findByID(request.getAircraftID());
        return ResponseConvertor.aircraftToResponse(aircraft);
    }
}