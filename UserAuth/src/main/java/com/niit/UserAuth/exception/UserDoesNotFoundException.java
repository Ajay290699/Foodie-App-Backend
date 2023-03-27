package com.niit.UserAuth.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User does not found")
public class UserDoesNotFoundException extends Exception {
}
