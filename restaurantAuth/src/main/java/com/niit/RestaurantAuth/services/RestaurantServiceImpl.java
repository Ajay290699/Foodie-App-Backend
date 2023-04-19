package com.niit.RestaurantAuth.services;

import com.niit.RestaurantAuth.exception.EmailAlreadyRegistered;
import com.niit.RestaurantAuth.exception.InvalidCredentialsException;
import com.niit.RestaurantAuth.models.RestaurantOwner;
import com.niit.RestaurantAuth.models.RestaurantOwnerDTO;
import com.niit.RestaurantAuth.models.proxy.OwnerProxy;
import com.niit.RestaurantAuth.rabbitMQ.EmailDTO;
import com.niit.RestaurantAuth.rabbitMQ.MailProducer;
import com.niit.RestaurantAuth.repository.RestaurantOwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantAuthService {

    @Autowired
    private RestaurantAuthService restaurantAuthService;
    @Autowired
    private RestaurantOwnerRepo restaurantOwnerRepo;
    @Autowired
    private OwnerProxy ownerProxy;
    @Autowired
    private MailProducer mailProducer;

    @Autowired
    public RestaurantServiceImpl(RestaurantAuthService restaurantAuthService, RestaurantOwnerRepo restaurantOwnerRepo, OwnerProxy ownerProxy, MailProducer mailProducer) {
        this.restaurantAuthService = restaurantAuthService;
        this.restaurantOwnerRepo = restaurantOwnerRepo;
        this.ownerProxy = ownerProxy;
        this.mailProducer = mailProducer;
    }

    @Override
    public RestaurantOwner signUpOwner(RestaurantOwner restaurantOwner) throws EmailAlreadyRegistered {
        if (restaurantOwnerRepo.findById(restaurantOwner.getEmail()).isPresent()) throw new EmailAlreadyRegistered();
        RestaurantOwnerDTO restaurantOwnerDTO = new RestaurantOwnerDTO(restaurantOwner.getEmail(), restaurantOwner.getOwnerName());
        ResponseEntity<?> response = ownerProxy.sendDataToService(restaurantOwnerDTO);
        mailProducer.sendMailDtoToQueue(new EmailDTO(restaurantOwner.getEmail(), "You Have Successfully Registered To Foodie-App...." +
                " \n Thank You For Using Our Services!!!", "RESTAURANT OWNER REGISTRATION SUCCESSFUL"));

        return restaurantOwnerRepo.save(restaurantOwner);
    }

    @Override
    public RestaurantOwner restaurantOwnerLogin(RestaurantOwner restaurantOwner) throws InvalidCredentialsException {
        RestaurantOwner restaurantOwner1 = restaurantOwnerRepo.findByEmailIdAndPassword(restaurantOwner.getEmail(), restaurantOwner.getPassword());
        if (restaurantOwner1 == null) throw new InvalidCredentialsException();
        else return restaurantOwner1;
//        if (restaurantOwnerRepo.findById(email).isPresent()) {
//            RestaurantOwner restaurantOwner = restaurantOwnerRepo.findById(email).get();
//            if (restaurantOwner.getPassword().equals(password)) {
//                return restaurantOwner;
//            } else {
//                throw new InvalidCredentialsException();
//            }
//        } else {
//            throw new InvalidCredentialsException();
//        }
    }

}
