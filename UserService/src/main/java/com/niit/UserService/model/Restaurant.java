package com.niit.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {

    private String restaurantName;   //dominos

    private String location;        //pune
    private Set<Dishes> dishesSet;
//    @Id
//    private String restaurantId;
//    private String restaurantName;
//    private String restaurantImage;
//    private List<Dishes> dishes;
//    private String emailId;
//    private List<Order> orders;
}
