package com.example.polarisdigitechassessment.utils;

import com.example.polarisdigitechassessment.data.dto.ApiDataResponseDto;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class DataResponseUtils {

        public static ApiDataResponseDto successResponse(String message, Object data, int code) {
                return ApiDataResponseDto.builder()
                        .data(data)
                        .timestamp(LocalDateTime.now())
                        .message(message)
                        .status(true)
                        .code(code)
                        .build();
        }
        public static ApiDataResponseDto successResponse(String message, Object data) {
                return ApiDataResponseDto.builder()
                        .data(data)
                        .timestamp(LocalDateTime.now())
                        .message(message)
                        .status(true)
                        .code(HttpStatus.OK.value())
                        .build();
        }

        public static ApiDataResponseDto successResponse(String message) {
                return ApiDataResponseDto.builder()
                        .timestamp(LocalDateTime.now())
                        .message(message)
                        .status(true)
                        .code(HttpStatus.OK.value())
                        .build();
        }

        public static ApiDataResponseDto errorResponse(String message) {
                return ApiDataResponseDto.builder()
                        .timestamp(LocalDateTime.now())
                        .message(message)
                        .status(false)
                        .code(HttpStatus.BAD_REQUEST.value())
                        .build();
        }

        public static ApiDataResponseDto errorResponse(HttpStatus status, String message) {
                ApiDataResponseDto responseDto = errorResponse(message);
                responseDto.setCode(status.value());
                return responseDto;
        }
}
