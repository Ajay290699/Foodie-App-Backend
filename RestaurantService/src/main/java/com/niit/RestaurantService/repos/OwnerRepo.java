package com.niit.RestaurantService.repos;


import com.niit.RestaurantService.models.RestaurantOwner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepo extends MongoRepository<RestaurantOwner, String> {
}
