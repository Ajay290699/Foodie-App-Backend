package com.niit.RestaurantService.controller;

import com.niit.RestaurantService.exceptions.RestaurantAlreadyExistsException;
import com.niit.RestaurantService.models.Dishes;
import com.niit.RestaurantService.models.Restaurant;
import com.niit.RestaurantService.models.RestaurantOwner;
import com.niit.RestaurantService.repos.RestaurantRepo;
import com.niit.RestaurantService.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/restaurant-service")
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    //    @Autowired
//    private OwnerRepo ownerRepo;
//
//    @Autowired
//    private DishesRepo dishesRepo;
    @Autowired
    private RestaurantRepo restaurantRepo;
//    public RestaurantController(RestaurantService restaurantService){
//        this.restaurantService=restaurantService;
//    }

    @PostMapping("/add-owner")
    public ResponseEntity<?> addOwner(@RequestBody RestaurantOwner restaurantOwner) {
        return new ResponseEntity<>(restaurantService.addOwner(restaurantOwner), HttpStatus.OK);
    }

//    @GetMapping("/getOwnerRestaurant/{restaurantOwnerId}")
//    public ResponseEntity<?> getOwnerRestaurant(@PathVariable String restaurantOwnerId){
//        return new ResponseEntity<>(restaurantService.getOwnerRestaurant(restaurantOwnerId),HttpStatus.OK);
//    }

    @PostMapping("/add-restaurant/{restaurantOwnerId}")
    public ResponseEntity<?> addRestaurant(@PathVariable String restaurantOwnerId, @RequestBody Restaurant restaurant) {

        try {
            return new ResponseEntity<>(restaurantService.addRestaurant(restaurantOwnerId, restaurant), HttpStatus.OK);
        } catch (RestaurantAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/add-dish/{resturatName}")
    public ResponseEntity<?> addDishes(@RequestBody Dishes dish, @PathVariable String resturatName) {
        return new ResponseEntity<>(restaurantService.addDishesToRestaurant(resturatName, dish), HttpStatus.OK);
    }

    @GetMapping("/getAllDishes")
    public ResponseEntity<?> getAllDishes() {
        return new ResponseEntity<>(restaurantService.getAllDishes(), HttpStatus.OK);
    }

    @GetMapping("/getAllRestaurant")
    public ResponseEntity<?> getAllRestaurants() {
        return new ResponseEntity<>(restaurantService.getAllRestaurant(), HttpStatus.OK);
    }

    @PutMapping("/updateRestaurant")
    public ResponseEntity<?> updateRestaurantDetails(@RequestBody Restaurant restaurant) {
        return new ResponseEntity<>(restaurantService.updateRestaurantDetails(restaurant.getRestaurantName(),
                restaurant.getLocation()), HttpStatus.OK);
    }

    @PutMapping("/updateDishes")
    public ResponseEntity<?> updateDishDetails(@RequestBody Dishes dishes) {
        return new ResponseEntity<>(restaurantService.updateDishDetails(dishes.getDishName(), dishes.getType(),
                dishes.getDishPrice()), HttpStatus.OK);
    }

    @DeleteMapping("/deleteDish/{dishName}")
    public ResponseEntity<?> deleteDishes(@PathVariable String dishName) {
        return new ResponseEntity<>(restaurantService.deleteDishes(dishName), HttpStatus.OK);
    }

    @DeleteMapping("/deleteRestaurant/{restaurantName}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable String restaurantName) {
        return new ResponseEntity<>(restaurantService.deleteRestaurant(restaurantName), HttpStatus.OK);
    }

}
