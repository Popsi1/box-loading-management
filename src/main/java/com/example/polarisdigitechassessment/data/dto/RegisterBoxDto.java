package com.example.polarisdigitechassessment.data.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterBoxDto {

    @NotBlank(message = "The txref is required.")
    @Size(min = 4, max = 20, message = "The Serial number must be between 4 and 20")
    private String txref;

    @NotNull(message = "The box battery capacity is required.")
    private double batteryCapacity;

    @NotNull(message = "The box weight is required.")
    @Max(value = 500, message = "The weight limit must be less than or equal to 500gr")
    private double weightLimit;

}
