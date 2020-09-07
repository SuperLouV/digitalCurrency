package com.chainalysis.digital_currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DigitalCurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalCurrencyApplication.class, args);
    }

}
