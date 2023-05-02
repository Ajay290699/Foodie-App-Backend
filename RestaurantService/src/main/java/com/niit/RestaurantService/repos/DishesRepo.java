package com.niit.RestaurantService.repos;


import com.niit.RestaurantService.models.Dishes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishesRepo extends MongoRepository<Dishes, String> {


}
