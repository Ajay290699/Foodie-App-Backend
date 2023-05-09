package com.niit.RestaurantAuth.service;

import com.niit.RestaurantAuth.exception.EmailAlreadyRegistered;
import com.niit.RestaurantAuth.exception.InvalidCredentialsException;
import com.niit.RestaurantAuth.models.RestaurantOwner;
import com.niit.RestaurantAuth.models.RestaurantOwnerDTO;
import com.niit.RestaurantAuth.models.proxy.OwnerProxy;
import com.niit.RestaurantAuth.rabbitMQ.EmailDTO;
import com.niit.RestaurantAuth.rabbitMQ.MailProducer;
import com.niit.RestaurantAuth.repository.RestaurantOwnerRepo;
import com.niit.RestaurantAuth.services.RestaurantServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    private RestaurantOwner restaurantOwner;

    private RestaurantOwnerDTO restaurantOwnerDTO;

    @Mock
    private RestaurantOwnerRepo restaurantOwnerRepo;

    @Mock
    private OwnerProxy ownerProxy;

    @Mock
    private MailProducer mailProducer;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    private EmailDTO restaurantEmailDTO;

    private List<RestaurantOwner> restaurantOwnerList;

    @BeforeEach
    void setUp() {
        restaurantEmailDTO = new EmailDTO("ajay@gmail.com", "you have Successfully registered to Foodie-App", "RESTAURANT OWNER REGISTRATION SUCCESSFUL");

        restaurantOwner = new RestaurantOwner("restaurant@gmail.com", "restaurant", "resta@123");
        restaurantOwnerList = new ArrayList<RestaurantOwner>();
        restaurantOwnerDTO = new RestaurantOwnerDTO("restaurant@gmail.com", "restaurant");
        this.restaurantOwnerList.add(restaurantOwner);
    }

    @AfterEach
    void tearDown() {
        restaurantOwner = null;
        restaurantOwnerDTO = null;
    }

    @Test
    public void restaurantOwnerLogInSuccess() throws InvalidCredentialsException {
        when(restaurantOwnerRepo.findByEmailIdAndPassword(restaurantOwner.getEmailId(), restaurantOwner.getPassword())).thenReturn(restaurantOwner);
        assertEquals(restaurantOwner, restaurantService.restaurantOwnerLogin(restaurantOwner));
        verify(restaurantOwnerRepo, times(1)).findByEmailIdAndPassword(restaurantOwner.getEmailId(), restaurantOwner.getPassword());
    }

    @Test
    public void restaurantOwnerLogInFailure() throws InvalidCredentialsException {
        when(restaurantOwnerRepo.findByEmailIdAndPassword(restaurantOwner.getEmailId(), restaurantOwner.getPassword())).thenReturn(null);
        assertThrows(InvalidCredentialsException.class, () -> restaurantService.restaurantOwnerLogin(restaurantOwner));
    }

//    @Test
//    void restaurantOwnerRegisterSuccess() throws EmailAlreadyRegistered {
//        when(restaurantOwnerRepo.findById(restaurantOwner.getEmailId())).thenReturn(ofNullable(null));
//        when(ownerProxy.sendDataToService(any())).thenReturn(any());
//        when(restaurantOwnerRepo.save(restaurantOwner)).thenReturn(restaurantOwner);
//        doNothing().when(mailProducer).sendMailDtoToQueue(restaurantEmailDTO);
//        assertEquals(restaurantOwner, restaurantService.signUpOwner(restaurantOwner));
//        verify(restaurantOwnerRepo, times(1)).save(any());
//        verify(restaurantOwnerRepo, times(2)).findById(any());
//    }

    @Test
    void restaurantOwnerRegisterFailure() {
        when(restaurantOwnerRepo.findById(restaurantOwner.getEmailId())).thenReturn(ofNullable(restaurantOwner));
        assertThrows(EmailAlreadyRegistered.class, () -> restaurantService.signUpOwner(restaurantOwner));
    }
}
