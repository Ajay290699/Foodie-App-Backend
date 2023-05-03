package com.niit.UserAuth.domain.user;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
public class UserDto {

    private String firstName;
    private String lastname;
    @Id
    private String email;
    private String buildingName;
    private String streetName;
    private String mobileNo;
    private int flatNO;
    private String city;
    private String state;
    private double pincode;
    private String pi;
}
