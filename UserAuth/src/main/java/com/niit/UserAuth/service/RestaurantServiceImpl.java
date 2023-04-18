package com.niit.UserAuth.service;

import com.niit.UserAuth.domain.restaurantOwner.RestaurantOwner;
import com.niit.UserAuth.domain.restaurantOwner.RestaurantOwnerDTO;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.proxy.OwnerProxy;
import com.niit.UserAuth.rabbitMQ.EmailDTO;
import com.niit.UserAuth.rabbitMQ.MailProducer;
import com.niit.UserAuth.repository.RestaurantOwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantAuthService {

    @Autowired
    RestaurantAuthService restaurantAuthService;
    @Autowired
    private RestaurantOwnerRepo restaurantOwnerRepo;
    @Autowired
    private OwnerProxy ownerProxy;
    @Autowired
    private MailProducer mailProducer;

    @Override
    public RestaurantOwner signUpOwner(RestaurantOwner restaurantOwner) {
        RestaurantOwnerDTO restaurantOwnerDTO = new RestaurantOwnerDTO(restaurantOwner.getEmail(), restaurantOwner.getOwnerName());
        ResponseEntity<?> response = ownerProxy.sendDataToService(restaurantOwnerDTO);
        mailProducer.sendMailDtoToQueue(new EmailDTO(restaurantOwner.getEmail(), "You Have Successfully Registered To Foodie-App...." +
                " \n Thank You For Using Our Services!!!", "RESTAURANT OWNER REGISTRATION SUCCESSFUL"));

        return restaurantOwnerRepo.save(restaurantOwner);
    }

    @Override
    public RestaurantOwner restaurantOwnerLogin(String email, String password) throws InvalidCredentialsException {
        if (restaurantOwnerRepo.findById(email).isPresent()) {
            RestaurantOwner restaurantOwner = restaurantOwnerRepo.findById(email).get();
            if (restaurantOwner.getPassword().equals(password)) {
                return restaurantOwner;
            } else {
                throw new InvalidCredentialsException();
            }
        } else {
            throw new InvalidCredentialsException();
        }
    }

}
