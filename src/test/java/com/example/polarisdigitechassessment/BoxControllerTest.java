package com.example.polarisdigitechassessment;

import com.example.polarisdigitechassessment.data.dto.ApiDataResponseDto;
import com.example.polarisdigitechassessment.data.dto.BoxDto;
import com.example.polarisdigitechassessment.data.dto.ItemDto;
import com.example.polarisdigitechassessment.data.dto.RegisterBoxDto;
import com.example.polarisdigitechassessment.data.model.Item;
import com.example.polarisdigitechassessment.service.BoxService;
import com.example.polarisdigitechassessment.utils.DataResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BoxControllerTest {

    final String basePath = "/api/boxes";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BoxService boxService;

    @Test
    public void registerBox() throws Exception{
        RegisterBoxDto boxDto = new RegisterBoxDto();
        boxDto.setTxref("11111");
        boxDto.setBatteryCapacity(30);
        boxDto.setWeightLimit(500);

        ResultActions response = mockMvc.perform(post(basePath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boxDto)));

        response.andDo(print()).
                andExpect(status().isOk());

    }

    @Test
    public void loadBox() throws Exception{
        List<ItemDto> items = new ArrayList<>();
        ItemDto itemDto = new ItemDto();
        itemDto.setCode("abcd");
        itemDto.setName("abcd");
        itemDto.setWeight(40);
        items.add(itemDto);

        ResultActions response = mockMvc.perform(put(basePath + "/{boxId}/load", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(items)));

        response.andDo(print()).
                andExpect(status().isOk());

    }

    @Test
    public void getAllDrones() throws Exception{

        ResultActions response = mockMvc.perform(get(basePath)
                .param("page", "0").param("pageSize", "10"));

        response.andExpect(status().isOk());
    }

    @Test
    public void getDroneMedications() throws Exception{

        ResultActions response = mockMvc.perform(get(basePath+ "/{boxId}/items", 1));

        response.andExpect(status().isOk());
    }

    @Test
    public void getAvailableDrones() throws Exception{

        ResultActions response = mockMvc.perform(get(basePath+ "/available"));

        response.andExpect(status().isOk());
    }

    @Test
    public void getBatteryCapacity() throws Exception{

        ResultActions response = mockMvc.perform(get(basePath+ "/{boxId}/battery-capacity", 1));
        
        response.andExpect(status().isOk());

    }
}
