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

    private String firstName;
    private String lastname;
    @Id
    private String emailId;
    private String buildingName;
    private String streetName;
    private int flatNO;
    private String city;
    private String state;
    private double pincode;

    //    saumya varaible's'
//    private String emailId;
//    private String firstName, lastName, mobileNumber;
//    private String buildingName;
//    private String streetName;
//    private int flatNo;
//    private String city;
//    private String state;
//    private double pincode;
    private Address address;
    private Favourites favourites;
}