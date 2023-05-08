package com.niit.RestaurantAuth.services;

import com.niit.RestaurantAuth.exception.EmailAlreadyRegistered;
import com.niit.RestaurantAuth.exception.InvalidCredentialsException;
import com.niit.RestaurantAuth.models.RestaurantOwner;
import com.niit.RestaurantAuth.models.RestaurantOwnerDTO;
import com.niit.RestaurantAuth.models.proxy.OwnerProxy;
import com.niit.RestaurantAuth.rabbitMQ.MailProducer;
import com.niit.RestaurantAuth.repository.RestaurantOwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private JavaMailSender javaMailSender;

    public void sendMail(String receiver, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("foodieapp64@gmail.com");
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);
        javaMailSender.send(simpleMailMessage);
        System.out.println("Mail sent...");
    }

//    @Autowired
//    public RestaurantServiceImpl(RestaurantAuthService restaurantAuthService, RestaurantOwnerRepo restaurantOwnerRepo, OwnerProxy ownerProxy, MailProducer mailProducer) {
//        this.restaurantAuthService = restaurantAuthService;
//        this.restaurantOwnerRepo = restaurantOwnerRepo;
//        this.ownerProxy = ownerProxy;
//        this.mailProducer = mailProducer;
//    }

    @Override
//    @EventListener(ApplicationReadyEvent.class)
    public RestaurantOwner signUpOwner(RestaurantOwner restaurantOwner) throws EmailAlreadyRegistered {
        if (restaurantOwnerRepo.findById(restaurantOwner.getEmailId()).isPresent()) throw new EmailAlreadyRegistered();
        RestaurantOwnerDTO restaurantOwnerDTO = new RestaurantOwnerDTO(restaurantOwner.getEmailId(), restaurantOwner.getOwnerName());
        ResponseEntity<?> response = ownerProxy.sendDataToService(restaurantOwnerDTO);
//        mailProducer.sendMailDtoToQueue(new EmailDTO(restaurantOwner.getEmailId(), "You Have Successfully Registered To Foodie-App...." +
//                " \n Thank You For Using Our Services!!!", "RESTAURANT OWNER REGISTRATION SUCCESSFUL"));
        sendMail(restaurantOwner.getEmailId(), "RESTAURANT OWNER REGISTRATION SUCCESSFUL",
                "Hii " + restaurantOwner.getOwnerName() + " you have been successfully Registered to Foodie-App...");

        return restaurantOwnerRepo.save(restaurantOwner);
    }

    @Override
    public RestaurantOwner restaurantOwnerLogin(RestaurantOwner restaurantOwner) throws InvalidCredentialsException {
        RestaurantOwner restaurantOwner1 = restaurantOwnerRepo.findByEmailIdAndPassword(restaurantOwner.getEmailId(), restaurantOwner.getPassword());
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
