package com.niit.RestaurantAuth.services.tokenService;

import com.niit.RestaurantAuth.models.RestaurantOwner;

import java.util.Map;

public interface TokenGenInt {

    public Map<String, String> tokenGeneration(RestaurantOwner restaurantOwner);
}
