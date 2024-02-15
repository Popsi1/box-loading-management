package com.example.polarisdigitechassessment.service.impl;

import com.example.polarisdigitechassessment.constant.AppConstant;
import com.example.polarisdigitechassessment.data.dto.*;
import com.example.polarisdigitechassessment.data.enums.BoxState;
import com.example.polarisdigitechassessment.data.model.Box;
import com.example.polarisdigitechassessment.data.model.Item;
import com.example.polarisdigitechassessment.exception.EntityConflictException;
import com.example.polarisdigitechassessment.exception.ResourceAlreadyExistException;
import com.example.polarisdigitechassessment.exception.ResourceNotFoundException;
import com.example.polarisdigitechassessment.repository.BoxRepository;
import com.example.polarisdigitechassessment.repository.ItemRepository;
import com.example.polarisdigitechassessment.service.BoxService;
import com.example.polarisdigitechassessment.service.ItemService;
import com.example.polarisdigitechassessment.utils.CheckWeightLimit;
import com.example.polarisdigitechassessment.utils.DataResponseUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.polarisdigitechassessment.constant.AppConstant.BATTERY_LIMIT;
import static com.example.polarisdigitechassessment.constant.AppConstant.INITIAL_BOX_WEIGHT;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoxServiceImpl implements BoxService {

	private final BoxRepository boxRepository;
	private final ItemRepository itemRepository;
	private final ItemService itemService;

	@Override
	public ApiDataResponseDto registerBox(RegisterBoxDto registerBoxDto) {

		Optional<Box> boxOptional = boxRepository.findByTxref(registerBoxDto.getTxref());
		if (boxOptional.isPresent()) {
			throw new ResourceAlreadyExistException("txref already exist");
		}

		Box box = new Box();
		box.setTxref(registerBoxDto.getTxref());
		box.setBatteryCapacity(registerBoxDto.getBatteryCapacity());
		box.setWeightLimit(registerBoxDto.getWeightLimit());
		box.setFreeWeight(registerBoxDto.getWeightLimit());
		box.setOccupiedWeight(INITIAL_BOX_WEIGHT);
		if (registerBoxDto.getBatteryCapacity() < BATTERY_LIMIT) {
			box.setBoxState(BoxState.IDLE);
		} else {
			box.setBoxState(BoxState.LOADING);
		}

		return DataResponseUtils.successResponse("Box successfully registered", new BoxDto(boxRepository.save(box)), HttpStatus.CREATED.value());
	}

	@Override
	public ApiDataResponseDto getBoxes(Pageable pageable) {

		Page<Box> page = boxRepository.allBoxes(pageable);
		PageableResponseDto data = PageableResponseDto.builder()
				.totalPages(page.getTotalPages())
				.totalElements((int) page.getTotalElements())
				.pageNumber(page.getNumber())
				.size(page.getSize())
				.content(page.getContent().stream().map(BoxDto::new).toList())
				.build();
		return DataResponseUtils.successResponse("Retrieved all boxes", data);
	}

	@Transactional
	public ApiDataResponseDto loadBox(Long boxId, List<ItemDto> itemDtos) {

		Box box = boxRepository.findById(boxId).orElseThrow(() -> new ResourceNotFoundException("Box not found"));

		if (box.getBoxState().equals(BoxState.IDLE)) {
			throw new EntityConflictException("Box can't be loaded, due to low battery");
		}

		if (!box.getBoxState().equals(BoxState.LOADING)) {
			throw new EntityConflictException("Box is already loaded");
		}

		double itemsTotalWeight = CheckWeightLimit.checkWeightLimit(box, itemDtos);

		List<Item> items = itemService.registeritems(box,itemDtos);

		box.setOccupiedWeight(itemsTotalWeight);
		box.setFreeWeight(box.getWeightLimit() - itemsTotalWeight);
		box.setBoxState(BoxState.LOADED);

		boxRepository.save(box);

		return DataResponseUtils.successResponse("Box successfully loaded");
	}

	@Override
	public ApiDataResponseDto getBoxItems(Long boxId) {
		Box box = boxRepository.findById(boxId)
				.orElseThrow(() -> new ResourceNotFoundException("Box not found"));

		List<Item> items = itemRepository.findItemByBox_Id(boxId)
				.orElseThrow(() -> new ResourceNotFoundException("Box do not have items"));

		List<ItemDto> itemDtos = items.stream().map(ItemDto::new).toList();

		return DataResponseUtils.successResponse("Retrieved box items", itemDtos);
	}

	@Override
	public ApiDataResponseDto getAvailableBoxes() {
		List<Box> boxes = boxRepository.findByBoxState(BoxState.LOADING)
				.orElseThrow(() -> new ResourceNotFoundException("Box not found"));

		List<BoxDto> boxDtos = boxes.stream().map(BoxDto::new).toList();

		return DataResponseUtils.successResponse("Retrieved available boxes", boxDtos);
	}

	@Override
	public ApiDataResponseDto getBatteryCapacity(Long boxId) {
		Box box = boxRepository.findById(boxId)
				.orElseThrow(() -> new ResourceNotFoundException("Box not found"));
		String boxBatteryLife = box.getBatteryCapacity() + "%";

		return DataResponseUtils.successResponse("Retrieved box battery capacity", boxBatteryLife);
	}
}