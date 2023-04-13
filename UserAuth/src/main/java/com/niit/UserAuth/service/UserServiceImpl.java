package com.niit.UserAuth.service;

import com.niit.UserAuth.domain.User;
import com.niit.UserAuth.domain.UserDto;
import com.niit.UserAuth.domain.UserSignUp;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.exception.UserAlreadyExistException;
import com.niit.UserAuth.proxy.UserProxy;
import com.niit.UserAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {


    UserRepository userRepository;

//    @Autowired
//    MailProducer mailProducer;


    UserProxy userProxy;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

    @Override
    public User userSignUp(UserSignUp userSignUp) throws UserAlreadyExistException {
        if (userRepository.findById(userSignUp.getEmail()).isEmpty()) {
            userProxy.sendDataToRestaurantService(new UserDto(userSignUp.getFirstName(), userSignUp.getLastName(), userSignUp.getEmail(),
                    userSignUp.getBuildingName(), userSignUp.getStreetName(), userSignUp.getFlatNo(), userSignUp.getCity(), userSignUp.getState(),
                    userSignUp.getPincode()));
            User user = userRepository.save(new User(userSignUp.getEmail(), userSignUp.getPassword()));
            return user;
        }
        throw new UserAlreadyExistException();
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

}
