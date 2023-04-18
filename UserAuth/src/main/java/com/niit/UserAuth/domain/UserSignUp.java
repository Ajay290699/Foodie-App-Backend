package com.niit.UserAuth.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserSignUp {


    @Id
    private String email;
    private String firstName, lastName;
    private String password;
    private String mobileNo;
    private String buildingName, streetName, city, state;
    private int flatNo;
    private double pincode;
//    private Image userImage;
//    private MultipartFile Image;
}
