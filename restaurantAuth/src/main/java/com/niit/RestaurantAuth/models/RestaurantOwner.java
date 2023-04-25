package com.niit.RestaurantAuth.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwner {

 @Id
 private String emailId;
    private String ownerName;
 private String password;
// private String role;


}
