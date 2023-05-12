package com.niit.UserService.service;

import com.niit.UserService.model.Dishes;
import com.niit.UserService.model.Restaurant;
import com.niit.UserService.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    public User addUser(User user);

//    public List<User> getAllUser();

    public User getUserDetails(String emailId);

    public Set<Dishes> addDishesToUserFavourite(String emailId, Dishes dishes);

    public Set<Restaurant> addRestaurantToUserFavourite(String emailId, Restaurant restaurant);

    public List<Dishes> addDishesToUserCart(String emailId, Dishes dishes);

    public Set<Dishes> getUserFavouriteAllDishes(String emailId);

    public Set<Restaurant> getUserFavouriteAllRestaurants(String emailId);

    public List<Dishes> getUserCartAllDishes(String emailId);

    public Set<Dishes> deleteDishFromUserFavourite(String emailId, Dishes dishes);

    public Set<Restaurant> deleteRestaurantFromUserFavourite(String emailId, Restaurant restaurant);

    public List<Dishes> deleteDishFromUserCart(String emailId, Dishes dishes);

    public List<Dishes> deleteAllCartItem(String emailId, List<Dishes> dishes);

    public User updateAddress(User user);
}