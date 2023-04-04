package com.niit.stackroute.restaurant.service.repos;

import com.niit.stackroute.restaurant.service.models.Dishes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishesRepo extends MongoRepository<Dishes, String> {

}
