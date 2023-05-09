package com.niit.RestaurantService.service;

import com.niit.RestaurantService.models.Dishes;
import com.niit.RestaurantService.models.Restaurant;
import com.niit.RestaurantService.models.RestaurantOwner;
import com.niit.RestaurantService.repos.DishesRepo;
import com.niit.RestaurantService.repos.OwnerRepo;
import com.niit.RestaurantService.repos.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantImpl implements RestaurantService {

    @Autowired
    private OwnerRepo ownerRepo;
    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private DishesRepo dishesRepo;

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String filePath = path + File.separator + name;
        File file1 = new File(path);
        if (!file1.exists()) {
            file1.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));
        return name;
    }

    @Override
    public InputStream getImage(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }

    @Override
    public RestaurantOwner addRestaurant(String restaurantOwnerId, Restaurant restaurant) {
        RestaurantOwner owner = ownerRepo.findById(restaurantOwnerId).get();
        Restaurant restaurant1 = restaurantRepo.save(restaurant);
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        owner.setRestaurant(restaurantList);
        return ownerRepo.save(owner);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants;
    }

    RestaurantOwner getRestaurantOwnerById(String restaurantOwnerId) {
        RestaurantOwner restaurantOwner = ownerRepo.findById(restaurantOwnerId).get();
        return restaurantOwner;
    }


    @Override
    public Restaurant addDishesToRestaurant(String restName, Dishes dishes) {
        Restaurant restaurant = restaurantRepo.findById(restName).get();
        Dishes dishes1 = dishesRepo.save(dishes);
//        System.out.println(restaurant);
//        Restaurant restaurant = restaurantOwner.getRestaurant();
        restaurant.addDishes(dishes);
//        restaurant.getDishesSet().add(dishes1);
        return restaurantRepo.save(restaurant);
    }

//    public Dishes addDishes(Dishes dish,String restName){
//        Dishes dishes = dishesRepo.save(dish);
//        if (restaurantRepo.findById(restName).isPresent()){
//            Restaurant restaurant = restaurantRepo.save(dishes)
//        }
//    }

    @Override
    public Restaurant updateRestaurantDetails(String restaurantName, String location) {
        if (restaurantRepo.findById(restaurantName).isPresent()) {
            Restaurant restaurant1 = restaurantRepo.findById(restaurantName).get();
            restaurant1.setRestaurantName(restaurantName);
            restaurant1.setLocation(location);
            return restaurantRepo.save(restaurant1);
        }
        return null;
    }
//    @Override
//    public List<Restaurant> getOwnerRestaurant(String restaurantOwnerId) {
//        Optional<RestaurantOwner> owner = ownerRepo.findById(restaurantOwnerId);
//        if (owner.isPresent()){
//            RestaurantOwner owner1 = owner.get();
//            List<Restaurant> restaurant = restaurantRepo.findByOwner(owner1);
//            return restaurant;
//        }
//        return null;
//    }

    @Override
    public Dishes updateDishDetails(String dishName, String type, double price) {
        if (dishesRepo.findById(dishName).isPresent()) {
            Dishes dishes = dishesRepo.findById(dishName).get();
            dishes.setType(type);
            dishes.setDishPrice(price);
            return dishesRepo.save(dishes);
        }
        return null;
    }

    @Override
    public Dishes deleteDishes(String dishName) {
        Optional<Dishes> dish = dishesRepo.findById(dishName);
        if (dish.isPresent()) {
            Dishes dishes = dish.get();
            dishesRepo.delete(dishes);
            return dishes;
        }
        return null;
    }

    @Override
    public Restaurant deleteRestaurant(String restaurantName) {
        Optional<Restaurant> restaurant = restaurantRepo.findById(restaurantName);
        if (restaurant.isPresent()) {
            Restaurant restaurant1 = restaurant.get();
            restaurantRepo.delete(restaurant1);
            return restaurant1;
        }
        return null;
    }

    @Override
    public List<Dishes> getAllDishes() {
        List<Dishes> dishes = dishesRepo.findAll();
        return dishes;
    }


    @Override
    public RestaurantOwner addOwner(RestaurantOwner restaurantOwner) {
        return ownerRepo.save(restaurantOwner);
    }


}
