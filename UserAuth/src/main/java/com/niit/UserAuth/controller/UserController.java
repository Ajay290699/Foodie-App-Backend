package com.niit.UserAuth.controller;

import com.niit.UserAuth.domain.User;
import com.niit.UserAuth.domain.UserSignUp;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.exception.UserAlreadyExistException;
import com.niit.UserAuth.service.IUserService;
import com.niit.UserAuth.token.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("app/v1")
public class UserController {

    private IUserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(IUserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserSignUp userSignUp) throws UserAlreadyExistException {
        return new ResponseEntity<>(userService.userSignUp(userSignUp), HttpStatus.CREATED);
    }

    @PostMapping("/user")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<?> loginCheck(@RequestBody User user) throws InvalidCredentialsException {
        User result = userService.loginCheck(user.getEmail(), user.getPassword());
        if (result != null) {
            Map<String, String> map = securityTokenGenerator.tokenGenerator(result);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            throw new InvalidCredentialsException();
        }
    }
}
