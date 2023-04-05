package com.niit.MailNotificationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MailNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailNotificationServiceApplication.class, args);
    }

}
