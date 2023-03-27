package com.niit.UserAuth.token;

import com.niit.UserAuth.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    public Map<String, String> tokenGenerator(User user);
}
