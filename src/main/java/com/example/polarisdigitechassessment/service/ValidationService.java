package com.example.polarisdigitechassessment.service;

import com.example.polarisdigitechassessment.data.dto.ItemDto;
import com.example.polarisdigitechassessment.data.dto.LoadBoxValidationDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

import java.util.*;

public interface ValidationService {

    List<LoadBoxValidationDto> validateRequest(List<ItemDto> itemDtos);
}
