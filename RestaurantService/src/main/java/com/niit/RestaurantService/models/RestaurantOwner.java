package com.niit.RestaurantService.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwner {
    @Id
    private String emailId;
    private String ownerName;
    private List<Restaurant> restaurant;

}
