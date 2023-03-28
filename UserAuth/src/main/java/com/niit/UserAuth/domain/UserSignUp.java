package com.niit.UserAuth.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserSignUp {

    private String firstName, lastName;
    private String email, password, role, status;
    private long mobileNo;
    private String buildingName, streetName, city, state;
    private int flatNo;
    private double pincode;
}
