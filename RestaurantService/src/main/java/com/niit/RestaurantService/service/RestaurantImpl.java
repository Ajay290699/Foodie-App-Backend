package com.niit.RestaurantService.service;

import com.niit.RestaurantService.models.Dishes;
import com.niit.RestaurantService.models.Restaurant;
import com.niit.RestaurantService.models.RestaurantOwner;
import com.niit.RestaurantService.repos.DishesRepo;
import com.niit.RestaurantService.repos.OwnerRepo;
import com.niit.RestaurantService.repos.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantImpl implements RestaurantService {

    @Autowired
    private OwnerRepo ownerRepo;
    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private DishesRepo dishesRepo;

    @Override
    public RestaurantOwner addRestaurant(String restaurantOwnerId, Restaurant restaurant) {
        RestaurantOwner owner = ownerRepo.findById(restaurantOwnerId).get();
        Restaurant restaurant1 = restaurantRepo.save(restaurant);
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        owner.setRestaurant(restaurantList);
        return ownerRepo.save(owner);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants;
    }


    @Override
    public Restaurant addDishesToRestaurant(Dishes dishes, String restName) {
        Dishes dishes1 = dishesRepo.save(dishes);
        Restaurant restaurant = restaurantRepo.findById(restName).get();
//        Restaurant restaurant = restaurantOwner.getRestaurant();
        restaurant.getDishesSet().add(dishes1);
        return restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurantDetails(String restaurantName, String location) {
        if (restaurantRepo.findById(restaurantName).isPresent()) {
            Restaurant restaurant1 = restaurantRepo.findById(restaurantName).get();
            restaurant1.setRestaurantName(restaurantName);
            restaurant1.setLocation(location);
            return restaurantRepo.save(restaurant1);
        }
        return null;
    }

    @Override
    public Dishes updateDishDetails(String dishName, String type, double price) {
        if (dishesRepo.findById(dishName).isPresent()) {
            Dishes dishes = dishesRepo.findById(dishName).get();
            dishes.setType(type);
            dishes.setDishPrice(price);
            return dishesRepo.save(dishes);
        }
        return null;
    }

    @Override
    public List<Dishes> getAllDishes() {
        List<Dishes> dishes = dishesRepo.findAll();
        return dishes;
    }


    @Override
    public RestaurantOwner addOwner(RestaurantOwner restaurantOwner) {
        return ownerRepo.save(restaurantOwner);
    }

}
