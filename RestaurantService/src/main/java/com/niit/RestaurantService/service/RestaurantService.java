package com.niit.RestaurantService.service;

import com.niit.RestaurantService.exceptions.RestaurantAlreadyExistsException;
import com.niit.RestaurantService.models.Dishes;
import com.niit.RestaurantService.models.Restaurant;
import com.niit.RestaurantService.models.RestaurantOwner;

import java.util.List;

public interface RestaurantService {

    public List<Restaurant> getAllRestaurant();

    public List<Dishes> getAllDishes();

    RestaurantOwner addOwner(RestaurantOwner restaurantOwner);

    public abstract RestaurantOwner addRestaurant(String restaurantOwnerId, Restaurant restaurant) throws RestaurantAlreadyExistsException;

    public abstract Restaurant addDishesToRestaurant(Dishes dishes, String resturantName);

    public Restaurant updateRestaurantDetails(String restaurantName, String location);

    public Dishes updateDishDetails(String dishName, String type, double price);

    public Dishes deleteDishes(String dishName);

    public Restaurant deleteRestaurant(String restaurantName);

}
