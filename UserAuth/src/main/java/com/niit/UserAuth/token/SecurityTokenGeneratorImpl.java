package com.niit.UserAuth.token;

import com.niit.UserAuth.domain.User;
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
        String jwtToken = null;
        jwtToken = Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date()).
                signWith(SignatureAlgorithm.HS256, "securityKey").compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", jwtToken);
        map.put("message", "User Logged in successfully");
        return map;
    }
}
