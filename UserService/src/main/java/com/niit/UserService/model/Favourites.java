package com.niit.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Favourites {

    @Id
    private String email;
    private List<Dishes> dishes;
    private List<Restaurant> restaurants;
    public void addDishes(Dishes dishes1) {

        if (Objects.isNull(dishes)) {
            dishes = new ArrayList<>();
        }
       dishes.add(dishes1);
    }

    public void addRestaurant(Restaurant restaurant1) {

        if (Objects.isNull(restaurants)) {
            restaurants = new ArrayList<>();
        }
        restaurants.add(restaurant1);
    }
}

