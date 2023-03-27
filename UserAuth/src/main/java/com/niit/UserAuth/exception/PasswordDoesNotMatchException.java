package com.niit.UserAuth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Password does not matched")
public class PasswordDoesNotMatchException extends Exception {
}
