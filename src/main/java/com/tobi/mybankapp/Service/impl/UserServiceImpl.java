package com.tobi.mybankapp.Service.impl;

import com.tobi.mybankapp.Payload.DepositRequest;
import com.tobi.mybankapp.Entity.User;
import com.tobi.mybankapp.Payload.TransferRequest;
import com.tobi.mybankapp.Payload.UserRequest;
import com.tobi.mybankapp.Payload.WithdrawalRequest;
import com.tobi.mybankapp.Repository.UserRepository;
import com.tobi.mybankapp.Service.UserService;
import com.tobi.mybankapp.Util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(UserRequest userRequest) {

        //save record
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setOtherName(userRequest.getOtherName());
        user.setAccountNumber(Utility.generateAccountNumber());
        user.setAccountBalance(0.0);
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAlternativePhoneNumber(userRequest.getAlternativeNumber());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setGender(userRequest.getGender());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setBvn(userRequest.getBvn());
        user.setReligion(userRequest.getReligion());
        user.setReferralCode(userRequest.getReferralCode());
        user.setPassword(userRequest.getPassword());

        return  userRepository.save(user);
    }


    public List<User> fetchAllRegisteredUSer() {
        return userRepository.findAll();
    }


    public User nameEnquiry(String firstName) throws IllegalArgumentException {

        boolean isFirstNameExist = userRepository.existsByFirstName(firstName);
        if (isFirstNameExist){
            return userRepository.findByFirstName(firstName);
        }else {
            return null;
        }
    }


    @Override
    public User fetchByAccountNumber(String accountNumber){
            boolean isAccountNumberExist = userRepository.existsByAccountNumber(accountNumber);

            if (isAccountNumberExist){
                return userRepository.findByAccountNumber(accountNumber);
            }else {
            return null;
            }
    }


    @Override
    public String depositUsingAccountNumber(String accountNumber, DepositRequest depositDTO) {
        boolean existsByAccountNumber = userRepository.existsByAccountNumber(accountNumber);
        if (existsByAccountNumber) {
            User user = userRepository.findByAccountNumber(accountNumber);
            user.setAccountBalance(user.getAccountBalance() + depositDTO.getAmount());
            userRepository.save(user);
            return "Your new balance is: " + user.getAccountBalance();
        }else {
            return null;
        }
    }

    @Override
    public String withdrawalRequest(String accountNumber, WithdrawalRequest withdrawalRequest){
        boolean existsByAccount = userRepository.existsByAccountNumber(accountNumber);
        if (existsByAccount){
            User user = userRepository.findByAccountNumber(accountNumber);
            user.setAccountBalance(user.getAccountBalance()- withdrawalRequest.getAmount());
            userRepository.save(user);
            return withdrawalRequest.getAmount()+" Withdrawn Successfully";
        }
        return null;
    }

    @Override
    public String transferRequest(String accountNumber, TransferRequest transferRequest) {
        boolean existsByAccountNumber = userRepository.existsByAccountNumber(accountNumber);
        boolean recipientExistsByAccountNumber = userRepository.existsByAccountNumber(transferRequest.getAccountNumber());
        User user = userRepository.findByAccountNumber(accountNumber);
        User user1 = userRepository.findByAccountNumber(transferRequest.getAccountNumber());

        if (existsByAccountNumber && recipientExistsByAccountNumber){
            if (user.getAccountBalance()> transferRequest.getAmount()){
            user.setAccountBalance(user.getAccountBalance()- transferRequest.getAmount());
            userRepository.save(user);
                user1.setAccountBalance(user1.getAccountBalance()+ transferRequest.getAmount());
                userRepository.save(user1);
            return "Transfer Successful!"+" We've sent "+"#" +transferRequest.getAmount()+" to "+ user1.getFirstName()+" "+user1.getLastName();
            }else {
                return "Not Sufficient fund!";
            }

        }
        return "Recipient not found";
    }

    @Override
    public String balanceEnquiry(String accountNumber) {
        boolean existsByAccountNumber = userRepository.existsByAccountNumber(accountNumber);
        User user = userRepository.findByAccountNumber(accountNumber);
        if (existsByAccountNumber){
            return user.getFirstName()+" "+ user.getLastName()+" "+ user.getOtherName()+" "+user.getAccountBalance();
        }
        return "Account recipient not found";
    }


}
