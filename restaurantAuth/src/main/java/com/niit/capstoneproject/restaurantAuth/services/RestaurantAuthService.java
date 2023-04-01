package com.niit.capstoneproject.restaurantAuth.services;

import com.niit.capstoneproject.restaurantAuth.models.RestaurantOwner;


public interface RestaurantAuthService {

    public abstract RestaurantOwner signUpOwner(RestaurantOwner restaurantOwner);

    public abstract RestaurantOwner login(String emailId, String password);

}
