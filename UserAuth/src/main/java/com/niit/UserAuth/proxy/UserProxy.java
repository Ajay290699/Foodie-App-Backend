package com.niit.UserAuth.proxy;

import com.niit.UserAuth.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "UserService", url = "localhost:9999")
public interface UserProxy {

    @PostMapping("/foodieApp/userService/addUser")
    public ResponseEntity<?> sendDataToRestaurantService(@RequestBody UserDto userDto);
}
