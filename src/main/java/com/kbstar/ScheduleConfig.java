package com.kbstar;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

// 스케쥴링의 환경설정 파일

@Configuration
public class ScheduleConfig implements SchedulingConfigurer

{
    private final int POOL_SIZE = 6;
    //6개의 풀을 사용할 것임
    @Override
    public void configureTasks(ScheduledTaskRegistrar registry) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("CommB-Scheduled-task-");
        threadPoolTaskScheduler.initialize();

        registry.setTaskScheduler(threadPoolTaskScheduler);
    }

}