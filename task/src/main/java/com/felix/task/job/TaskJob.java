package com.felix.task.job;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * 没使用线程池的方式
 */
@Configuration
@EnableScheduling
@Slf4j
public class TaskJob {


    @Scheduled(cron = "*/6 * * * * ?", zone = "GMT+8")
    public void job1() {
        log.info(String.format("【%s】开始执行：{}",Thread.currentThread().getName()), DateUtil.formatDateTime(new Date()));
    }

    @Scheduled(cron = "*/5 * * * * ?", zone = "GMT+8")
    public void job2() {
        log.info(String.format("【%s】开始执行：{}",Thread.currentThread().getName()), DateUtil.formatDateTime(new Date()));
    }
}
