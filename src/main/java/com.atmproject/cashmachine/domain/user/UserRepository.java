package com.atmproject.cashmachine.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alexandre on 08/01/18
 * @project atm-project
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsername (String username);

    @Modifying
    @Transactional
    @Query("Update User u set u.username =:username, u.password =:password where u.id =:id")
    int updateUser(@Param("id") Long id, @Param("username") String username, @Param("password") String password);

    @Modifying
    @Transactional
    @Query("Update User u set u.authStatus =:authStatus where u.id =:id")
    int updateStatus(@Param("id") Long id, @Param("authStatus") boolean authStatus);

    @Modifying
    @Transactional
    @Query("Update User u set u.bankBalance =:bankBalance where u.id =:id")
    int updateUserBalance(@Param("id") Long id, @Param("bankBalance") Integer bankBalance);

    @Query("SELECT COUNT(u) FROM User u WHERE u.authStatus=true")
    long countIfTrue();
}
