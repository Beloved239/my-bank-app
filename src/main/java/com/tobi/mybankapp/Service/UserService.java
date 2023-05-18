package com.tobi.mybankapp.Service;

import com.tobi.mybankapp.Payload.DepositRequest;
import com.tobi.mybankapp.Entity.User;
import com.tobi.mybankapp.Payload.TransferRequest;
import com.tobi.mybankapp.Payload.UserRequest;
import com.tobi.mybankapp.Payload.WithdrawalRequest;

import java.util.List;

public interface UserService {
    //interface helps to program in the general not in the specific
    //incomplete method

    //1st Method--return type will be User// method to create an object of User

    User registerUser (UserRequest userRequest);

    List<User> fetchAllRegisteredUSer();

    User nameEnquiry(String firstName);

    User fetchByAccountNumber(String accountNumber);

    String  depositUsingAccountNumber(String accountNumber, DepositRequest depositDTO);

    String withdrawalRequest(String accountNumber, WithdrawalRequest withdrawalRequest);

    String transferRequest(String accountNumber, TransferRequest transferRequest);

    String balanceEnquiry(String accountNumber);


}
