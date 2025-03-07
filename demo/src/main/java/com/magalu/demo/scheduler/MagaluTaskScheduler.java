package com.magalu.demo.scheduler;

import ch.qos.logback.core.util.FixedDelay;
import com.magalu.demo.DemoApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class MagaluTaskScheduler {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void runTask(){
        var dateTime = LocalDateTime.now();
        logger.info("Running at {}", dateTime);
    }
}
