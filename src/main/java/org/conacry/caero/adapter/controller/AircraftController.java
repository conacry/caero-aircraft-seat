package org.conacry.caero.adapter.controller;

import org.conacry.caero.adapter.controller.request.CreateAircraftRequest;
import org.conacry.caero.adapter.controller.response.CreateAircraftResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class AircraftController {

    @PostMapping(path = "/create-aircraft")
    public CreateAircraftResponse createAircraft(CreateAircraftRequest request) {
        //преоброзовать reqest create info
        //вызвать юзкейс создание аиррафт
        //вернуть результат (AircraftId)

        var response = new CreateAircraftResponse();
        response.setAircraftID("Hello world");
        return response;

    }
}