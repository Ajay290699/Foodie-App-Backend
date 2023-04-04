package com.niit.RestaurantAuth.services;

import com.niit.RestaurantAuth.models.RestaurantOwner;
import com.niit.RestaurantAuth.models.RestaurantOwnerDTO;
import com.niit.RestaurantAuth.models.proxy.OwnerProxy;
import com.niit.RestaurantAuth.repository.RestaurantOwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantAuthService {

    @Autowired
    private RestaurantOwnerRepo restaurantOwnerRepo;

    @Autowired
    private OwnerProxy ownerProxy;

    @Override
    public RestaurantOwner signUpOwner(RestaurantOwner restaurantOwner) {
        RestaurantOwnerDTO restaurantOwnerDTO = new RestaurantOwnerDTO(restaurantOwner.getEmailId(), restaurantOwner.getOwnerName());
        ResponseEntity<?> response = ownerProxy.sendDataToService(restaurantOwnerDTO);

        return restaurantOwnerRepo.save(restaurantOwner);
    }

    @Override
    public RestaurantOwner login(String emailId, String password) {

        return restaurantOwnerRepo.findByEmailIdAndPassword(emailId, password);
    }
}
