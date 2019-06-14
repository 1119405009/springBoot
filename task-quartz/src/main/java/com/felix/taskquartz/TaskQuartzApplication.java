package com.felix.taskquartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.felix.taskquartz.mapper"})
@SpringBootApplication
public class TaskQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskQuartzApplication.class, args);
    }

}
