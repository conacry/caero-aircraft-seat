package org.conacry.caero.adapter.controller;

import org.conacry.caero.adapter.controller.request.CreateAircraftRequest;
import org.conacry.caero.adapter.controller.request.DeleteAircraftRequest;
import org.conacry.caero.adapter.controller.response.CreateAircraftResponse;
import org.conacry.caero.boundary.usecase.DeleteAircraftUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class AircraftController {

    private final DeleteAircraftUseCase deleteAircraftUseCase;

    public AircraftController(DeleteAircraftUseCase deleteAircraftUseCase) {
        this.deleteAircraftUseCase = deleteAircraftUseCase;
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

    // /api/deleteAircraft
    @PostMapping("/delete-by-id")
    public ResponseEntity<Object> deleteAircraft(@RequestBody DeleteAircraftRequest request) {
        //написать класс ДелитАйкрафт Айркрафт айди над классом аннотация дата4
        //вызвать Юскэйс удаление передав ему Айди
        deleteAircraftUseCase.deleteAircraftByID(request.getAircraftID());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}