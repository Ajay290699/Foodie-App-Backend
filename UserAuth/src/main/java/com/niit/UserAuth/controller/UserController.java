package com.niit.UserAuth.controller;

import com.niit.UserAuth.domain.user.User;
import com.niit.UserAuth.domain.user.UserSignUp;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.exception.UserAlreadyExistException;
import com.niit.UserAuth.service.IUserService;
import com.niit.UserAuth.service.ImageService;
import com.niit.UserAuth.token.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/app/v1")
public class UserController {

    private IUserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(IUserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @GetMapping("/otp")
    public ResponseEntity<?> getOtp(@RequestBody String email) {
        System.out.println(email);
        return new ResponseEntity<>(userService.sendOTP(email), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserSignUp userSignUp) throws UserAlreadyExistException {
        return new ResponseEntity<>(userService.userRegistration(userSignUp), HttpStatus.OK);
    }

    @GetMapping("/user")
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

    @Autowired
    ImageService service;

    @PostMapping("/file")
    public ResponseEntity<?> uploadImageToFile(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadImageToFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<?> downloadImageFromFile(@PathVariable String fileName) throws IOException {
        byte[] imageData = service.downloadImageFromFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
