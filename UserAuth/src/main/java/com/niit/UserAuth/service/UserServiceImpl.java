package com.niit.UserAuth.service;

import com.niit.UserAuth.domain.User;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.exception.UserAlreadyExistException;
import com.niit.UserAuth.exception.UserDoesNotFoundException;
import com.niit.UserAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) throws UserAlreadyExistException {
        if (userRepository.findById(user.getEmail()).isEmpty()) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User loginCheck(String email, String password) throws InvalidCredentialsException {
        if (userRepository.findById(email).isPresent()) {
            User user = userRepository.findById(email).get();
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                throw new InvalidCredentialsException();
            }
        } else {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) throws UserDoesNotFoundException {
        if (userRepository.findById(user.getEmail()).isEmpty()) {
            return null;
        }
        User temp = userRepository.findById(user.getEmail()).get();
        temp.setFirstName(user.getFirstName());
        temp.setLastname(user.getLastname());
        temp.setMobileNo(user.getMobileNo());
        temp.setPassword(user.getPassword());
//        temp.setAddress(user.getAddress());
//        temp.setAddress(user.setAddress(user.getAddress()));
        return userRepository.save(temp);
    }
}
