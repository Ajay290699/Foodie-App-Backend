package com.niit.UserAuth.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private String firstName;
    private String lastname;
    private String buildingName;
    private String streetName;
    private int flatNo;
    private String city;
    private String state;
    private double pincode;
}