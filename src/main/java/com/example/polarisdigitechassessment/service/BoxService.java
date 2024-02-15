package com.example.polarisdigitechassessment.service;

import com.example.polarisdigitechassessment.data.dto.ApiDataResponseDto;
import com.example.polarisdigitechassessment.data.dto.ItemDto;
import com.example.polarisdigitechassessment.data.dto.RegisterBoxDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoxService {

	ApiDataResponseDto registerBox(RegisterBoxDto registerBoxDto);

    ApiDataResponseDto loadBox(Long boxId, List<ItemDto> itemDtos);

	ApiDataResponseDto getBoxes(Pageable pageable);

	ApiDataResponseDto getBoxItems(Long boxId);

	ApiDataResponseDto getAvailableBoxes();

	ApiDataResponseDto getBatteryCapacity(Long boxId);

}