package com.niit.RestaurantService.repos;


import com.niit.RestaurantService.models.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepo extends MongoRepository<Restaurant, String> {

}
