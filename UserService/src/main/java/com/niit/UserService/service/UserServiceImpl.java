package com.niit.UserService.service;

import com.niit.UserService.model.*;
import com.niit.UserService.repository.CartItemRepository;
import com.niit.UserService.repository.FavouriteRepository;
import com.niit.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

//    @Override
//    public List<User> getAllUser() {
//        return userRepository.findAll();
//    }

    @Override
    public User getUserDetails(String emailId) {
        User user = userRepository.findById(emailId).get();
        return user;
    }


    @Override
    public List<Dishes> addDishesToUserFavourite(String emailId, Dishes dishes) {
        if (favouriteRepository.findById(emailId).isPresent()) {
            Favourites favourites = favouriteRepository.findById(emailId).get();
            favourites.addDishes(dishes);
            favouriteRepository.save(favourites);
            return favouriteRepository.findById(emailId).get().getDishes();
        } else {
            Favourites favourites = new Favourites();
            favourites.setEmail(emailId);
            favourites.addDishes(dishes);
            favouriteRepository.save(favourites);
            return favouriteRepository.findById(emailId).get().getDishes();
        }

    }

    @Override
    public List<Restaurant> addRestaurantToUserFavourite(String emailId, Restaurant restaurant) {
        if (favouriteRepository.findById(emailId).isPresent()) {
            Favourites favourites = favouriteRepository.findById(emailId).get();
            favourites.addRestaurant(restaurant);
            favouriteRepository.save(favourites);
            return favouriteRepository.findById(emailId).get().getRestaurants();
        } else {
            Favourites favourites = new Favourites();
            favourites.setEmail(emailId);
            favourites.addRestaurant(restaurant);
            favouriteRepository.save(favourites);
            return favouriteRepository.findById(emailId).get().getRestaurants();
        }

    }

    @Override
    public List<Dishes> addDishesToUserCart(String emailId, Dishes dishes) {
        if (cartItemRepository.findById(emailId).isPresent()) {
            CartItem cartItem = cartItemRepository.findById(emailId).get();
            cartItem.addDishes(dishes);
            cartItemRepository.save(cartItem);
            return cartItemRepository.findById(emailId).get().getDishesList();
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setEmail(emailId);
            cartItem.addDishes(dishes);
            cartItemRepository.save(cartItem);
            return cartItemRepository.findById(emailId).get().getDishesList();
        }
    }

    @Override
    public List<Dishes> getUserFavouriteAllDishes(String emailId) {
        return favouriteRepository.findById(emailId).get().getDishes();
    }

    @Override
    public List<Restaurant> getUserFavouriteAllRestaurants(String emailId) {
        return favouriteRepository.findById(emailId).get().getRestaurants();
    }

    public List<Dishes> getUserCartAllDishes(String emailId) {
        return cartItemRepository.findById(emailId).get().getDishesList();
    }

    @Override
    public List<Dishes> deleteDishFromUserFavourite(String emailId, Dishes dishes) {
        if (favouriteRepository.findById(emailId).isPresent()) {
            Favourites favourites = favouriteRepository.findById(emailId).get();
            favourites.getDishes().remove(dishes);
            favouriteRepository.save(favourites);
            return favouriteRepository.findById(emailId).get().getDishes();
        }
        return null;
    }

    @Override
    public List<Restaurant> deleteRestaurantFromUserFavourite(String emailId, Restaurant restaurant) {
        if (favouriteRepository.findById(emailId).isPresent()) {
            Favourites favourites = favouriteRepository.findById(emailId).get();
            favourites.getRestaurants().remove(restaurant);
            favouriteRepository.save(favourites);
            return favouriteRepository.findById(emailId).get().getRestaurants();
        }
        return null;
    }


    @Override
    public List<Dishes> deleteDishFromUserCart(String emailId, Dishes dishes) {
        if (cartItemRepository.findById(emailId).isPresent()) {
            CartItem cartItem = cartItemRepository.findById(emailId).get();
            cartItem.getDishesList().remove(dishes);
            cartItemRepository.save(cartItem);
            return cartItemRepository.findById(emailId).get().getDishesList();
        }
        return null;
    }

    @Override
    public List<Dishes> deleteAllCartItem(String emailId, List<Dishes> dishes) {
        if (cartItemRepository.findById(emailId).isPresent()) {
            CartItem cartItem = cartItemRepository.findById(emailId).get();
            cartItem.getDishesList().removeAll(dishes);
            cartItemRepository.save(cartItem);
            return cartItemRepository.findById(emailId).get().getDishesList();
        }
        return null;
    }

    @Override
    public User updateAddress(User user) {
        return userRepository.save(user);
    }


}