package com.niit.UserAuth.service;

import com.niit.UserAuth.domain.user.User;
import com.niit.UserAuth.domain.user.UserSignUp;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.exception.UserAlreadyExistException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IUserService {

    public User userRegistration(UserSignUp userSignUp) throws UserAlreadyExistException;

    public User loginCheck(String email, String password) throws InvalidCredentialsException;

    public List<User> getAllUser();

    public int generateOTP();

    public int sendOTP(String email);

    String uploadImage(String path, MultipartFile file) throws IOException;

    InputStream getImage(String path, String fileName) throws FileNotFoundException;
}
