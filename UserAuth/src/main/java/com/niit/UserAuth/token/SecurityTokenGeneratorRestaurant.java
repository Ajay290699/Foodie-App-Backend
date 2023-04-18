package com.niit.UserAuth.token;

import com.niit.UserAuth.domain.restaurantOwner.RestaurantOwner;

import java.util.Map;

public interface SecurityTokenGeneratorRestaurant {

    public Map<String, String> tokenGeneration(RestaurantOwner restaurantOwner);
}
