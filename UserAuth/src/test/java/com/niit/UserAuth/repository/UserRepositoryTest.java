package com.niit.UserAuth.repository;

import com.niit.UserAuth.domain.User;
import org.junit.jupiter.api.AfterEach;
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
    public void setUp() {
        user = new User("ajay@gmail.com", "Pass@123");
    }

    @AfterEach
    public void tearDown() {
        user = null;
    }

    @Test
    public void userRegistration() {
        userRepository.save(user);
        User user1 = userRepository.findById(user.getEmailId()).get();
        assertNotNull(user1);
        assertEquals(user.getEmailId(), user1.getEmailId());
    }

    @Test
    public void userRegistrationFailure() {
        userRepository.save(user);
        User user1 = userRepository.findById(user.getEmailId()).get();
        assertNotNull(user1);
        assertEquals(user, user1.getEmailId());
    }

    @Test
    public void userLogin() {
        User user1 = userRepository.findById(user.getEmailId()).get();
        assertNotNull(user1);
        assertEquals(user.getEmailId(), user1.getEmailId());
        assertEquals(user.getPassword(), user1.getPassword());
    }
}
