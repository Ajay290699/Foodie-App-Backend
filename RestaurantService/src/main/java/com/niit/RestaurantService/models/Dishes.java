package com.niit.RestaurantService.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Dishes {


    @Id
    private String dishName;
    private String type;
    private double dishPrice;
    private String dishImage;


}
