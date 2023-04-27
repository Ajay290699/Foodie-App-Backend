package com.niit.UserService.service;

import com.niit.UserService.model.User;
import com.niit.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserDetails(String emailId) {
        User user = userRepository.findById(emailId).get();
        return user;
    }

    @Override
    public Set<String> getUserFavouriteAllCuisines(String emailId) {
        return userRepository.findById(emailId).get().getFavourites().getCuisines();
    }

    @Override
    public Map<String, String> getUserFavouriteAllRestaurants(String emailId) {
        return userRepository.findById(emailId).get().getFavourites().getRestaurants();
    }


    @Override
    public Set<String> addCuisinesToUserFavourite(String emailId, String cuisine) {
        User user = userRepository.findById(emailId).get();
        user.getFavourites().getCuisines().add(cuisine);
        return userRepository.save(user).getFavourites().getCuisines();
    }

    @Override
    public Map<String, String> addRestaurantToUserFavourite(String emailId, String restaurantName, String restaurantId) {
        User user = userRepository.findById(emailId).get();
        user.getFavourites().getRestaurants().put(restaurantId, restaurantName);
        return userRepository.save(user).getFavourites().getRestaurants();
    }


    @Override
    public Set<String> deleteCuisineFromUserFavourite(String emailId, String cuisineName) {
        User user = userRepository.findById(emailId).get();
        for (String cuisine : user.getFavourites().getCuisines()) {
            if (cuisine.equalsIgnoreCase(cuisineName)) {
                user.getFavourites().getCuisines().remove(cuisine);
                return userRepository.save(user).getFavourites().getCuisines();
            }
        }
        return null;
    }

    @Override
    public Map<String, String> deleteRestaurantFromUserFavourite(String emailId, String restaurantName) {
        User user = userRepository.findById(emailId).get();
        for (Map.Entry<String, String> restaurant : user.getFavourites().getRestaurants().entrySet()) {
            if (restaurant.getValue().equalsIgnoreCase(restaurantName)) {
                user.getFavourites().getRestaurants().remove(restaurant.getKey());
                return userRepository.save(user).getFavourites().getRestaurants();
            }
        }
        return null;
    }

}