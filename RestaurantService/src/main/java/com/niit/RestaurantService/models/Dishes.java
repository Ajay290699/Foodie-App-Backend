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
    private String dishName;  //veg-pizza
    private String type;        //veg
    private double dishPrice;      //50
    private String dishImage;


}
