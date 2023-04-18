package com.niit.UserAuth.repository;

import com.niit.UserAuth.domain.restaurantOwner.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantOwnerRepo extends JpaRepository<RestaurantOwner, String> {

    public RestaurantOwner findByEmailIdAndPassword(String emailId, String pwd);

}
