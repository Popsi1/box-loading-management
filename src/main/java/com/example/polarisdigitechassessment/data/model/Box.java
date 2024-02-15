package com.example.polarisdigitechassessment.data.model;

import com.example.polarisdigitechassessment.data.enums.BoxState;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "box")
public class Box extends BaseClass {

    @Column(name = "serial_number", unique = true, length = 20, nullable = false)
    private String txref;

    @Column(name = "box_state", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private BoxState boxState;

    @Column(name = "battery_capacity", nullable = false)
    private double batteryCapacity;

    @Column(name = "weight_limit", length = 500, nullable = false)
    private double weightLimit;

    @Column(name = "free_weight", length = 500, nullable = false)
    private double freeWeight;

    @Column(name = "occupied_weight", length = 500, nullable = false)
    private double occupiedWeight;
}
