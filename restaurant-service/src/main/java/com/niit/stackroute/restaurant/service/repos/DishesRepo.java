package com.niit.stackroute.restaurant.service.repos;

import com.niit.stackroute.restaurant.service.models.Dishes;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DishesRepo extends MongoRepository<Dishes, String> {

}
