package com.niit.UserService.controller;

import com.niit.UserService.model.Dishes;
import com.niit.UserService.model.Order;
import com.niit.UserService.model.Restaurant;
import com.niit.UserService.model.User;
import com.niit.UserService.service.OrderServiceImpl;
import com.niit.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/foodieApp/userService")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderServiceImpl orderServiceimpl;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

//    @GetMapping("/getAllUser")
//    public ResponseEntity<?> getAllUser() {
//        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
//    }

    @GetMapping("/getUserDetails")
    public ResponseEntity<?> getUserDetails(HttpServletRequest request) {
        String emailId = (String) request.getAttribute("emailId");
        return new ResponseEntity<>(userService.getUserDetails(emailId), HttpStatus.OK);
    }

    @GetMapping("/getUserFavouriteAllDishes")
    public ResponseEntity<?> getUserFavouriteAllCuisines(HttpServletRequest request) {
        String emailId = (String) request.getAttribute("emailId");
        return new ResponseEntity<>(userService.getUserFavouriteAllDishes(emailId), HttpStatus.OK);
    }

    @GetMapping("/getUserFavouriteAllRestaurants")
    public ResponseEntity<?> getUserFavouriteAllRestaurants(HttpServletRequest request) {
        String emailId = (String) request.getAttribute("emailId");
        return new ResponseEntity<>(userService.getUserFavouriteAllRestaurants(emailId), HttpStatus.OK);
    }

    @GetMapping("/getUserCartAllDishes")
    public ResponseEntity<?> getUserFavouriteAllDishes(HttpServletRequest request) {
        String emailId = (String) request.getAttribute("emailId");
        return new ResponseEntity<>(userService.getUserCartAllDishes(emailId), HttpStatus.OK);
    }

    @PostMapping("/addDishesToUserFavourite")
    public ResponseEntity<?> addDishesToUserFavourite(@RequestBody Dishes dishes, HttpServletRequest request) {
        String emailId = (String) request.getAttribute("emailId");
        return new ResponseEntity<>(userService.addDishesToUserFavourite(emailId, dishes), HttpStatus.OK);
    }

    @PostMapping("/addDishesToUserCart")
    public ResponseEntity<?> addDishesToUserCart(@RequestBody Dishes dishes, HttpServletRequest request) {
        String emailId = (String) request.getAttribute("emailId");
        return new ResponseEntity<>(userService.addDishesToUserCart(emailId, dishes), HttpStatus.OK);
    }

    @PostMapping("/addRestaurantToUserFavourite")
    public ResponseEntity<?> addRestaurantToUserFavorite(@RequestBody Restaurant restaurant, HttpServletRequest request) {
        String emailId = (String) request.getAttribute("emailId");
        return new ResponseEntity<>(userService.addRestaurantToUserFavourite(emailId, restaurant), HttpStatus.OK);

    }

    @PostMapping("/deleteDishesFromUserFavourite")
    public ResponseEntity<?> deleteCuisineFromUserWishlist(@RequestBody Dishes dishes, HttpServletRequest request) {
        String emailId = (String) request.getAttribute("emailId");
        return new ResponseEntity<>(userService.deleteDishFromUserFavourite(emailId, dishes)
                , HttpStatus.OK);
    }

    @PostMapping("/deleteRestaurantFromUserFavourite")
    public ResponseEntity<?> deleteRestaurantFromUserFavourite(@RequestBody Restaurant restaurant, HttpServletRequest request) {
        String emailId = (String) request.getAttribute("emailId");
        userService.deleteRestaurantFromUserFavourite(emailId, restaurant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/deleteDishesFromUserCart")
    public ResponseEntity<?> deleteDishFromUserCart(@RequestBody Dishes dishes, HttpServletRequest request) {
        String emailId = (String) request.getAttribute("emailId");
        userService.deleteDishFromUserCart(emailId, dishes);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/upadteUserAddress")
    public ResponseEntity<?> upadteUserAddress(@RequestBody User user) {
        return new ResponseEntity<>(userService.updateAddress(user), HttpStatus.OK);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderServiceimpl.addOrder(order), HttpStatus.OK);
    }

    @GetMapping("/getOrderDetails/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable String orderId) {
        return new ResponseEntity<>(orderServiceimpl.getOrderDetails(orderId), HttpStatus.OK);
    }

    @DeleteMapping("/cancelOrder/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable String orderId) {
        return new ResponseEntity<>(orderServiceimpl.cancelOrder(orderId), HttpStatus.OK);
    }

    @GetMapping("/getAllOrders/{orderId}")
    public ResponseEntity<?> getAllOrders(@PathVariable String orderId) {
        return new ResponseEntity<>(orderServiceimpl.getAllOrder(), HttpStatus.OK);
    }
}