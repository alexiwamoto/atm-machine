package com.atmproject.cashmachine.domain.money;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */
@Data
public class Money {

    private List<Integer> bills;

    public Money() {
        fillBillValues();
    }

    private void fillBillValues() {
        bills = new ArrayList<Integer>();
        bills.add(10);
        bills.add(20);
        bills.add(50);
        bills.add(100);
    }
}
