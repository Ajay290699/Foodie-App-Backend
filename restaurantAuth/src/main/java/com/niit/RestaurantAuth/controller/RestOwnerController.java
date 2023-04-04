package com.niit.RestaurantAuth.controller;

import com.niit.RestaurantAuth.models.RestaurantOwner;
import com.niit.RestaurantAuth.services.RestaurantAuthService;
import com.niit.RestaurantAuth.services.tokenService.TokenGenInt;
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
    private TokenGenInt tokenGenInt;


    @PostMapping("/sign-in")
    public ResponseEntity<?> signUp(@RequestBody RestaurantOwner restaurantOwner) {
        return new ResponseEntity<>(restaurantAuthService.signUpOwner(restaurantOwner), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RestaurantOwner restaurantOwner) {
        RestaurantOwner ownerCred = restaurantAuthService.login(restaurantOwner.getEmailId(), restaurantOwner.getPassword());
        if (ownerCred != null) {
            return new ResponseEntity<>(tokenGenInt.tokenGeneration(ownerCred), HttpStatus.OK);
        } else
            return new ResponseEntity<>("No user found, please login", HttpStatus.OK);
    }


}
