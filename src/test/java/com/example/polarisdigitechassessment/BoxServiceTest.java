package com.example.polarisdigitechassessment;

import com.example.polarisdigitechassessment.data.dto.ApiDataResponseDto;
import com.example.polarisdigitechassessment.data.dto.BoxDto;
import com.example.polarisdigitechassessment.data.dto.ItemDto;
import com.example.polarisdigitechassessment.data.dto.RegisterBoxDto;
import com.example.polarisdigitechassessment.data.enums.BoxState;
import com.example.polarisdigitechassessment.data.model.Box;
import com.example.polarisdigitechassessment.data.model.Item;
import com.example.polarisdigitechassessment.repository.BoxRepository;
import com.example.polarisdigitechassessment.repository.ItemRepository;
import com.example.polarisdigitechassessment.service.ItemService;
import com.example.polarisdigitechassessment.service.impl.BoxServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.polarisdigitechassessment.constant.AppConstant.INITIAL_BOX_WEIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BoxServiceTest {

    @Mock
    private BoxRepository boxRepository;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ItemService itemService;
    @InjectMocks
    private BoxServiceImpl boxService;

    @Test
    public void registerBox(){
        RegisterBoxDto boxDto = new RegisterBoxDto();
        boxDto.setTxref("11113");
        boxDto.setBatteryCapacity(30);
        boxDto.setWeightLimit(500);

        given(boxRepository.findByTxref(boxDto.getTxref()))
                .willReturn(Optional.empty());

        Box box = new Box();
        box.setTxref(boxDto.getTxref());
        box.setBoxState(BoxState.LOADING);
        box.setBatteryCapacity(boxDto.getBatteryCapacity());
        box.setWeightLimit(boxDto.getWeightLimit());
        box.setFreeWeight(boxDto.getWeightLimit());
        box.setOccupiedWeight(INITIAL_BOX_WEIGHT);

        given(boxRepository.save(any())).willReturn(box);

        ApiDataResponseDto apiDataResponseDto = boxService.registerBox(boxDto);

        assertThat(apiDataResponseDto.getData()).isNotNull();
    }

    @Test
    public void loadBox(){
        Box box = new Box();
        box.setTxref("11111");
        box.setBoxState(BoxState.LOADING);
        box.setBatteryCapacity(30);
        box.setWeightLimit(500);
        box.setFreeWeight(500);
        box.setOccupiedWeight(INITIAL_BOX_WEIGHT);

        ItemDto itemDto = new ItemDto();
        itemDto.setCode("11111");
        itemDto.setWeight(50);
        itemDto.setName("book");

        Item item = new Item(box, itemDto);

        List<ItemDto> itemDtos = List.of(itemDto);
        List<Item> items = List.of(item);

        given(boxRepository.findById(1L))
                .willReturn(Optional.of(box));

        given(boxRepository.save(any())).willReturn(box);

        ApiDataResponseDto apiDataResponseDto = boxService.loadBox(1L, itemDtos);

        assertThat(apiDataResponseDto.getMessage()).isEqualTo("Box successfully loaded");
    }

    @Test
    public void getBoxItems(){
        Box box = new Box();
        box.setId(1L);
        box.setTxref("11111");
        box.setBoxState(BoxState.LOADING);
        box.setBatteryCapacity(30);
        box.setWeightLimit(500);
        box.setFreeWeight(500);
        box.setOccupiedWeight(INITIAL_BOX_WEIGHT);

        given(boxRepository.findById(box.getId()))
                .willReturn(Optional.of(box));

        ItemDto itemDto = new ItemDto();
        itemDto.setCode("11111");
        itemDto.setWeight(50);
        itemDto.setName("book");

        Item item = new Item(box, itemDto);

        List<Item> items = List.of(item);

        given(itemRepository.findItemByBox_Id(box.getId())).willReturn(Optional.of(items));

        ApiDataResponseDto apiDataResponseDto = boxService.getBoxItems(box.getId());

        assertThat(apiDataResponseDto.getMessage()).isEqualTo("Retrieved box items");
    }

    @Test
    public void getAvailableBoxes(){
        Box box = new Box();
        box.setId(1L);
        box.setTxref("11111");
        box.setBoxState(BoxState.LOADING);
        box.setBatteryCapacity(30);
        box.setWeightLimit(500);
        box.setFreeWeight(500);
        box.setOccupiedWeight(INITIAL_BOX_WEIGHT);

        List<Box> boxes = List.of(box);

        given(boxRepository.findByBoxState(box.getBoxState()))
                .willReturn(Optional.of(boxes));

        ApiDataResponseDto apiDataResponseDto = boxService.getAvailableBoxes();

        assertThat(apiDataResponseDto.getMessage()).isEqualTo("Retrieved available boxes");
    }

    @Test
    public void getBatteryCapacity(){
        Box box = new Box();
        box.setId(1L);
        box.setTxref("11111");
        box.setBoxState(BoxState.LOADING);
        box.setBatteryCapacity(30);
        box.setWeightLimit(500);
        box.setFreeWeight(500);
        box.setOccupiedWeight(INITIAL_BOX_WEIGHT);

        List<Box> boxes = List.of(box);

        given(boxRepository.findById(box.getId()))
                .willReturn(Optional.of(box));

        ApiDataResponseDto apiDataResponseDto = boxService.getBatteryCapacity(box.getId());

        assertThat(apiDataResponseDto.getMessage()).isEqualTo("Retrieved box battery capacity");
    }
}
