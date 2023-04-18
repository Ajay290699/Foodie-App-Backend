package com.niit.UserAuth.service;

import com.niit.UserAuth.domain.user.User;
import com.niit.UserAuth.domain.user.UserDto;
import com.niit.UserAuth.domain.user.UserSignUp;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.exception.UserAlreadyExistException;
import com.niit.UserAuth.proxy.UserProxy;
import com.niit.UserAuth.rabbitMQ.EmailDTO;
import com.niit.UserAuth.rabbitMQ.MailProducer;
import com.niit.UserAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    MailProducer mailProducer;


    UserProxy userProxy;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

    @Override
    public User userRegistration(UserSignUp userSignUp) throws UserAlreadyExistException {
        if (userRepository.findById(userSignUp.getEmail()).isEmpty()) {
            userProxy.sendDataToRestaurantService(new UserDto(userSignUp.getFirstName(), userSignUp.getLastName(), userSignUp.getEmail(),
                    userSignUp.getBuildingName(), userSignUp.getStreetName(), userSignUp.getMobileNo(), userSignUp.getFlatNo(), userSignUp.getCity(), userSignUp.getState(),
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

    @Override
    public int generateOTP() {
        int otp = ThreadLocalRandom.current().nextInt(1000, 9999);
        return otp;
    }

    @Override
    public int sendOTP(String email) {
        int otp = generateOTP();
        String mailBodyWithOtp = "Foodie-App OTP Verification : " + otp;
        EmailDTO emailDTO = new EmailDTO(email, mailBodyWithOtp, "OTP");
        mailProducer.sendMailDtoToQueue(emailDTO);
        System.out.println(emailDTO);
        return otp;
    }

}
