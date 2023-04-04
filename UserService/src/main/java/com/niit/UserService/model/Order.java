package com.niit.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private String UserId;
    private String restaurantId, restaurantName;
    private String orderedOn, orderStatus;
    private List<CartItem> CartItems;
    private int totalAmount;
    private Address address;

}