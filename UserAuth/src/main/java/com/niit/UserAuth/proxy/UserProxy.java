package com.niit.UserAuth.proxy;

import com.niit.UserAuth.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "UserService", url = "localhost:900")
public interface UserProxy {

    public ResponseEntity<?> sendDataToRestaurantService(@RequestBody User user);
}
