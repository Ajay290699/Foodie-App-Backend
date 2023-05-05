package com.niit.UserService;

import com.niit.UserService.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableEurekaClient
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<?> filterUrl() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/userService/getAllUser");
        filterRegistrationBean.addUrlPatterns("/foodieApp/userService/addDishesToUserFavourite");
        filterRegistrationBean.addUrlPatterns("/foodieApp/userService/addRestaurantToUserFavourite");
        filterRegistrationBean.addUrlPatterns("/foodieApp/userService/addDishesToUserCart");
        filterRegistrationBean.addUrlPatterns("/foodieApp/userService/getUserFavouriteAllDishes");
        filterRegistrationBean.addUrlPatterns("/foodieApp/userService/getUserFavouriteAllRestaurants");
        filterRegistrationBean.addUrlPatterns("/foodieApp/userService/getUserCartAllDishes");
        filterRegistrationBean.addUrlPatterns("/foodieApp/userService/deleteDishesFromUserFavourite");
        filterRegistrationBean.addUrlPatterns("/foodieApp/userService/deleteRestaurantFromUserFavourite");
        filterRegistrationBean.addUrlPatterns("/foodieApp/userService/deleteDishesFromUserCart");
        filterRegistrationBean.addUrlPatterns("/foodieApp/userService/deleteAllDishesFromUserCart");
        filterRegistrationBean.addUrlPatterns("/foodieApp/userService/getUserDetails");
        return filterRegistrationBean;
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

