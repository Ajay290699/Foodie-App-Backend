package com.niit.UserService.controller;

import com.niit.UserService.model.Order;
import com.niit.UserService.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/orderService")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceimpl;

    @PostMapping("/addOrder")
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderServiceimpl.addOrder(order), HttpStatus.OK);
    }

    @GetMapping("/getOrderDetails/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable String orderId) {
        return new ResponseEntity<>(orderServiceimpl.getOrderDetails(orderId), HttpStatus.OK);
    }

    @DeleteMapping("/cancelOrder/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable String orderId) {
        return new ResponseEntity<>(orderServiceimpl.cancelOrder(orderId), HttpStatus.OK);
    }

    @GetMapping("/getAllOrders/{orderId}")
    public ResponseEntity<?> getAllOrders(@PathVariable String orderId) {
        return new ResponseEntity<>(orderServiceimpl.getAllOrder(), HttpStatus.OK);
    }


}
