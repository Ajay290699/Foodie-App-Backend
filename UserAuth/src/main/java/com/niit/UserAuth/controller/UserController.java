package com.niit.UserAuth.controller;

import com.niit.UserAuth.domain.user.ImageModel;
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
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/app/v1")
public class UserController {

    private IUserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    //    @Value("${project.images}")
    private String path = "UserAuth/src/main/resources/Image";

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
        return new ResponseEntity<>(userService.userRegistration(userSignUp), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody User user) throws InvalidCredentialsException {
        User result = userService.loginCheck(user.getEmail(), user.getPassword());
        if (result != null) {
            Map<String, String> map = securityTokenGenerator.generateToken(result);
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

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("image") MultipartFile image) {


        String fileName = null;
        try {
            fileName = this.userService.uploadImage(path, image);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        ImageModel imageModel = new ImageModel(fileName, "Image is sucessfully uploaded");

        return new ResponseEntity<>(imageModel, HttpStatus.CREATED);

    }

    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void servefile(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream stream = this.userService.getImage(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(stream, response.getOutputStream());
    }
}
