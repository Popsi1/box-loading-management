package com.example.polarisdigitechassessment.data.dto;

import com.example.polarisdigitechassessment.data.model.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {

    @NotBlank(message = "The name is required.")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "only letters, numbers, underscore and hyphen allowed")
    private String name;

    @NotNull(message = "The weight is required.")
    private double weight;

    @NotBlank(message = "The code is required.")
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "only upper case letters, underscore and numbers allowed")
    private String code;

    public ItemDto(Item item) {
        this.name = item.getName();
        this.weight = item.getWeight();
        this.code = item.getCode();
    }

}
