package com.niit.stackroute.restaurant.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Restaurant is already registered")
public class RestaurantAlreadyExistsException extends Exception {
}
