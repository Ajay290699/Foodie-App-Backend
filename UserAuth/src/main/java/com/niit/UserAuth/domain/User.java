package com.niit.UserAuth.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    private String email;
    private String password, role;
    private long mobileNo;
    private String status;
}
