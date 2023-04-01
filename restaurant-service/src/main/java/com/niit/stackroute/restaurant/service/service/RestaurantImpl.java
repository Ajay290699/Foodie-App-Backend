package com.niit.stackroute.restaurant.service.service;

import com.niit.stackroute.restaurant.service.models.Dishes;
import com.niit.stackroute.restaurant.service.models.Restaurant;
import com.niit.stackroute.restaurant.service.models.RestaurantOwner;
import com.niit.stackroute.restaurant.service.repos.OwnerRepo;
import com.niit.stackroute.restaurant.service.repos.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class RestaurantImpl implements RestaurantService {

    @Autowired
    private OwnerRepo ownerRepo;
    @Autowired
    private RestaurantRepo restaurantRepo;

    @Override
    public RestaurantOwner addRestaurant(Restaurant restaurant, String emailId) {
        RestaurantOwner owner = ownerRepo.findById(emailId).get();
        owner.setRestaurant(restaurant);
        restaurantRepo.save(restaurant);
        return ownerRepo.save(owner);

    }

    @Override
    public RestaurantOwner addDishesToRestaurant(Dishes dishes, String emailId) {
        RestaurantOwner restaurantOwner = ownerRepo.findById(emailId).get();
        Restaurant restaurant = restaurantOwner.getRestaurant();
        restaurant.getDishesSet().add(dishes);

        return ownerRepo.save(restaurantOwner);
    }

    @Override
    public RestaurantOwner addOwner(RestaurantOwner restaurantOwner) {
        return ownerRepo.save(restaurantOwner);
    }

    //getAllDishes()
    //getAllRestaurant()

}
