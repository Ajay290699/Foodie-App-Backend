package com.niit.UserService.service;

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
    @InjectMocks
    private UserServiceImpl userService;

    private CartItem cartItem;

    private FoodItems foodItems;

    private User user;

    private Favourites favourites;

    private List<CartItem> list1;

    private Set<String> cuisines;

    private Map<String, String> restaurant;

    private String jwt;

    @BeforeEach
    public void setup() {
        list1 = new ArrayList<>();
        foodItems = new FoodItems("xyz", "400", "xyz", null);
        cartItem = new CartItem(foodItems, 5, 600);
        list1.add(cartItem);
        cuisines = new HashSet<>();
        cuisines.add("abc");
        restaurant = new HashMap<>();
        restaurant.put("abc", "abc");
        favourites = new Favourites(cuisines, restaurant);
        user = new User("abc", "abc", "abc", "abc", "abc", "abc", 12345, "abc", "abc", 232323, favourites);

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

        foodItems = null;
        cartItem = null;
        favourites = null;
        list1 = Collections.emptyList();

    }


    @Test
    public void getAllUserSuccess() {
        List<User> list = new ArrayList<>();
        list.add(user);
        when(userRepository.findAll()).thenReturn(list);
        assertEquals(list, userService.getAllUser());
    }

    @Test
    public void getAllUserFailure() {
        List<User> list = new ArrayList<>();
        list.add(user);
        when(userRepository.findAll()).thenReturn(list);
        assertNotEquals(list.get(0).getEmail(), userService.getAllUser());
    }

    @Test
    public void getUserFavoriteAllCuisinesSuccess() {
        userRepository.save(user);
        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        assertEquals(cuisines, userService.getUserFavouriteAllCuisines(user.getEmail()));
    }

    @Test
    public void getUserWishlistAllCuisinesFailure() {
        userRepository.save(user);
        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        assertNotEquals(user, userService.getUserFavouriteAllCuisines(user.getEmail()));
    }

    @Test
    public void getUserWishListAllRestaurantsSuccess() {
        userRepository.save(user);
        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        assertEquals(restaurant, userService.getUserFavouriteAllRestaurants(user.getEmail()));
    }

    @Test
    public void getUserWishListAllRestaurantsFailure() {
        userRepository.save(user);
        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        assertNotEquals(cuisines, userService.getUserFavouriteAllRestaurants(user.getEmail()));
    }

    @Test
    public void addCuisinesToUserFavoriteSuccess() {
        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(cuisines, userService.addCuisinesToUserFavourite(user.getEmail(), "abc"));
    }

    @Test
    public void addCuisinesToUserWishlistFailure() {
        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);
        assertNotEquals(user, userService.addCuisinesToUserFavourite(user.getEmail(), "abc"));
    }

    @Test
    public void addRestaurantToUserFavoriteSuccess() {
        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(restaurant, userService.addRestaurantToUserFavourite(user.getEmail(), "abc", "123"));
    }

    @Test
    public void addRestaurantToUserWishlistFailure() {
        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);
        assertNotEquals(cuisines, userService.addRestaurantToUserFavourite(user.getEmail(), "abc", "123"));
    }


}