package com.niit.RestaurantService.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Data
@Document
@Getter
@Setter
public class Restaurant {

    @Id
    private String restaurantName;   //dominos

    private String location;        //pune
    private String resImage;
    private List<Dishes> dishesSet;

    public void addDishes(Dishes dishes1) {

        if (Objects.isNull(dishesSet)) {
            dishesSet = new ArrayList<>();
        }
        dishesSet.add(dishes1);
    }


}
