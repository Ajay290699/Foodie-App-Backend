package com.niit.UserService.controller;

import com.niit.UserService.model.Address;
import com.niit.UserService.model.User;
import com.niit.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/foodieApp/userService")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

    @RestController
    @RequestMapping("/userService/")
    public class CUserController {

        @GetMapping("/getAllUser")
        public ResponseEntity<?> getAllUser() {
            return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
        }

        @GetMapping("/getUserDetails")
        public ResponseEntity<?> getUserDetails(HttpServletRequest request) {
            String emailId = (String) request.getAttribute("emailId");
            return new ResponseEntity<>(userService.getUserDetails(emailId), HttpStatus.OK);
        }

        @GetMapping("/getUserFavouriteAllCuisines")
        public ResponseEntity<?> getUserFavouriteAllCuisines(HttpServletRequest request) {
            String emailId = (String) request.getAttribute("emailId");
            return new ResponseEntity<>(userService.getUserFavouriteAllCuisines(emailId), HttpStatus.OK);
        }

        @GetMapping("/getUserFavouriteAllRestaurants")
        public ResponseEntity<?> getUserFavouriteAllRestaurants(HttpServletRequest request) {
            String emailId = (String) request.getAttribute("emailId");
            return new ResponseEntity<>(userService.getUserFavouriteAllRestaurants(emailId), HttpStatus.OK);
        }

        @GetMapping("/getUserAddress")
        public ResponseEntity<?> getUserAddress(HttpServletRequest request) {
            String emailId = (String) request.getAttribute("emailId");
            return new ResponseEntity<>(userService.getUserAddress(emailId), HttpStatus.OK);
        }

        @PostMapping("/addCuisinesToUserFavourite")
        public ResponseEntity<?> addCuisinesToUserFavourite(@RequestBody String cuisineName, HttpServletRequest request) {
            String emailId = (String) request.getAttribute("emailId");
            return new ResponseEntity<>(userService.addCuisinesToUserFavourite(emailId, cuisineName), HttpStatus.OK);

        }

        @PostMapping("/addRestaurantToUserFavourite/{restaurantId}")
        public ResponseEntity<?> addRestaurantToUserWishlist(@RequestBody String restaurantName, @PathVariable String restaurantId, HttpServletRequest request) {
            String emailId = (String) request.getAttribute("emailId");
            return new ResponseEntity<>(userService.addRestaurantToUserFavourite(emailId, restaurantName, restaurantId), HttpStatus.OK);

        }

        @PutMapping("/updateUserAddress/{orderId}")
        public ResponseEntity<?> updateUserAddress(@PathVariable String orderPlace, @RequestBody Address address, HttpServletRequest request) throws IOException {
            String emailId = (String) request.getAttribute("emailId");
            System.out.println(emailId);
            return new ResponseEntity<>(userService.updateUserAddress(emailId, orderPlace, address), HttpStatus.OK);

        }

        @DeleteMapping("deleteCuisineFromUserWishlist")
        public ResponseEntity<?> deleteCuisineFromUserWishlist(@RequestBody String cuisineName, HttpServletRequest request) {
            String emailId = (String) request.getAttribute("emailId");
            userService.deleteCuisineFromUserFavourite(emailId, cuisineName);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @DeleteMapping("deleteRestaurantFromUserWishlist")
        public ResponseEntity<?> deleteRestaurantFromUserWishlist(@RequestBody String restaurantName, HttpServletRequest request) {
            String emailId = (String) request.getAttribute("emailId");
            userService.deleteRestaurantFromUserFavourite(emailId, restaurantName);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


}
