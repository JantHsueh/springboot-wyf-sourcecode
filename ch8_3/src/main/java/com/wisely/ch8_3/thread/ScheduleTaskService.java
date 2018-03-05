package com.wisely.ch8_3.thread;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTaskService {

    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 100000)
    private void scheduleTask(){
        System.out.println("执行"+ mSimpleDateFormat.format(new Date()));
    }
}
