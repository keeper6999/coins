package com.liulishuo.interview.coins;

import com.liulishuo.interview.coins.config.ApplicationConfig;
import com.liulishuo.interview.coins.config.OpsServiceConfig;
import com.liulishuo.interview.coins.config.UserServiceConfig;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplicationBuilder parentBuilder =
                new SpringApplicationBuilder(ApplicationConfig.class);

        parentBuilder.child(OpsServiceConfig.class).properties("server.port:8081").run(args);

        parentBuilder.child(UserServiceConfig.class).properties("server.port:8080").run(args);
    }
}
