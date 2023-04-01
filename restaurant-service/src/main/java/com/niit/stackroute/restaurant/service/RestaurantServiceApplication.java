package com.niit.stackroute.restaurant.service;

import com.niit.stackroute.restaurant.service.filter.JwtFilter;
import com.niit.stackroute.restaurant.service.service.RestaurantImpl;
import com.niit.stackroute.restaurant.service.service.RestaurantService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication  //(scanBasePackages = {"controller","service","models", "repos"})
public class RestaurantServiceApplication {

	@Bean
	public static RestaurantService bean() {
		return new RestaurantImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(RestaurantServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filter() {
		FilterRegistrationBean frObj = new FilterRegistrationBean<>();
		frObj.setFilter(new JwtFilter());
		frObj.addUrlPatterns("/restaurant-service/add-restaurant", "/restaurant-service/add-dish/*");
		return frObj;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}


}
