package com.atmproject.cashmachine.unit;

import com.atmproject.cashmachine.domain.money.MoneyService;
import com.atmproject.cashmachine.domain.user.User;
import com.atmproject.cashmachine.domain.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.Assert.*;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */

@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MoneyTest {

    @Autowired
    private MoneyService moneyService;

    @Autowired
    private UserService userService;

    @Test
    public void shouldBeReturnAMapWithBills() {
        Map map = moneyService.getAmount(80);
        assertEquals(map.get(50), 1);
        assertEquals(map.get(20), 1);
        assertEquals(map.get(10), 1);
    }

    @Test
    public void shouldBeReturnNewBankBalanceAfterWithdraw() {
        User user = new User("Jack1", "password1", 300,  true, 0);
        userService.saveUser(user);
        user.setWithdrawCash(150);
        User updatedUser = userService.updateUserBalance(user);
        assertTrue(updatedUser.getBankBalance() == 150);
    }
}
