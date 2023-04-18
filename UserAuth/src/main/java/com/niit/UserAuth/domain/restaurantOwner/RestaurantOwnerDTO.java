package com.niit.UserAuth.domain.restaurantOwner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwnerDTO {
    private String emailId, ownerName;

}
