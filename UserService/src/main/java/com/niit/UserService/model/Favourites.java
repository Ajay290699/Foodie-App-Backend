package com.niit.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Favourites {

    @Id
    private String email;
    private Set<Dishes> dishes;
    private Set<Restaurant> restaurants;

    public void addDishes(Dishes dishes1) {

        if (Objects.isNull(dishes)) {
            dishes = new HashSet<>();
        }
        dishes.add(dishes1);
    }

    public void addRestaurant(Restaurant restaurant1) {

        if (Objects.isNull(restaurants)) {
            restaurants = new HashSet<>();
        }
        restaurants.add(restaurant1);
    }
}

