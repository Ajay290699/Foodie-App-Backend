package com.niit.UserAuth.service;


import com.niit.UserAuth.domain.restaurantOwner.RestaurantOwner;
import com.niit.UserAuth.exception.InvalidCredentialsException;

public interface RestaurantAuthService {

    public abstract RestaurantOwner signUpOwner(RestaurantOwner restaurantOwner);

    public abstract RestaurantOwner restaurantOwnerLogin(String email, String password) throws InvalidCredentialsException;

}
