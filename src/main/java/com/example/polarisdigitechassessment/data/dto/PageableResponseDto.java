package com.example.polarisdigitechassessment.data.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PageableResponseDto {

    private Integer pageNumber;

    private Integer size;

    private Object content;

    private Integer totalPages;

    private Integer totalElements;
}
