package org.conacry.caero.adapter.controller;

import org.conacry.caero.adapter.controller.convertor.RequestConvertor;
import org.conacry.caero.adapter.controller.request.CreateAircraftRequest;
import org.conacry.caero.adapter.controller.response.CreateAircraftResponse;
import org.conacry.caero.boundary.usecase.CreateAircraftUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class AircraftController {

    private final CreateAircraftUseCase createAircraftUseCase;

    public AircraftController(CreateAircraftUseCase createAircraftUseCase) {
        this.createAircraftUseCase = createAircraftUseCase;
    }


    @PostMapping(path = "/create-aircraft")
    public CreateAircraftResponse createAircraft(@RequestBody CreateAircraftRequest request) {
        var info = RequestConvertor.createRequestToModel(request);

        var aircraft = createAircraftUseCase.execute(info);

        var response = new CreateAircraftResponse();
        response.setAircraftID(aircraft.getAircraftID().toString());
        return response;
    }
// request null
// mock usecase

//response возращает 2 одинаковых id
}