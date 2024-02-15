package com.example.polarisdigitechassessment.data.dto;

import com.example.polarisdigitechassessment.data.enums.BoxState;
import com.example.polarisdigitechassessment.data.model.Box;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoxDto {

    private Long id;

    private String txref;

    private BoxState boxState;

    private double batteryCapacity;

    private double weightLimit;

    private double freeWeight;

    private double occupiedWeight;

    public BoxDto(Box box) {
        this.id = box.getId();
        this.txref = box.getTxref();
        this.boxState = box.getBoxState();
        this.batteryCapacity = box.getBatteryCapacity();
        this.weightLimit = box.getWeightLimit();
        this.freeWeight = box.getFreeWeight();
        this.occupiedWeight = box.getOccupiedWeight();
    }
}


