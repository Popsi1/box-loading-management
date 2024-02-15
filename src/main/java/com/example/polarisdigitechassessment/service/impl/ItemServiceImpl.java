package com.example.polarisdigitechassessment.service.impl;

import com.example.polarisdigitechassessment.data.dto.ItemDto;
import com.example.polarisdigitechassessment.data.model.Box;
import com.example.polarisdigitechassessment.data.model.Item;
import com.example.polarisdigitechassessment.repository.BoxRepository;
import com.example.polarisdigitechassessment.repository.ItemRepository;
import com.example.polarisdigitechassessment.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;
	private final BoxRepository boxRepository;

	@Override
	public List<Item> registeritems(Box box, List<ItemDto> itemDtos) {
		List<Item> items = itemDtos.stream().map(itemDto -> new Item(box, itemDto)).toList();
		return itemRepository.saveAll(items);
	}
}