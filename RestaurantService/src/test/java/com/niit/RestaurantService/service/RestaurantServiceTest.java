package com.niit.RestaurantService.service;

import com.niit.RestaurantService.models.Dishes;
import com.niit.RestaurantService.models.Restaurant;
import com.niit.RestaurantService.models.RestaurantOwner;
import com.niit.RestaurantService.repos.OwnerRepo;
import com.niit.RestaurantService.repos.RestaurantRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static junit.framework.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepo restaurantRepo;


    @Mock
    private OwnerRepo ownerRepo;

    @InjectMocks
    private RestaurantImpl restaurantService;

    private Dishes dishes;

    private Restaurant restaurant;

    private RestaurantOwner restaurantOwner;

    private Set<Dishes> dishesSet;

    @BeforeEach
    void setUp() {
//        Dishes disheSet = new HashSet<Dishes>();
        dishes = new Dishes("pizza", "veg", 45);
        dishesSet.add(dishes);
        restaurant = new Restaurant("XYZ Hotel", "mumbai", dishesSet);
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
//        RestaurantOwner restaurantOwner1 = new RestaurantOwner();
//        restaurantOwner1.setEmailId("res@gmail.com");
//        restaurantOwner1.setOwnerName("restaurant");
//        restaurantOwner1.setRestaurant(restaurant);
        when(restaurantRepo.save(restaurantOwner)).thenReturn(restaurantOwner);
        RestaurantOwner savedOwner = restaurantService.addOwner(restaurantOwner);
        assertEquals(restaurantOwner, savedOwner);
    }

    @Test
    void addRestaurant() {
        when(restaurantRepo.save(restaurant)).thenReturn(restaurant);
        assertEquals();
    }
}
