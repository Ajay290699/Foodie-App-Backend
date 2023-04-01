package com.niit.stackroute.restaurant.service.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Dishes {

    @Id
    private String dishName;
    private String type;
    private int dishPrice;


}
