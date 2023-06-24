package org.conacry.caero.boundary.repository;

import org.conacry.caero.domain.entity.aircraft.Aircraft;
import org.conacry.caero.domain.entity.aircraft.AircraftID;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<AircraftDbModel, UUID>{
    //void save(AircraftDbModel aircraft);
    Optional<AircraftDbModel> findByID(AircraftID aircraftID);
    List<AircraftDbModel> findAll();
}
