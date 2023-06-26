package org.conacry.caero.adapter.repository;

import org.conacry.caero.adapter.repository.model.AircraftDbModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AircraftDao extends JpaRepository<AircraftDbModel, UUID> {
}
