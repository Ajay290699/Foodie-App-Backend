package com.niit.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dishes {
    private String dishName;  //veg-pizza
    private String type;        //veg
    private int dishPrice;
    private String dishImage;
    private int quantity;

}