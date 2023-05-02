package com.niit.UserService.service;

import com.niit.UserService.model.Order;
import com.niit.UserService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {

        return orderRepository.insert(order);
    }

    @Override
    public Order getOrderDetails(String orderId) {
        return orderRepository.findById(orderId).get();
    }

    @Override
    public boolean cancelOrder(String orderId) {
        orderRepository.deleteById(orderId);
        return true;
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }
}
