package com.niit.RestaurantAuth.controller;

import com.niit.RestaurantAuth.exception.EmailAlreadyRegistered;
import com.niit.RestaurantAuth.exception.InvalidCredentialsException;
import com.niit.RestaurantAuth.models.RestaurantOwner;
import com.niit.RestaurantAuth.services.RestaurantServiceImpl;
import com.niit.RestaurantAuth.services.tokenService.SecurityTokenGeneratorRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/owner-auth")
@RestController
public class RestOwnerController {

    private RestaurantServiceImpl restaurantAuthService;


    private SecurityTokenGeneratorRestaurant securityTokenGeneratorRestaurant;

    @Autowired
    public RestOwnerController(RestaurantServiceImpl restaurantAuthService, SecurityTokenGeneratorRestaurant securityTokenGeneratorRestaurant) {
        this.restaurantAuthService = restaurantAuthService;
        this.securityTokenGeneratorRestaurant = securityTokenGeneratorRestaurant;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signUp(@RequestBody RestaurantOwner restaurantOwner) throws EmailAlreadyRegistered {
        return new ResponseEntity<>(restaurantAuthService.signUpOwner(restaurantOwner), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RestaurantOwner restaurantOwner) throws InvalidCredentialsException {
        RestaurantOwner restaurantOwner1 = restaurantAuthService.restaurantOwnerLogin(restaurantOwner);
        if (restaurantOwner1 != null) {
            return new ResponseEntity<>(securityTokenGeneratorRestaurant.tokenGeneration(restaurantOwner1), HttpStatus.OK);
        } else {
            throw new InvalidCredentialsException();
        }
    }
//        RestaurantOwner ownerCred = restaurantAuthService.restaurantOwnerLogin(restaurantOwner.getEmail(), restaurantOwner.getPassword());
//        if (ownerCred != null) {
//            return new ResponseEntity<>(securityTokenGeneratorRestaurant.tokenGeneration(ownerCred), HttpStatus.OK);
//        } else
//            return new ResponseEntity<>("No user found, please login", HttpStatus.OK);
//    }


}
