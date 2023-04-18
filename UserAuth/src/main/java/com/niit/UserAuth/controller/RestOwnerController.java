package com.niit.UserAuth.controller;

import com.niit.UserAuth.domain.restaurantOwner.RestaurantOwner;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.service.RestaurantAuthService;
import com.niit.UserAuth.token.SecurityTokenGeneratorRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/owner-auth")
@RestController
public class RestOwnerController {

    @Autowired
    private RestaurantAuthService restaurantAuthService;

    @Autowired
    private SecurityTokenGeneratorRestaurant securityTokenGeneratorRestaurant;


    @PostMapping("/sign-in")
    public ResponseEntity<?> signUp(@RequestBody RestaurantOwner restaurantOwner) {
        return new ResponseEntity<>(restaurantAuthService.signUpOwner(restaurantOwner), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RestaurantOwner restaurantOwner) throws InvalidCredentialsException {
        RestaurantOwner ownerCred = restaurantAuthService.restaurantOwnerLogin(restaurantOwner.getEmail(), restaurantOwner.getPassword());
        if (ownerCred != null) {
            return new ResponseEntity<>(securityTokenGeneratorRestaurant.tokenGeneration(ownerCred), HttpStatus.OK);
        } else
            return new ResponseEntity<>("No user found, please login", HttpStatus.OK);
    }


}
