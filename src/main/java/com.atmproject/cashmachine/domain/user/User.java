package com.atmproject.cashmachine.domain.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */
@Data
@Entity
public class User {

    public User() {
    }

    public User(String username, String password, Integer bankBalance, boolean authStatus, Integer withdrawCash) {
        this.username = username;
        this.password = password;
        this.bankBalance = bankBalance;
        this.authStatus = authStatus;
        this.withdrawCash = withdrawCash;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private Integer bankBalance;

    private boolean authStatus;

    private Integer withdrawCash;
}
