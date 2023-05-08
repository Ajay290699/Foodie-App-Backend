package com.niit.UserService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.UserService.model.*;
import com.niit.UserService.service.UserService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    private CartItem cartItem;

    private User user;

    private Favourites favourites;

    private List<CartItem> list1;

    private List<Dishes> dishes;

    private List<Restaurant> restaurant;

    private String jwt;

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
//        list1 = new ArrayList<>();
        dishes = new ArrayList<>();
        dishes.add(new Dishes("xyz", "veg", 40, 2));
        cartItem = new CartItem("saumya@gmail.com", dishes);
//        list1.add(cartItem);
//        cuisines = new HashSet<>();
//        cuisines.add("abc");
        restaurant = new ArrayList<>();
        restaurant.add(new Restaurant("abc", "hss", dishes));
        favourites = new Favourites("saumya@gmail.com", dishes, restaurant);
        user = new User("saumya@gmail.com", "jhjjd", "jhjkdh", "98787887989", "yuyuy", "uuwhwd", 12424, "jkhff", "up", 787637, null, null, "");

        Map<String, String> result = new HashMap<String, String>();
        Map<String, Object> userdata = new HashMap<>();
        userdata.put("user_email", user.getEmail());


        jwt = Jwts.builder()
                .setClaims(userdata)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "securityKey")
                .compact();
    }

    @AfterEach
    public void tear() {

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

//    @Test
//    public void getAllUserssSuccess() throws Exception {
//        List<User> list4 = new ArrayList<>();
//        list4.add(user);
//        when(userService.getAllUser()).thenReturn(list4);
//        mockMvc.perform(get("/foodieApp/userService/getAllUser")).andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].email").value("saumya@gmail.com"))
//                .andExpect(jsonPath("$[0].firstName").value("jhjjd")).andExpect(jsonPath("$[0].mobileNo").value("98787887989"));
//
//    }

    @Test
    public void addUserSuccess() throws Exception {

        when(userService.addUser(user)).thenReturn(user);
        mockMvc.perform(post("/foodieApp/userService/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk());
    }
}