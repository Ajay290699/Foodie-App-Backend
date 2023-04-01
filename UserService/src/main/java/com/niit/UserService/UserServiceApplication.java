package com.niit.UserService;

import com.niit.UserService.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<?> filterUrl() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/foodieApp/userService/getUserDetails");
		filterRegistrationBean.addUrlPatterns("/foodieApp/userService/getUserFavouriteAllCuisines");
		filterRegistrationBean.addUrlPatterns("/foodieApp/userService/getUserFavouriteAllRestaurants");
		filterRegistrationBean.addUrlPatterns("/foodieApp/userService/getUserAddress");
		filterRegistrationBean.addUrlPatterns("/foodieApp/userService/addCuisinesToUserFavourite");
		filterRegistrationBean.addUrlPatterns("/foodieApp/userService/addRestaurantToUserFavourite/*");
		filterRegistrationBean.addUrlPatterns("/foodieApp/userService/updateUserAddress/*");
		filterRegistrationBean.addUrlPatterns("/foodieApp/userService/deleteCuisineFromUserFavourite");
		filterRegistrationBean.addUrlPatterns("/userService/deleteRestaurantFromUserFavourite/*");

		return filterRegistrationBean;
	}
}
