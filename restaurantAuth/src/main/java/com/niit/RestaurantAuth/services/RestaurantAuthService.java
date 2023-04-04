package com.niit.RestaurantAuth.services;


import com.niit.RestaurantAuth.models.RestaurantOwner;

public interface RestaurantAuthService {

    public abstract RestaurantOwner signUpOwner(RestaurantOwner restaurantOwner);

    public abstract RestaurantOwner login(String emailId, String password);

}
