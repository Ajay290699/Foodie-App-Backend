package com.niit.UserAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class UserAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthApplication.class, args);
	}

//	@Bean
//	public UserProxy bean(){
//		return new UserProxy();
//	}

//	public int generateOTP() {
//		int otp = ThreadLocalRandom.current().nextInt(1000,9999);
//		return otp;
//	}
}
