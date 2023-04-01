package com.niit.capstoneproject.restaurantAuth;

import com.niit.capstoneproject.restaurantAuth.services.RestaurantAuthService;
import com.niit.capstoneproject.restaurantAuth.services.RestaurantServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@EnableFeignClients
@SpringBootApplication  //(scanBasePackages = {"controller","service","models", "repos"})
public class RestaurantAuthApplication {


	public static void main(String[] args) {
		SpringApplication.run(RestaurantAuthApplication.class, args);
	}

	@Bean
	public RestaurantAuthService genBean() {
		return new RestaurantServiceImpl();
	}

}
