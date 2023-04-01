package com.niit.stackroute.restaurant.service.repos;

import com.niit.stackroute.restaurant.service.models.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RestaurantRepo extends MongoRepository<Restaurant, String> {
}
