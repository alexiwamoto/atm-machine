package com.atmproject.cashmachine.domain.money;

import com.atmproject.cashmachine.domain.user.User;
import com.atmproject.cashmachine.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */
@RestController
@RequestMapping("/api")
public class MoneyController {

    @Autowired
    MoneyService moneyService;

    @Autowired
    UserService userService;

    @RequestMapping(path = "withdrawcash/", method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MoneyResponse> getCash(@RequestBody User user) {
        if(user.getWithdrawCash() <= user.getBankBalance()) {
            Map<Integer, Integer> bills = moneyService.getAmount(user.getWithdrawCash());
            User updatedUser;
            if(bills.size() > 0) {
                updatedUser = userService.updateUserBalance(user);
                updatedUser.setPassword(user.getPassword());
                return new ResponseEntity<MoneyResponse>(new MoneyResponse(updatedUser, bills), HttpStatus.OK);
            }
        }
        return new ResponseEntity<MoneyResponse>(HttpStatus.EXPECTATION_FAILED);
    }
}
