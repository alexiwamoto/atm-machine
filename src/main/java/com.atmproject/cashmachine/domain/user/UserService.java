package com.atmproject.cashmachine.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.atmproject.cashmachine.util.CryptUtil.encodePassword;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean saveUser(User user) {
        if (getIfExists(user.getUsername()) == null) {
            String encodedPassword = encodePassword(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean updateUser(User user) {
        if(findUser(user.getId()) != null) {
            userRepository.updateUser(user.getId(), user.getUsername(), encodePassword(user.getPassword()));
            user.setAuthStatus(false);
            updateStatus(user);
            return true;
        }
        return false;
    }

    public User updateUserBalance(User user) {
        user.setBankBalance(user.getBankBalance() - user.getWithdrawCash());
        userRepository.updateUserBalance(user.getId(), user.getBankBalance());
        return findUser(user.getId());
    }

    public boolean deleteUser(Long id) {
        if(findUser(id) != null) {
            userRepository.delete(id);
            return true;
        }
        return false;
    }

    public void updateStatus(User user) {
        userRepository.updateStatus(user.getId(), user.isAuthStatus());
    }

    public User findUser(Long id) {
        return userRepository.findOne(id);
    }

    public User getIfExists(String username) {
        return userRepository.getUserByUsername(username);
    }

    public long verifyConnectedUsers() {
        return userRepository.countIfTrue();
    }
}
