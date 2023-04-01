package com.niit.capstoneproject.restaurantAuth.services.tokenService;

import com.niit.capstoneproject.restaurantAuth.models.RestaurantOwner;

import java.util.Map;

public interface TokenGenInt {

    public Map<String, String> tokenGeneration(RestaurantOwner restaurantOwner);
}
