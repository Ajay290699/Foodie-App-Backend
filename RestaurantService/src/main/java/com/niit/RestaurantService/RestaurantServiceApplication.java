package com.niit.RestaurantService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RestaurantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantServiceApplication.class, args);
	}


//	@Bean
//	public FilterRegistrationBean filter() {
//		FilterRegistrationBean frObj = new FilterRegistrationBean<>();
//		frObj.setFilter(new JwtFilter());
//		frObj.addUrlPatterns("/restaurant-service/add-restaurant/*", "/restaurant-service/add-dish/*",
//				"/restaurant-service/getAllDishes","/restaurant-service/getAllRestaurant",
//				"/restaurant-service/updateRestaurant","/restaurant-service/updateDishes",
//				"/restaurant-service/deleteDish/*","/restaurant-service/deleteRestaurant/*");
//		return frObj;
//	}

//	@Bean
//	public FilterRegistrationBean  filterUrl(){
//		FilterRegistrationBean frb = new FilterRegistrationBean();
//		frb.setFilter(new JwtFilter());
//		frb.addUrlPatterns("/product-app-v1/get-user-details{ajay}","/product-app-v1/add-product-to-user",
//				"/product-app-v1/admin/add-new-product","/product-app-v1/admin/update-product",
//				"/product-app-v1/admin/delete-product");
//		return frb;
//	}

//	@Bean
//	public FilterRegistrationBean filterRegistrationBean(){
//		final CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("http://localhost:4200");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**",config);
//		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter());
//		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return bean;
//	}


}
