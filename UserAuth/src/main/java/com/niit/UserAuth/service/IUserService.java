package com.niit.UserAuth.service;

import com.niit.UserAuth.domain.User;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.exception.UserAlreadyExistException;
import com.niit.UserAuth.exception.UserDoesNotFoundException;

import java.util.List;

public interface IUserService {

    public User addUser(User user) throws UserAlreadyExistException;

    public User loginCheck(String email, String password) throws InvalidCredentialsException;

    public List<User> getAllUser();

    public User updateUser(User user) throws UserDoesNotFoundException;
}
