package com.atmproject.cashmachine.unit;

import com.atmproject.cashmachine.domain.user.User;
import com.atmproject.cashmachine.domain.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import javax.transaction.Transactional;
import static org.junit.Assert.*;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */
@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    private User user;

    @Test
    public void shouldReturnTrueIfSavedUser() {
        user = new User("Jack", "pass", 300,  false, 0);
        assertTrue(userService.saveUser(user));
    }

    @Test
    public void shouldReturnTrueIfUserWasChanged() {
        user = new User("Jack1", "password1", 300,  true, 0);
        userService.saveUser(user);

        user.setUsername("Jack2");
        user.setPassword("password");
        userService.updateUser(user);

        assertEquals(userService.findUser(user.getId()).getUsername(), "Jack2" );
        assertEquals(userService.findUser(user.getId()).getPassword(), "password");
        assertFalse(userService.findUser(user.getId()).isAuthStatus());
    }

    @Test
    public void shouldReturnTrueAfterDeleteUser() {
        user = new User("Jack1", "password1", 300,  true, 100);
        userService.saveUser(user);
        assertTrue(userService.deleteUser(user.getId()));
    }

    @Test
    public void shouldReturnSavedUserById() {
        user = new User("Jack1", "password1", 300,  true, 100);
        userService.saveUser(user);
        User user2 = userService.findUser(user.getId());
        assertEquals(user,user2);
    }

    @Test
    public void shouldReturnTrueIfUserWasFoundByUsername() {
        user = new User("Jack1", "password1", 300,  true, 100);
        userService.saveUser(user);
        assertNotNull(userService.getIfExists("Jack1"));
    }

    @Test
    public void shouldReturnTheNumberOfUserLogged() {
        User user1 = new User("Jack1", "password1", 300,  true, 100);
        userService.saveUser(user1);
        User user2 = new User("Jack2", "password2", 300,  true, 100);
        userService.saveUser(user2);
        User user3 = new User("Jack3", "password3", 300,  true, 100);
        userService.saveUser(user3);
        User user4 = new User("Jack4", "password4", 300,  false, 100);
        userService.saveUser(user4);
        assertEquals(userService.verifyConnectedUsers(), 3);
    }
}