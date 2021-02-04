package com.test.eshopweb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScheduledService {

//    @Scheduled(fixedDelay = 10_000)
    public void run() {
        log.info("scheduled operation running ...");
    }

}
