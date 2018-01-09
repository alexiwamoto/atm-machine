package com.atmproject.cashmachine.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/save/user", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveUser(@RequestBody User user) {
        try {
            if(userService.saveUser(user)) {
                return new ResponseEntity(HttpStatus.CREATED);
            }
        } catch (Exception s) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/delete/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable Long id) {
        if (id != null) {
            try {
                if(userService.deleteUser(id)) {
                    return new ResponseEntity(HttpStatus.OK);
                }
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/update/user", method= RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody User user) {
        try{
            if(userService.updateUser(user)) {
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
