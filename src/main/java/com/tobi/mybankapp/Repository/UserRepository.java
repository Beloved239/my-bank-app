package com.tobi.mybankapp.Repository;

import com.tobi.mybankapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    boolean isExistsByEmail(String email);
//    boolean isExistsByAccountNumber(String accountNumber);

    boolean existsByFirstName(String firstName);
    boolean existsByAccountNumber(String accountNumber);

//    User findByAccountName(String accountNumber);

    User findByFirstName(String firstName);

    User findByAccountNumber(String accountNumber);

//    Boolean existsByAccountNumber(String accountNumber);




    /*
     * UserRepository is used to perform Database operations
     *
     */
//    Optional<User> findByAccountNumber(String accountNumber);
//    String findByAccountNumber(String accountNumber);


}
