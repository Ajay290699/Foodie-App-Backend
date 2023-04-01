package com.niit.UserService.service;

import com.niit.UserService.model.Address;
import com.niit.UserService.model.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {

//    public User addUser(User user);

    public User addUser(User user);

    public List<User> getAllUser();

    public User getUserDetails(String emailId);

    public Set<String> getUserFavouriteAllCuisines(String emailId);

    public Map<String, String> getUserFavouriteAllRestaurants(String emailId);

    public Address getUserAddress(String emailId);

    public Set<String> addCuisinesToUserFavourite(String emailId, String cuisine);

    public Map<String, String> addRestaurantToUserFavourite(String emailId, String restaurantName, String restaurantId);

    public Address updateUserAddress(String emailId, String orderId, Address address);

    //    public Address deleteAddressFromUser(String emailId,String buildingName);
    public Set<String> deleteCuisineFromUserFavourite(String emailId, String cuisineName);

    public Map<String, String> deleteRestaurantFromUserFavourite(String emailId, String restaurantName);

}
