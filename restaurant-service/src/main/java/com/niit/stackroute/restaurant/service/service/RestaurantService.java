package com.niit.stackroute.restaurant.service.service;

import com.niit.stackroute.restaurant.service.exceptions.RestaurantAlreadyExistsException;
import com.niit.stackroute.restaurant.service.models.Dishes;
import com.niit.stackroute.restaurant.service.models.Restaurant;
import com.niit.stackroute.restaurant.service.models.RestaurantOwner;

public interface RestaurantService {

    public abstract RestaurantOwner addRestaurant(Restaurant restaurant, String emailId) throws RestaurantAlreadyExistsException;

    public abstract RestaurantOwner addDishesToRestaurant(Dishes dishes, String emailId);

    RestaurantOwner addOwner(RestaurantOwner restaurantOwner);


}
