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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    MailProducer mailProducer;


    @Autowired
    UserProxy userProxy;

    @Autowired
    private JavaMailSender javaMailSender;


//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
//        this.userRepository = userRepository;
//        this.userProxy = userProxy;
//    }

    public void sendMail(String receiver, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("ajayc9838@gmail.com");
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);
        javaMailSender.send(simpleMailMessage);
        System.out.println("Mail sent...");
    }

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String filePath = path + File.separator + name;
        File file1 = new File(path);
        if (!file1.exists()) {
            file1.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));
        return name;
    }

    @Override
    public InputStream getImage(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }

    @Override
    public User userRegistration(UserSignUp userSignUp) throws UserAlreadyExistException {
        if (userRepository.findById(userSignUp.getEmail()).isEmpty()) {
            userProxy.sendDataToRestaurantService(new UserDto(userSignUp.getFirstName(), userSignUp.getLastName(), userSignUp.getEmail(),
                    userSignUp.getBuildingName(), userSignUp.getStreetName(), userSignUp.getMobileNo(), userSignUp.getFlatNo(), userSignUp.getCity(), userSignUp.getState(),
                    userSignUp.getPincode(), userSignUp.getPi()));
            User user = userRepository.save(new User(userSignUp.getEmail(), userSignUp.getPassword()));
//            mailProducer.sendMailDtoToQueue(new EmailDTO(user.getEmail(), "You Have Successfully Registered To Foodie-App...." +
//                    " \n Thank You For Using Our Services!!!", "USER REGISTRATION SUCCESSFUL"));
            sendMail(userSignUp.getEmail(), "USER REGISTRATION SUCCESSFUL"
                    , "Hii " + userSignUp.getFirstName() + " you have successfully registered to Foddie-App \nWlcome to Foodie-App");
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
