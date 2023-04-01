package com.niit.UserService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Restuarant {
    @Id
    private String restaurantId;
    private String restaurantName;
    private String restaurantImage;
    private List<Cuisines> dishes;
    private Address address;
    private String emailId;
    private List<Order> orders;
}
