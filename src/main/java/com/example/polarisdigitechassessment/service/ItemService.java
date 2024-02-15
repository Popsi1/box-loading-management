package com.example.polarisdigitechassessment.service;

import com.example.polarisdigitechassessment.data.dto.ItemDto;
import com.example.polarisdigitechassessment.data.model.Box;
import com.example.polarisdigitechassessment.data.model.Item;

import java.util.List;

public interface ItemService {

	List<Item> registeritems(Box box, List<ItemDto> itemDto);
}