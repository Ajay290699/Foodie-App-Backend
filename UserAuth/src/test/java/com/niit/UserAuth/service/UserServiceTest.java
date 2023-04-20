package com.niit.UserAuth.service;

import com.niit.UserAuth.domain.user.User;
import com.niit.UserAuth.domain.user.UserSignUp;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.exception.UserAlreadyExistException;
import com.niit.UserAuth.proxy.UserProxy;
import com.niit.UserAuth.rabbitMQ.EmailDTO;
import com.niit.UserAuth.rabbitMQ.MailProducer;
import com.niit.UserAuth.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProxy userProxy;

    @Mock
    private MailProducer mailProducer;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    private UserSignUp userSignUp;

    private EmailDTO emailDTO;
    private List<User> userList;

    @BeforeEach
    void setUp() {
        user = new User("ajay@gmail.com", "Pass@123");
        userSignUp = new UserSignUp();
        userSignUp.setEmail("ajay@gmail.com");
        userSignUp.setFirstName("ajay");
        userSignUp.setLastName("chavan");
        userSignUp.setMobileNo("123456799");
        userSignUp.setPassword("Pass@123");
        userList = new ArrayList<User>();
        this.userList.add(user);

        emailDTO = new EmailDTO("ajay@gmail.com", "you have Successfully registered to Foodie-App", "USER REGISTRATION SUCCESSFUL");
    }

    @AfterEach
    void tearDown() {
        user = null;
        userSignUp = null;
    }

    @Test
    @DisplayName("User Registered")
    void userRegistration() throws UserAlreadyExistException {

        when(userRepository.findById(user.getEmail())).thenReturn(ofNullable(null));
        when(userProxy.sendDataToRestaurantService(any())).thenReturn(any());
        when(userRepository.save(user)).thenReturn(user);
        doNothing().when(mailProducer).sendMailDtoToQueue(emailDTO);
        assertEquals(user, userService.userRegistration(userSignUp));
        verify(userRepository, times(1)).save(any());
        verify(userRepository, times(1)).findById(any());
    }

    @Test
    void userRegistrationFailure() {
        when(userRepository.findById(user.getEmail())).thenReturn(ofNullable(user));
        assertThrows(UserAlreadyExistException.class, () -> userService.userRegistration(userSignUp));
    }

    @Test
    @DisplayName("User log in successfully")
    public void userLogIn() throws InvalidCredentialsException {
        when(userRepository.findById(user.getEmail())).thenReturn(ofNullable(user));
        assertEquals(user, userService.loginCheck(user.getEmail(), user.getPassword()));
        verify(userRepository, times(2)).findById(user.getEmail());
    }

    @Test
    void userLoginFailure() {
        when(userRepository.findById(user.getEmail())).thenReturn(ofNullable(null));
        assertThrows(InvalidCredentialsException.class, () -> userService.loginCheck(user.getEmail(), user.getPassword()));
    }
}
