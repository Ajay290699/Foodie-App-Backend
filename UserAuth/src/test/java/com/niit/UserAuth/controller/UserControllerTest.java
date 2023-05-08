package com.niit.UserAuth.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.UserAuth.domain.user.User;
import com.niit.UserAuth.domain.user.UserSignUp;
import com.niit.UserAuth.service.UserServiceImpl;
import com.niit.UserAuth.token.SecurityTokenGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserServiceImpl cUserService;
    @InjectMocks
    private UserController cUserController;
    @Autowired
    private MockMvc mockMvc;

    private User user;
    private UserSignUp userSignUp;

    @Mock
    private SecurityTokenGenerator iUserToken;


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
        userSignUp = new UserSignUp("user123@gmail.com", "ajay", "chavan", "1234567890", "user", "", "", "", "", 00, 678, "");
        user = new User("user123@gmail.com", "23456");
        result = new HashMap<>();
        result.put("token", "abcd");
        result.put("message", "user login success");
        result.put("user_email", user.getEmail());
        mockMvc = MockMvcBuilders.standaloneSetup(cUserController).build();
    }

    @AfterEach
    void clear() {
        this.user = null;
        this.userSignUp = null;
    }

    @Test
    public void userLogInSuccess() throws Exception {
        when(cUserService.loginCheck(user.getEmail(), user.getPassword())).thenReturn(user);
        when(iUserToken.generateToken(user)).thenReturn(result);
        mockMvc.perform(post("/app/v1/login").contentType(APPLICATION_JSON).content(jsToString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(cUserService, times(1)).loginCheck(user.getEmail(), user.getPassword());
    }
}