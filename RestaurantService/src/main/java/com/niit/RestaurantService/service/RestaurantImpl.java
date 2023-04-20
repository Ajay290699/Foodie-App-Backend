package com.niit.RestaurantService.service;

import com.niit.RestaurantService.models.Dishes;
import com.niit.RestaurantService.models.Restaurant;
import com.niit.RestaurantService.models.RestaurantOwner;
import com.niit.RestaurantService.repos.OwnerRepo;
import com.niit.RestaurantService.repos.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
    public Restaurant addDishesToRestaurant(Dishes dishes, String restName) {
        Restaurant restaurant = restaurantRepo.findById(restName).get();
//        Restaurant restaurant = restaurantOwner.getRestaurant();
        restaurant.getDishesSet().add(dishes);

        return restaurantRepo.save(restaurant);
    }

    @Override
    public RestaurantOwner addOwner(RestaurantOwner restaurantOwner) {
        return ownerRepo.save(restaurantOwner);
    }

    //getAllDishes()
    //getAllRestaurant()

}
