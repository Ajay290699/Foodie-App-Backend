package com.niit.ApiGateWay.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public RouteLocator getRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/app/v1/**")
                        .uri("lb://UserAuthService/*"))
                .route(p -> p
                        .path("/owner-auth/**")
                        .uri("lb://restaurantAuthService/*"))
                .route(p -> p
                        .path("/restaurant-service/**")
                        .uri("lb://restaurantService/*"))
                .route(p -> p
                        .path("/foodieApp/userService/**")
                        .uri("lb://UserService/*"))
                .build();
    }
}
