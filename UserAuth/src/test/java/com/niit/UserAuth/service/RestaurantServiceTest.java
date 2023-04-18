package com.niit.UserAuth.service;

import com.niit.UserAuth.domain.restaurantOwner.RestaurantOwner;
import com.niit.UserAuth.domain.restaurantOwner.RestaurantOwnerDTO;
import com.niit.UserAuth.exception.InvalidCredentialsException;
import com.niit.UserAuth.proxy.OwnerProxy;
import com.niit.UserAuth.rabbitMQ.EmailDTO;
import com.niit.UserAuth.repository.RestaurantOwnerRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
//          when(restaurantOwnerRepo.findByEmailIdAndPassword(restaurantOwner.getEmail(),restaurantOwner.getPassword())).thenReturn(restaurantOwner);
//          assertThrows(InvalidCredentialsException.class,()->restaurantService.restaurantOwnerLogin(restaurantOwner.getEmail(),restaurantOwner.getPassword()));
        Mockito.when(restaurantOwnerRepo.findById(restaurantOwner.getEmail())).thenReturn(ofNullable(restaurantOwner));
        assertEquals(restaurantOwner, restaurantService.restaurantOwnerLogin(restaurantOwner.getEmail(), restaurantOwner.getPassword()));
        verify(restaurantOwnerRepo, times(2)).findById(restaurantOwner.getEmail());
    }

    @Test
    public void restaurantOwnerLogInFailure() throws InvalidCredentialsException {
        when(restaurantOwnerRepo.findByEmailIdAndPassword(restaurantOwner.getEmail(), restaurantOwner.getPassword())).thenReturn(null);
        assertThrows(InvalidCredentialsException.class, () -> restaurantService.restaurantOwnerLogin(restaurantOwner.getEmail(), restaurantOwner.getPassword()));
//        when(restaurantOwnerRepo.findById(restaurantOwner.getEmail())).thenReturn(null);
//        assertThrows(InvalidCredentialsException.class,()->restaurantService.signUpOwner(restaurantOwner.getPassword(), ));
    }
}
