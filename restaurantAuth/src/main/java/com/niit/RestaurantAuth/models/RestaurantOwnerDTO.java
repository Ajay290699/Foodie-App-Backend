package com.niit.RestaurantAuth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwnerDTO {
    private String emailId, ownerName;

}
