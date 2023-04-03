package com.niit.stackroute.restaurant.service.controller;

import com.niit.stackroute.restaurant.service.exceptions.RestaurantAlreadyExistsException;
import com.niit.stackroute.restaurant.service.models.Dishes;
import com.niit.stackroute.restaurant.service.models.Restaurant;
import com.niit.stackroute.restaurant.service.models.RestaurantOwner;
import com.niit.stackroute.restaurant.service.repos.RestaurantRepo;
import com.niit.stackroute.restaurant.service.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RequestMapping("/restaurant-service")
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RestaurantRepo restaurantRepo;
//    public RestaurantController(RestaurantService restaurantService){
//        this.restaurantService=restaurantService;
//    }

    @PostMapping("/add-owner")
    public ResponseEntity<?> addOwner(@RequestBody RestaurantOwner restaurantOwner) {
        return new ResponseEntity<>(restaurantService.addOwner(restaurantOwner), HttpStatus.OK);
    }

    @PostMapping("/add-restaurant")
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant, HttpServletRequest httpServletRequest) {

        try {
            String emailId = (String) httpServletRequest.getAttribute("owner-emailId");
            return new ResponseEntity<>(restaurantService.addRestaurant(restaurant, emailId), HttpStatus.OK);
        } catch (RestaurantAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/add-dish")
    public ResponseEntity<?> addDishes(@RequestBody Dishes dish, HttpServletRequest httpServletRequest) {
        //String currEmailId=(String) httpServletRequest.getAttribute("user-emailId");
        String ownerEmailId = (String) httpServletRequest.getAttribute("owner-emailId");
        return new ResponseEntity<>(restaurantService.addDishesToRestaurant(dish, ownerEmailId), HttpStatus.OK);
    }

}