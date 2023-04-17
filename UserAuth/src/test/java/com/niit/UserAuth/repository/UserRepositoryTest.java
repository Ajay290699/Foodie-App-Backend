package com.niit.UserAuth.repository;

import com.niit.UserAuth.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("ajay@gmail.com", "Ajay@123");
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void userRegistration() {
        userRepository.save(user);
        User user1 = userRepository.findById(user.getEmailId()).get();
        Assertions.assertNotNull(user1);
        Assertions.assertEquals(user.getEmailId(), user1.getEmailId());
    }


    @Test
    void userLogin() {
        User user1 = userRepository.findById(user.getEmailId()).get();
        assertNotNull(user1);
        assertEquals(user.getEmailId(), user1.getEmailId());
        assertEquals(user.getPassword(), user1.getPassword());
    }
}
