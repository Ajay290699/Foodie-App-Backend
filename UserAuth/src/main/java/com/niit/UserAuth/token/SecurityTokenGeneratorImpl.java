package com.niit.UserAuth.token;

import com.niit.UserAuth.domain.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator {

    @Override
    public Map<String, String> tokenGenerator(User user) {
        Map<String, String> result = new HashMap<String, String>();

        user.setPassword("");

        Map<String, Object> userdata = new HashMap<>();

        userdata.put("user_email", user.getEmail());
        String jwt = Jwts.builder()
                .setClaims(userdata)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "mysecretkey")
                .compact();

        result.put("token", jwt);
        result.put("message", "User login Success");
        return result;
    }

//    @Override
//    public Map<String, String> tokenGenerator(User user) {

//        saumya method

//        Map<String, String> result = new HashMap<String, String>();
//
//        user.setPassword("");
//
//        Map<String, Object> userdata = new HashMap<>();
//
//        userdata.put("user_email", user.getEmail());
//        String jwt = Jwts.builder()
//                .setClaims(userdata)
//                .setIssuedAt(new Date())
//                .signWith(SignatureAlgorithm.HS512, "secretKey")
//                .compact();
//
//        result.put("token", jwt);
//        result.put("message", "User login Success");
//        return result;

////
//        Map<String, Object> tokenData = new HashMap<>();
//
//        tokenData.put("emailId", user.getEmail());
//        tokenData.put("password", user.getPassword());
//
//        Map<String, String> token = new HashMap<>();
//
//        String tokenBuilder = Jwts.builder()
//                .setClaims(tokenData)
//                .setIssuedAt(new Date())
//                .setIssuer("Restaurant_Owner")
//                .signWith(SignatureAlgorithm.HS512, "Fooide-app-owner-key")
//                .compact();
//
//        token.put("token", tokenBuilder);
//        token.put("emailId", user.getEmail());
//        token.put("message", "login succes, token generated");
//
//        return token;
//    }

    //rajeshwar sir method

//        Map<String, String> token = new HashMap<>();
//        Map<String, Object> tokenData = new HashMap<>();
//        tokenData.put("emailId", user.getEmail());
//
//        String jwtToken = Jwts.builder()
//                .setClaims(tokenData)
//                .setIssuedAt(new Date())
//                .setIssuer("FoodieApp_user")
//                .signWith(SignatureAlgorithm.HS512,"securityKey")
//                .compact();
//
//        token.put("token", jwtToken);
//        token.put("message", "login successfull, token generated");
//        return token;

//        gayatri ma'am method
//        String jwtToken = null;
//        jwtToken = Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date()).
//                signWith(SignatureAlgorithm.HS256, "securityKey").compact();
//        Map<String, String> map = new HashMap<>();
//        map.put("token", jwtToken);
//        map.put("message", "User Logged in successfully");
//        return map;


}
