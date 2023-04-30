package com.niit.UserService.service;

import com.niit.UserService.model.Order;

import java.util.List;

public interface OrderService {

    public Order addOrder(Order order);

    public Order getOrderDetails(String orderId);

    public boolean cancelOrder(String orderId);

    public List<Order> getAllOrder();
}
