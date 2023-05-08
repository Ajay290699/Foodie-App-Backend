package com.niit.RestaurantService.service;

import com.niit.RestaurantService.models.Dishes;
import com.niit.RestaurantService.models.Restaurant;
import com.niit.RestaurantService.models.RestaurantOwner;
import com.niit.RestaurantService.repos.DishesRepo;
import com.niit.RestaurantService.repos.OwnerRepo;
import com.niit.RestaurantService.repos.RestaurantRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepo restaurantRepo;


    @Mock
    private OwnerRepo ownerRepo;

    @Mock
    private DishesRepo dishesRepo;

    @InjectMocks
    private RestaurantImpl restaurantService;

    private Dishes dishes;

    private Restaurant restaurant1;

    private List<Restaurant> restaurant;

    private RestaurantOwner restaurantOwner;

    private List<Dishes> dishesSet;

    @BeforeEach
    void setUp() {
        dishesSet = new ArrayList<Dishes>();
        dishes = new Dishes("pizza", "veg", 45);
        dishesSet.add(dishes);
        restaurant = new ArrayList<>();
        restaurant1 = new Restaurant("XYZ Hotel", "mumbai", dishesSet);
        restaurant.add(restaurant1);
        restaurantOwner = new RestaurantOwner("abc@gmail.com", "ABC", restaurant);
    }

    @AfterEach
    void tearDown() {
        restaurantOwner = null;
        restaurant = null;
        dishes = null;
        dishesSet = null;
    }

    @Test
    void addRestaurantOwner() {
        when(ownerRepo.save(restaurantOwner)).thenReturn(restaurantOwner);
        RestaurantOwner savedOwner = restaurantService.addOwner(restaurantOwner);
        assertEquals(restaurantOwner, savedOwner);
    }

    @Test
    void addRestaurant() {
        when(ownerRepo.findById(restaurantOwner.getEmailId())).thenReturn(Optional.ofNullable(restaurantOwner));
        when(restaurantRepo.save(restaurant1)).thenReturn(restaurant1);
        when(ownerRepo.save(restaurantOwner)).thenReturn(restaurantOwner);
        assertEquals(restaurantOwner, restaurantService.addRestaurant(restaurantOwner.getEmailId(), restaurant1));
    }

    @Test
    void addRestaurantFail() {
        when(ownerRepo.findById(restaurantOwner.getEmailId())).thenReturn(Optional.ofNullable(restaurantOwner));
        when(restaurantRepo.save(restaurant1)).thenReturn(restaurant1);
        when(ownerRepo.save(restaurantOwner)).thenReturn(null);
        assertNotEquals(restaurantOwner, restaurantService.addRestaurant(restaurantOwner.getEmailId(), restaurant1));
    }

    @Test
    void addDish() {
        when(restaurantRepo.findById(restaurant1.getRestaurantName())).thenReturn(Optional.ofNullable(restaurant1));
        when(dishesRepo.save(dishes)).thenReturn(dishes);
        restaurant1.addDishes(dishes);
        when(restaurantRepo.save(restaurant1)).thenReturn(restaurant1);

        assertEquals(restaurant1, restaurantService.addDishesToRestaurant(restaurant1.getRestaurantName(), dishes));
    }

    @Test
    void addDishFail() {
        when(restaurantRepo.findById(restaurant1.getRestaurantName())).thenReturn(Optional.ofNullable(restaurant1));
        when(dishesRepo.save(dishes)).thenReturn(dishes);
        restaurant1.addDishes(dishes);
        when(restaurantRepo.save(restaurant1)).thenReturn(restaurant1);

        assertNotEquals(null, restaurantService.addDishesToRestaurant(restaurant1.getRestaurantName(), dishes));
    }
}