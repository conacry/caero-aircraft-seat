package org.conacry.caero.adapter.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "aircraft")
public class AircraftDbModel {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "model",nullable = false)
    private String model;

    @OneToMany
    @JoinColumn(name = "aircraft_id", referencedColumnName = "id")
    private List<SeatDbModel> seat;

}
