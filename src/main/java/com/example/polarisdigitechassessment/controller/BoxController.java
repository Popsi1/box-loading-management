package com.example.polarisdigitechassessment.controller;

import com.example.polarisdigitechassessment.data.dto.ApiDataResponseDto;
import com.example.polarisdigitechassessment.data.dto.ItemDto;
import com.example.polarisdigitechassessment.data.dto.RegisterBoxDto;
import com.example.polarisdigitechassessment.service.BoxService;
import com.example.polarisdigitechassessment.service.ValidationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boxes")
public class BoxController {

    private final BoxService boxService;
    private final ValidationService validationService;


    @PostMapping
    public ResponseEntity<ApiDataResponseDto> registerBox(@Valid @RequestBody RegisterBoxDto RegisterBoxDto) {
        return ResponseEntity.ok(boxService.registerBox(RegisterBoxDto));
    }

    @GetMapping
    public ResponseEntity<ApiDataResponseDto> getBoxes(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(boxService.getBoxes(pageable));
    }

    @PutMapping("/{boxId}/load")
    public CompletableFuture<ResponseEntity<?>> loadBox(@PathVariable Long boxId, @RequestBody List<ItemDto> itemDtos) {
        return CompletableFuture.supplyAsync(() -> validationService.validateRequest(itemDtos))
                .thenApply(fieldErrors -> {
                    if (fieldErrors.isEmpty()) {
                        return ResponseEntity.ok(boxService.loadBox(boxId, itemDtos));
                    } else {
                        return ResponseEntity.ok(fieldErrors);
                    }
                });

    }

    @GetMapping("/{boxId}/items")
    public ResponseEntity<ApiDataResponseDto> getBoxItems(@PathVariable Long boxId) {
        return ResponseEntity.ok(boxService.getBoxItems(boxId));
    }

    @GetMapping("/available")
    public ResponseEntity<ApiDataResponseDto> getAvailableBoxes() {
        return ResponseEntity.ok(boxService.getAvailableBoxes());
    }

    @GetMapping("/{boxId}/battery-capacity")
    public ResponseEntity<ApiDataResponseDto> getBatteryCapacity(@PathVariable Long boxId) {
        ApiDataResponseDto apiDataResponseDto = boxService.getBatteryCapacity(boxId);
        return ResponseEntity.ok(apiDataResponseDto);
    }
}
