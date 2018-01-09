package com.atmproject.cashmachine.domain.authorization;

import com.atmproject.cashmachine.domain.user.User;
import com.atmproject.cashmachine.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.atmproject.cashmachine.util.CryptUtil.encodePassword;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */
@Service
public class AuthService {

    @Autowired
    private UserService userService;

    private User foundUser;

    public long verifyConnectedUsers() {
        return userService.verifyConnectedUsers();
    }

    public boolean userExists(User user) {
        this.foundUser = userService.getIfExists(user.getUsername());
        if(this.foundUser != null)
            return true;
        return false;
    }

    public boolean isLoggedUser() {
        return this.foundUser.isAuthStatus();
    }

    public boolean isSamePassword(User user) {
        return this.foundUser.getPassword().equals(encodePassword(user.getPassword()));
    }

    public User authUser() {
        if(verifyConnectedUsers() <= 5) {
            this.foundUser.setAuthStatus(true);
            userService.updateStatus(this.foundUser);
            return this.foundUser;
        }
        return null;
    }

    public boolean disallowUser(User user) {
        if(userExists(user) && isLoggedUser()) {
            this.foundUser.setAuthStatus(false);
            userService.updateStatus(this.foundUser);
            return true;
        }
        return false;
    }
}
