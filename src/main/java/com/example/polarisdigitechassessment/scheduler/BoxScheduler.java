package com.example.polarisdigitechassessment.scheduler;

import com.example.polarisdigitechassessment.constant.AppConstant;
import com.example.polarisdigitechassessment.data.enums.BoxState;
import com.example.polarisdigitechassessment.data.model.Box;
import com.example.polarisdigitechassessment.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.polarisdigitechassessment.constant.AppConstant.BATTERY_LIMIT;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoxScheduler {

    private final BoxRepository boxRepository;

    // This method will be executed every 10 seconds
    @Async
    @Scheduled(fixedRate = 10000)
    public void checkIdleBoxesBatteryCapacity() {
        try {
            log.info("Box capacity check at {}", LocalDateTime.now());
            Optional<List<Box>> boxes = boxRepository.findByBoxState(BoxState.IDLE);
            if (boxes.isPresent()){
                for (Box box : boxes.get()) {
                    if(box.getBoxState().equals(BoxState.IDLE) && box.getBatteryCapacity() >= BATTERY_LIMIT) {
                        box.setBoxState(BoxState.LOADING);
                        boxRepository.save(box);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error during task execution", e);
        }
    }
}
