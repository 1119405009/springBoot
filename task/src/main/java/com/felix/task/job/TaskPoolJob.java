package com.felix.task.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

@Component
@Slf4j
public class TaskPoolJob {

    /**
     * 按照标准时间来算，每隔 10s 执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void job1() {
        log.info(String.format("【%s】开始执行：{}",Thread.currentThread().getName()), DateUtil.formatDateTime(new Date()));
    }

    /**
     * 从启动时间开始，间隔 2s 执行
     * 固定间隔时间
     */
    @Scheduled(fixedRate = 2000)
    public void job2() {
        log.info(String.format("【%s】开始执行：{}",Thread.currentThread().getName()), DateUtil.formatDateTime(new Date()));
    }

    /**
     * 从启动时间开始，延迟 5s 后间隔 4s 执行
     * 固定等待时间
     */
    @Scheduled(fixedDelay = 4000, initialDelay = 5000)
    public void job3() {
        log.info(String.format("【%s】开始执行：{}",Thread.currentThread().getName()), DateUtil.formatDateTime(new Date()));
    }
}
