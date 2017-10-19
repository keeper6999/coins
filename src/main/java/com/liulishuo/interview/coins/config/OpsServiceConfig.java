package com.liulishuo.interview.coins.config;

import com.liulishuo.interview.coins.controller.OpsController;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class OpsServiceConfig {

    @Bean
    public OpsController controller() {
        return new OpsController();
    }
}
