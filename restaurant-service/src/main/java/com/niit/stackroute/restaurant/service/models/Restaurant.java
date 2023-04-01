package com.niit.stackroute.restaurant.service.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Data
@Document
@Getter
@Setter
public class Restaurant {

    @Id
    private String restaurantId;
    private String restaurantName, location;
    private Set<Dishes> dishesSet;


    public Restaurant() {
        dishesSet = new HashSet<>();
    }

}
