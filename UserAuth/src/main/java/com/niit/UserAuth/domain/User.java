package com.niit.UserAuth.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    private String email;
    private String firstName;
    private String lastname;
    private long mobileNo;
    private String password;
    private String role;
    private String status;
    private List<Address> address;
}
