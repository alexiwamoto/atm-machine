package com.atmproject.cashmachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */
@SpringBootApplication
@EnableAutoConfiguration
public class AtmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtmApplication.class, args);
    }
}
