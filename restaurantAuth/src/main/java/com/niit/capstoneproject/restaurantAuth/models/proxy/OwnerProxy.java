package com.niit.capstoneproject.restaurantAuth.models.proxy;

import com.niit.capstoneproject.restaurantAuth.models.RestaurantOwnerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "restaurant-app", url = "localhost:5000")
public interface OwnerProxy {

    @PostMapping("/restaurant-service/add-owner")
    public abstract ResponseEntity<?> sendDataToService(@RequestBody RestaurantOwnerDTO restaurantOwnerDTO);
}
