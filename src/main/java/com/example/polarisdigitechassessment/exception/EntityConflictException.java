package com.example.polarisdigitechassessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EntityConflictException extends RuntimeException {
        public EntityConflictException(String message) {
                super(message);
        }
}
