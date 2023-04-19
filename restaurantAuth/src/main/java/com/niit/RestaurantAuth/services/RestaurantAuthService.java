package com.niit.RestaurantAuth.services;


import com.niit.RestaurantAuth.exception.EmailAlreadyRegistered;
import com.niit.RestaurantAuth.exception.InvalidCredentialsException;
import com.niit.RestaurantAuth.models.RestaurantOwner;

public interface RestaurantAuthService {

    public abstract RestaurantOwner signUpOwner(RestaurantOwner restaurantOwner) throws EmailAlreadyRegistered;

    public abstract RestaurantOwner restaurantOwnerLogin(RestaurantOwner restaurantOwner) throws InvalidCredentialsException;

}
