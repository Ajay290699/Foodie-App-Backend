package com.niit.RestaurantAuth.repository;

import com.niit.RestaurantAuth.models.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantOwnerRepo extends JpaRepository<RestaurantOwner, String> {

    public RestaurantOwner findByEmailIdAndPassword(String emailId, String pwd);

}
