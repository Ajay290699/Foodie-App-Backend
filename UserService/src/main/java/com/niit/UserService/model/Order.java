package com.niit.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Order {
    @Id
    private String orderId;
    private String email;
    private String restaurantName;
    private List<CartItem> CartItems;
    private int totalAmount;
    private String buildingName;
    private String streetName;
    private int flatNo;
    private String city;
    private String state;
    private double pincode;

}