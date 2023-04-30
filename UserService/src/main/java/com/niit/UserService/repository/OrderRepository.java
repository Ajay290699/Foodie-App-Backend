package com.niit.UserService.repository;

import com.niit.UserService.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {


}
