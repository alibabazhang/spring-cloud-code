package com.rui.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TccCoordinatorExampleApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TccCoordinatorExampleApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
