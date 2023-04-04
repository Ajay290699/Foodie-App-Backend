package com.niit.stackroute.restaurant.service.repos;

import com.niit.stackroute.restaurant.service.models.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends MongoRepository<Restaurant, String> {
}
