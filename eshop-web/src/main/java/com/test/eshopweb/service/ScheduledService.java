package com.test.eshopweb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScheduledService {

    @Autowired
    private ItemServiceImpl itemService;

//    @Scheduled(fixedDelay = 10_000)
    public void run() {
        // jeste lepe s EhCache a TTL / TTI
//        log.info("scheduled operation running ...");
        itemService.clearCache();
    }

}
