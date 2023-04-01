package com.niit.stackroute.restaurant.service.repos;

import com.niit.stackroute.restaurant.service.models.RestaurantOwner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OwnerRepo extends MongoRepository<RestaurantOwner, String> {
}
