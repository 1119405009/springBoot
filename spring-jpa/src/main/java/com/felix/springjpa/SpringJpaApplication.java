package com.felix.springjpa;

import com.felix.springjpa.dao.impl.BaseDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * spring  Jpa的使用复杂的查询
 *
 *
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseDaoImpl.class)
public class SpringJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaApplication.class, args);
    }

}
