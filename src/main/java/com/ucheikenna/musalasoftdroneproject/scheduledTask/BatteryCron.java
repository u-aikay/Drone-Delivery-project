package com.ucheikenna.musalasoftdroneproject.scheduledTask;

import com.ucheikenna.musalasoftdroneproject.services.serviceInterface.DroneServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@EnableAsync
@RequiredArgsConstructor
@Slf4j
@Component
public class BatteryCron {

    private final DroneServices droneServices;

    @Async
    @Scheduled(fixedRateString = "PT60S", initialDelay = 5000L)
    public void scheduleFixedRateTaskAsync()  {
        log.info("SCHEDULER FOR BATTERY STATUS IS RUNNING...");

        droneServices.scheduleFixedRateTaskAsync();
    }
}
