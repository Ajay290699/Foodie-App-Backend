package com.niit.RestaurantService.service;

import com.niit.RestaurantService.exceptions.RestaurantAlreadyExistsException;
import com.niit.RestaurantService.models.Dishes;
import com.niit.RestaurantService.models.Restaurant;
import com.niit.RestaurantService.models.RestaurantOwner;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface RestaurantService {

    public List<Restaurant> getAllRestaurant();

    public List<Dishes> getAllDishes();

    String uploadImage(String path, MultipartFile file) throws IOException;

    InputStream getImage(String path, String fileName) throws FileNotFoundException;

    RestaurantOwner addOwner(RestaurantOwner restaurantOwner);

//    RestaurantOwner owner(String restaurantOwnerId);


//    public List<Restaurant> getOwnerRestaurant(String restaurantOwnerId);

    public abstract RestaurantOwner addRestaurant(String restaurantOwnerId, Restaurant restaurant) throws RestaurantAlreadyExistsException;

    public abstract Restaurant addDishesToRestaurant(String resturantName, Dishes dishes);

    public Restaurant updateRestaurantDetails(String restaurantName, String location);

    public Dishes updateDishDetails(String dishName, String type, double price);

    public Dishes deleteDishes(String dishName);

    public Restaurant deleteRestaurant(String restaurantName);

}
