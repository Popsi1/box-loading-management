package com.example.polarisdigitechassessment.exception;

import com.example.polarisdigitechassessment.utils.DataResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class GlobalResponseExceptionHandler {

        private static final Logger log = LoggerFactory.getLogger(GlobalResponseExceptionHandler.class);

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> notValid(MethodArgumentNotValidException ex) {

                BindingResult bindingResult = ex.getBindingResult();

                Map<String, String> fieldErrors = new HashMap<>();
                for (FieldError fieldError : bindingResult.getFieldErrors()) {
                        String fieldName = fieldError.getField();
                        String errorMessage = fieldError.getDefaultMessage();
                        fieldErrors.put(fieldName, errorMessage);
                }

                return ResponseEntity.badRequest().body(fieldErrors);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
                return ResponseEntity
                        .of(Optional.of(DataResponseUtils.errorResponse(e.getMessage())));
        }

        @ExceptionHandler(ResourceAlreadyExistException.class)
        public ResponseEntity<Object> handleResourceAlreadyExistException(ResourceAlreadyExistException e) {
                return ResponseEntity
                        .of(Optional.of(DataResponseUtils.errorResponse(e.getMessage())));
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Object> handleException(Exception e) {
                return new ResponseEntity<>(DataResponseUtils.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(EntityConflictException.class)
        public ResponseEntity<Object> handleEntityConflictException(EntityConflictException e) {
                return ResponseEntity.badRequest().body(DataResponseUtils.errorResponse(e.getMessage()));
        }

        @ExceptionHandler(MaxWeightExceededException.class)
        public ResponseEntity<Object> handleMaxWeightExceededException(MaxWeightExceededException e) {
                return ResponseEntity.badRequest().body(DataResponseUtils.errorResponse(e.getMessage()));
        }

}
