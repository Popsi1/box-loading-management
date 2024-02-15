package com.example.polarisdigitechassessment.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoadBoxValidationDto {
    private String itemName;
    private Map<String, String> fieldErrors = new HashMap<>();
}
