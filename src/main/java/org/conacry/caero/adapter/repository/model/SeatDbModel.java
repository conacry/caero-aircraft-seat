package org.conacry.caero.adapter.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "seat")
public class SeatDbModel {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "seat_number")
    private String number;

    @Column(name = "fare_condition")
    private String fareCondition;

    @Column(name = "aircraft_id")
    private UUID aircraftID;
}