package com.niit.UserService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private CartItem cartItem;

    private FoodItems foodItems;

    private User user;

    private Favourites favourites;

    private List<CartItem> list1;


    private String jwt;

    //    @Test
//    void restaurantOwnerRegistrationFailure() throws Exception {
//        Mockito.when(restaurantService.signUpOwner(any())).thenThrow(EmailAlreadyRegistered.class);
//        mockMvc.perform(MockMvcRequestBuilders.post("/owner-auth/sign-in").contentType(APPLICATION_JSON).content(jsToString(restaurantOwner)))
//                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
//        verify(restaurantService, times(1)).signUpOwner(any());
//    }
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setup() {
        list1 = new ArrayList<>();
        foodItems = new FoodItems("xyz", "400", "xyz", null);
        cartItem = new CartItem(foodItems, 5, 600);
        list1.add(cartItem);
        favourites = new Favourites();
        user = new User("abc", "abc", "abc", "abc", "abc", "abc", 12345, "abc", "abc", 232323, favourites);

// Token Generation

        Map<String, String> result = new HashMap<String, String>();
        Map<String, Object> userdata = new HashMap<>();
        userdata.put("user_email", user.getEmail());


        jwt = Jwts.builder()
                .setClaims(userdata)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "FoodieApp_key")
                .compact();
    }

    @AfterEach
    public void tear() {

        foodItems = null;
        cartItem = null;
        favourites = null;
        list1 = Collections.emptyList();

    }

//    @Test
//    public void getAllUserFaliure() throws Exception {
//        List<User> list4 = new ArrayList<>();
//        list4.add(user);
//        when(userService.getAllUser()).thenReturn(list4);
//        mockMvc.perform(get("/foodieApp/userService/getAllUser")).andExpect(status().isUnauthorized())
//                .andExpect( jsonPath("$[0].email").value("abc"))
//                .andExpect( jsonPath("$[0].firstName").value("abc")).andExpect( jsonPath("$[0].mobileNo").value("abc"));
//
//    }

    @Test
    public void getAllUserssSuccess() throws Exception {
        List<User> list4 = new ArrayList<>();
        list4.add(user);
        when(userService.getAllUser()).thenReturn(list4);
        mockMvc.perform(get("/foodieApp/userService/getAllUser")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("abc"))
                .andExpect(jsonPath("$[0].firstName").value("abc")).andExpect(jsonPath("$[0].mobileNo").value("abc"));

    }

    @Test
    public void addUserSuccess() throws Exception {

        when(userService.addUser(user)).thenReturn(user);
        mockMvc.perform(post("/foodieApp/userService/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk());
    }
}