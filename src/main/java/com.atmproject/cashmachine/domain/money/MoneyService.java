package com.atmproject.cashmachine.domain.money;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */
@Service
public class MoneyService {

    private Money money;

    private Map<Integer, Integer> billAmount;

    public Map<Integer, Integer> getAmount(Integer value) {
        billAmount = new HashMap<Integer, Integer>();
        if(checkValue(value)) {
            money = new Money();
            Collections.reverse(money.getBills());
            while (value > 0) {
                for(Integer bill : money.getBills()) {
                    int i = 0;
                    while (value >= bill) {
                        value -= bill;
                        i ++;
                    }
                    billAmount.put(bill, i);
                }
            }
        }
        return billAmount;
    }

    private boolean checkValue(Integer value) {
        if(value % 10 != 0)
            return false;
        return true;
    }
}
