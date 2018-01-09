package com.atmproject.cashmachine.domain.authorization;

import com.atmproject.cashmachine.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping(path = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> login(@RequestBody User user) {
        if(!authService.userExists(user))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        if(authService.isLoggedUser())
            return new ResponseEntity(HttpStatus.CONFLICT);
        if(!authService.isSamePassword(user))
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        try {
            User authenticatedUser = authService.authUser();
            authenticatedUser.setPassword(user.getPassword());
            return new ResponseEntity(authenticatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logout(@RequestBody User user) {
        try{
            authService.disallowUser(user);
        }catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
