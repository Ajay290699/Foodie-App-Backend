package com.niit.RestaurantAuth.models.proxy;

import com.niit.RestaurantAuth.models.RestaurantOwnerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "restaurant-app", url = "localhost:5000")
public interface OwnerProxy {

    @PostMapping("/restaurant-service/add-owner")
    public abstract ResponseEntity<?> sendDataToService(@RequestBody RestaurantOwnerDTO restaurantOwnerDTO);
}
