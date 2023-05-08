package com.niit.UserService.service;

import com.niit.UserService.model.*;
import com.niit.UserService.repository.FavouriteRepository;
import com.niit.UserService.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private FavouriteRepository favouriteRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private CartItem cartItem;

    private User user;

    private Favourites favourites;

    private List<CartItem> list1;

    private List<Dishes> dishes;

    private List<Restaurant> restaurant;

    private String jwt;

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
//    public void getAllUserSuccess() {
//        List<User> list = new ArrayList<>();
//        list.add(user);
//        when(userRepository.findAll()).thenReturn(list);
//        assertEquals(list, userService.getAllUser());
//    }
//
//    @Test
//    public void getAllUserFailure() {
//        List<User> list = new ArrayList<>();
//        list.add(user);
//        when(userRepository.findAll()).thenReturn(list);
//        assertNotEquals(list.get(0), userService.getAllUser());
//    }


    @Test
    public void getUserSuccess() {
        when(userRepository.findById("saumya@gmail.com")).thenReturn(Optional.ofNullable(user));
        assertEquals(user, userService.getUserDetails("saumya@gmail.com"));
    }

    @Test
    public void getUserFailure() {
        when(userRepository.findById("saumya@gmail.com")).thenReturn(Optional.ofNullable(user));
        User user1 = new User();
//       user1=user;
        user1.setEmail("xyz@gmail.com");
        assertNotEquals(user1, userService.getUserDetails("saumya@gmail.com"));
    }


    @Test
    public void getUserFavoriteAllDishesSuccess() {
        favouriteRepository.save(favourites);
        when(favouriteRepository.findById(favourites.getEmail())).thenReturn(Optional.ofNullable(favourites));
        assertEquals(dishes, userService.getUserFavouriteAllDishes(favourites.getEmail()));
    }


    @Test
    public void getUserFavoriteAllDishesFailure() {
        favouriteRepository.save(favourites);
        when(favouriteRepository.findById(favourites.getEmail())).thenReturn(Optional.ofNullable(favourites));
        Favourites favourites1 = new Favourites();
//
        assertNotEquals(favourites1.getDishes(), userService.getUserFavouriteAllDishes(favourites.getEmail()));
    }


    @Test
    public void getUserFavoriteAllRestaurantsSuccess() {
        favouriteRepository.save(favourites);
        when(favouriteRepository.findById(favourites.getEmail())).thenReturn(Optional.ofNullable(favourites));
        assertEquals(restaurant, userService.getUserFavouriteAllRestaurants(user.getEmail()));
    }

    @Test
    public void getUserFavoriteAllRestaurantsFailure() {
        favouriteRepository.save(favourites);
        when(favouriteRepository.findById(favourites.getEmail())).thenReturn(Optional.ofNullable(favourites));
        assertNotEquals(restaurant, userService.getUserFavouriteAllDishes(favourites.getEmail()));
    }

    //
//
    @Test
    public void addCuisinesToUserFavoriteSuccess() {
        when(favouriteRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(favourites));
        when(favouriteRepository.save(favourites)).thenReturn(favourites);
        assertEquals(dishes, userService.addDishesToUserFavourite(favourites.getEmail(), new Dishes("xyz", "veg", 40, 2)));
    }

    @Test
    public void addCuisinesToUserFavoriteFailure() {
        when(favouriteRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(favourites));
        when(favouriteRepository.save(favourites)).thenReturn(favourites);
        Dishes dishes1 = new Dishes();
        assertNotEquals(dishes1, userService.addDishesToUserFavourite(favourites.getEmail(), new Dishes("xyz", "veg", 40, 2)));
    }


//    @Test
//    public void addCuisinesToUserWishlistFailure() {
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
//        when(userRepository.save(user)).thenReturn(user);
//        assertNotEquals(user, userService.addCuisinesToUserFavourite(user.getEmail(), "abc"));
//    }
//
//    @Test
//    public void addRestaurantToUserFavoriteSuccess() {
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
//        when(userRepository.save(user)).thenReturn(user);
//        assertEquals(restaurant, userService.addRestaurantToUserFavourite(user.getEmail(), "abc", "123"));
//    }
//
//    @Test
//    public void addRestaurantToUserWishlistFailure() {
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
//        when(userRepository.save(user)).thenReturn(user);
//        assertNotEquals(dishes, userService.addRestaurantToUserFavourite(user.getEmail(), "abc", "123"));
//    }


}