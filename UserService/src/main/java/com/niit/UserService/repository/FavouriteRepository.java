package com.niit.UserService.repository;

import com.niit.UserService.model.Favourites;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavouriteRepository extends MongoRepository<Favourites, String> {

}
