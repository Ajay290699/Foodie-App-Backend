package com.niit.UserAuth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.UserAuth.domain.user.User;
import com.niit.UserAuth.domain.user.UserSignUp;
import com.niit.UserAuth.exception.UserAlreadyExistException;
import com.niit.UserAuth.service.UserServiceImpl;
import com.niit.UserAuth.token.SecurityTokenGenerator;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    private User user;

    private UserSignUp userSignUp;

    @Mock
    private SecurityTokenGenerator securityTokenGenerator;

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
        userSignUp = new UserSignUp("ajay@gmail.com", "ajay", "chavan", "Pass@123", "4563289", "building", "area", "mumbai", "maharashtra", 452, 400777);
        user = new User("ajay@gmail.com", "Pass@123");
        result = new HashMap<>();
        result.put("token", "abcd");
        result.put("message", "user login successful");
        result.put("user_email", user.getEmail());
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    void tearDown() {
        user = null;
        userSignUp = null;
    }

    @Test
    void userRegistrationSuccess() throws Exception {
        Mockito.when(userService.userRegistration(userSignUp)).thenReturn(user);
        mockMvc.perform(post("/app/v1/register").contentType(APPLICATION_JSON).content(jsToString(userSignUp)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void userRegistrationFailure() throws Exception {
        Mockito.when(userService.userRegistration(any())).thenThrow(UserAlreadyExistException.class);
        mockMvc.perform(post("/app/v1/register").contentType(APPLICATION_JSON).contentType(jsToString(userSignUp)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        Mockito.verify(userService, times(1)).userRegistration(any());
    }
}
