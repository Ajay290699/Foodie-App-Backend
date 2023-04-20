package com.niit.RestaurantService.service;

import com.niit.RestaurantService.exceptions.RestaurantAlreadyExistsException;
import com.niit.RestaurantService.models.Dishes;
import com.niit.RestaurantService.models.Restaurant;
import com.niit.RestaurantService.models.RestaurantOwner;

public interface RestaurantService {

    public abstract Restaurant addRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException;

    public abstract Restaurant addDishesToRestaurant(Dishes dishes, String resturantName);

    RestaurantOwner addOwner(RestaurantOwner restaurantOwner);


}
