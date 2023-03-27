package com.niit.UserAuth.service;

import com.niit.UserAuth.domain.User;
import com.niit.UserAuth.exception.UserAlreadyExistException;
import com.niit.UserAuth.exception.UserDoesNotFoundException;

import java.util.List;

public interface IUserService {

    public User addUser(User user) throws UserAlreadyExistException;

    public User userLogin(String email, String password) throws UserDoesNotFoundException;

    public List<User> getAllUser();
}
