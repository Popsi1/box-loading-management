package com.example.polarisdigitechassessment.utils;

import com.example.polarisdigitechassessment.data.dto.ItemDto;
import com.example.polarisdigitechassessment.data.model.Box;
import com.example.polarisdigitechassessment.data.model.Item;
import com.example.polarisdigitechassessment.exception.MaxWeightExceededException;

import java.util.List;

public class CheckWeightLimit {

    public static double checkWeightLimit(Box box, List<ItemDto> itemDtos) {
        double itemsTotalWeight = itemDtos.stream().mapToDouble(ItemDto::getWeight).sum();
        if (itemsTotalWeight > box.getWeightLimit()) {
            throw new MaxWeightExceededException("Box overloaded");
        }
        return itemsTotalWeight;
    }
}
