package com.niit.UserAuth.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
public class UserDto {

    private String firstName;
    private String lastname;
    private String emailId;
    private String buildingName;
    private String streetName;
    private int flatNO;
    private String city;
    private String state;
    private double pincode;
}
