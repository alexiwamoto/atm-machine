package com.atmproject.cashmachine.domain.money;

import com.atmproject.cashmachine.domain.user.User;
import lombok.Data;

import java.util.Map;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */
@Data
public class MoneyResponse {

    private User user;

    private Map<Integer, Integer> bills;

    public MoneyResponse(User user, Map<Integer, Integer> bills) {
        this.user = user;
        this.bills = bills;
    }
}
