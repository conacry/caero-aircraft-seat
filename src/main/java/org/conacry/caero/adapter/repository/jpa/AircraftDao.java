package org.conacry.caero.adapter.repository.jpa;

import org.conacry.caero.adapter.repository.model.AircraftDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface AircraftDao extends JpaRepository<AircraftDbModel, UUID> {
    AircraftDbModel findByIdAndStatus(UUID id, String status);
    List<AircraftDbModel> findAllByStatus(String aircraftStatus);
}
