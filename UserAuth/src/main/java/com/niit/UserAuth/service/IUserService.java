package com.niit.UserAuth.service;

import com.niit.UserAuth.domain.User;
import com.niit.UserAuth.domain.UserSignUp;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.exception.UserAlreadyExistException;

import java.util.List;

public interface IUserService {

    public User userSignUp(UserSignUp userSignUp) throws UserAlreadyExistException;

    public User loginCheck(String email, String password) throws InvalidCredentialsException;

    public List<User> getAllUser();

    public int generateOTP();

    public int sendOTP(String email);
}
