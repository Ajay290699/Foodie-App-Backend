package com.niit.RestaurantService.controller;

import com.niit.RestaurantService.exceptions.RestaurantAlreadyExistsException;
import com.niit.RestaurantService.models.Dishes;
import com.niit.RestaurantService.models.ImageModel;
import com.niit.RestaurantService.models.Restaurant;
import com.niit.RestaurantService.models.RestaurantOwner;
import com.niit.RestaurantService.repos.RestaurantRepo;
import com.niit.RestaurantService.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@CrossOrigin
@RequestMapping("/restaurant-service")
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    private final String path = "RestaurantService/src/main/resources/restaurantImage";

    private final String path1 = "RestaurantService/src/main/resources/dishImage";

    @Autowired
    private RestaurantRepo restaurantRepo;

    @PostMapping("/add-owner")
    public ResponseEntity<?> addOwner(@RequestBody RestaurantOwner restaurantOwner) {
        return new ResponseEntity<>(restaurantService.addOwner(restaurantOwner), HttpStatus.OK);
    }

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

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("image") MultipartFile image) {


        String fileName = null;
        try {
            fileName = this.restaurantService.uploadImage(path, image);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        ImageModel imageModel = new ImageModel(fileName, "Image is sucessfully uploaded");

        return new ResponseEntity<>(imageModel, HttpStatus.CREATED);

    }

    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void servefile(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream stream = this.restaurantService.getImage(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(stream, response.getOutputStream());
    }

    @PostMapping("/dishUpload")
    public ResponseEntity<?> dishUpload(@RequestParam("image") MultipartFile image) {


        String fileName = null;
        try {
            fileName = this.restaurantService.uploadImage(path1, image);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        ImageModel imageModel = new ImageModel(fileName, "Image is sucessfully uploaded");

        return new ResponseEntity<>(imageModel, HttpStatus.CREATED);

    }

    @GetMapping(value = "/dishImages/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void dishservefile(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream stream = this.restaurantService.getImage(path1, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(stream, response.getOutputStream());
    }
}
