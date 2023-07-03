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

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(
            name = "aircraft_id",
            referencedColumnName = "id",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private List<SeatDbModel> seat;
}
