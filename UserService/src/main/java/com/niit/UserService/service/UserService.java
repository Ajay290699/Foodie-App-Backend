package com.niit.UserService.service;

import com.niit.UserService.model.Dishes;
import com.niit.UserService.model.Restaurant;
import com.niit.UserService.model.User;

import java.util.List;

public interface UserService {

    public User addUser(User user);

//    public List<User> getAllUser();

    public User getUserDetails(String emailId);

    public List<Dishes> addDishesToUserFavourite(String emailId, Dishes dishes);

    public List<Restaurant> addRestaurantToUserFavourite(String emailId, Restaurant restaurant);

    public List<Dishes> addDishesToUserCart(String emailId, Dishes dishes);

    public List<Dishes> getUserFavouriteAllDishes(String emailId);

    public List<Restaurant> getUserFavouriteAllRestaurants(String emailId);

    public List<Dishes> getUserCartAllDishes(String emailId);

    public List<Dishes> deleteDishFromUserFavourite(String emailId, Dishes dishes);

    public List<Restaurant> deleteRestaurantFromUserFavourite(String emailId, Restaurant restaurant);

    public List<Dishes> deleteDishFromUserCart(String emailId, Dishes dishes);

    public User updateAddress(User user);
}