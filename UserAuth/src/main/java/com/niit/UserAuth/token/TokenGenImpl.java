package com.niit.UserAuth.token;

import com.niit.UserAuth.domain.restaurantOwner.RestaurantOwner;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenGenImpl implements SecurityTokenGeneratorRestaurant {
    @Override
    public Map<String, String> tokenGeneration(RestaurantOwner restaurantOwner) {


        Map<String, Object> tokenData = new HashMap<>();

        tokenData.put("emailId", restaurantOwner.getEmail());
        tokenData.put("password", restaurantOwner.getPassword());

        Map<String, String> token = new HashMap<>();

        String tokenBuilder = Jwts.builder()
                .setClaims(tokenData)
                .setIssuedAt(new Date())
                .setIssuer("Restaurant_Owner")
                .signWith(SignatureAlgorithm.HS512, "Fooide-app-owner-key")
                .compact();

        token.put("token", tokenBuilder);
        token.put("message", "login succes, token generated");

        return token;
    }
}
