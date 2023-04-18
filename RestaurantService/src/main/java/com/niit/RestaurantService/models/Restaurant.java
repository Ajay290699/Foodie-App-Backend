package com.niit.RestaurantService.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@AllArgsConstructor
@Data
@Document
@Getter
@Setter
public class Restaurant {


    //    private String restaurantId;
    @Id
    private String restaurantName;   //dominos

    private String location;        //pune
    private Set<Dishes> dishesSet;  //veg,non-veg

//    domino's, KFC, MCdonald's, Square pizza, darjeeling momo's,

}
