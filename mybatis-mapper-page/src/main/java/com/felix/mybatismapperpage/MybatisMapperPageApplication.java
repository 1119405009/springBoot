package com.felix.mybatismapperpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.felix.mybatismapperpage.mapper"})
public class MybatisMapperPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisMapperPageApplication.class, args);
    }

}
