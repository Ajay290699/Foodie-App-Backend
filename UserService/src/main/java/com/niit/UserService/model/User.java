package com.niit.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {
    @Id
    private String email;
    private String firstName;
    private String lastname;
    private String mobileNo;
    private String buildingName;
    private String streetName;
    private int flatNo;
    private String city;
    private String state;
    private double pincode;
    private CartItem cartItem;
    private Favourites favourites;
}