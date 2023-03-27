package com.niit.UserAuth.repository;

import com.niit.UserAuth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
