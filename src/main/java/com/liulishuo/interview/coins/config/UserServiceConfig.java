package com.liulishuo.interview.coins.config;

import com.liulishuo.interview.coins.controller.UserController;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = "com.liulishuo.interview.coins.model")
@EnableJpaRepositories(basePackages = "com.liulishuo.interview.coins.dao")
@EnableTransactionManagement
public class UserServiceConfig {

    @Bean
    public UserController controller() {
        return new UserController();
    }
}
