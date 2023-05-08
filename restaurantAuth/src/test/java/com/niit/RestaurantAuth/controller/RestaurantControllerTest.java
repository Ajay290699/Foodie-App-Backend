package com.niit.RestaurantAuth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.RestaurantAuth.exception.EmailAlreadyRegistered;
import com.niit.RestaurantAuth.exception.InvalidCredentialsException;
import com.niit.RestaurantAuth.models.RestaurantOwner;
import com.niit.RestaurantAuth.services.RestaurantServiceImpl;
import com.niit.RestaurantAuth.services.tokenService.SecurityTokenGeneratorRestaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @Mock
    private RestaurantServiceImpl restaurantService;

    @InjectMocks
    private RestOwnerController restOwnerController;

    @Autowired
    private MockMvc mockMvc;

    private RestaurantOwner restaurantOwner;

    @Mock
    private SecurityTokenGeneratorRestaurant securityTokenGeneratorRestaurant;

    private Map<String, String> result;

    private static String jsToString(Object ob) {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }

    @BeforeEach
    void setUp() {
        restaurantOwner = new RestaurantOwner("restaurant@gmail.com", "restaurant", "resta@123");
        result = new HashMap<>();
        result.put("token", "abcd");
        result.put("message", "Restaurant Owner Login Successfull");
        result.put("restaurantOwner_Email", restaurantOwner.getEmailId());
        mockMvc = MockMvcBuilders.standaloneSetup(restOwnerController).build();
    }

    @AfterEach
    void tearDown() {
        restaurantOwner = null;
    }

    @Test
    void restaurantOwnerRegistrationSuccess() throws Exception {
        Mockito.when(restaurantService.signUpOwner(restaurantOwner)).thenReturn(restaurantOwner);
        mockMvc.perform(MockMvcRequestBuilders.post("/owner-auth/sign-in").contentType(APPLICATION_JSON).content(jsToString(restaurantOwner)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void restaurantOwnerRegistrationFailure() throws Exception {
        Mockito.when(restaurantService.signUpOwner(any())).thenThrow(EmailAlreadyRegistered.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/owner-auth/sign-in").contentType(APPLICATION_JSON).content(jsToString(restaurantOwner)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(restaurantService, times(1)).signUpOwner(any());
    }

    @Test
    void restaurantOwnerLoginSuccess() throws Exception {
        Mockito.when(restaurantService.restaurantOwnerLogin(restaurantOwner)).thenReturn(restaurantOwner);
        Mockito.when(securityTokenGeneratorRestaurant.tokenGeneration(restaurantOwner)).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.post("/owner-auth/login").contentType(APPLICATION_JSON).content(jsToString(restaurantOwner)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(restaurantService, times(1)).restaurantOwnerLogin(restaurantOwner);
    }

    @Test
    void restaurantOwnerLoginFailure() throws Exception {
        Mockito.when(restaurantService.restaurantOwnerLogin(any())).thenThrow(InvalidCredentialsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/owner-auth/login").contentType(APPLICATION_JSON).content(jsToString(restaurantOwner)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(restaurantService, times(1)).restaurantOwnerLogin(any());
    }
}
