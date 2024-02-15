package com.example.polarisdigitechassessment.service.impl;

import com.example.polarisdigitechassessment.data.dto.ItemDto;
import com.example.polarisdigitechassessment.data.dto.LoadBoxValidationDto;
import com.example.polarisdigitechassessment.service.ItemService;
import com.example.polarisdigitechassessment.service.ValidationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ValidationServiceImpl implements ValidationService {

    public List<LoadBoxValidationDto> validateRequest(List<ItemDto> itemDtos) {
        List<LoadBoxValidationDto> loadBoxValidationDtos = new ArrayList<>();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        for (ItemDto itemDto : itemDtos){

            Set<ConstraintViolation<ItemDto>> violations = validator.validate(itemDto);

            LoadBoxValidationDto loadBoxValidationDto = new LoadBoxValidationDto();

            Map<String, String> fieldErrors = new HashMap<>();

            for (ConstraintViolation<ItemDto> violation : violations) {
                String fieldName = String.valueOf(violation.getPropertyPath());
                String errorMessage = violation.getMessage();
                fieldErrors.put(fieldName, errorMessage);
            }

            if (!fieldErrors.isEmpty()){
                loadBoxValidationDto.setItemName(itemDto.getName());
                loadBoxValidationDto.setFieldErrors(fieldErrors);
                loadBoxValidationDtos.add(loadBoxValidationDto);
            }
        }

        return loadBoxValidationDtos;
    }
}
