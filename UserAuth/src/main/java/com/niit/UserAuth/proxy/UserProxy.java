package com.niit.UserAuth.proxy;

import com.niit.UserAuth.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "RestaurantService", url = "localhost:900")
public interface UserProxy {

    @PostMapping("restaurantService/addUser")
    public ResponseEntity<?> sendDataToRestaurantService(@RequestBody UserDto userDto);
}
