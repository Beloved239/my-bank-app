package com.tobi.mybankapp.Controler;

import com.tobi.mybankapp.Payload.DepositRequest;
import com.tobi.mybankapp.Entity.User;
import com.tobi.mybankapp.Payload.TransferRequest;
import com.tobi.mybankapp.Payload.UserRequest;
import com.tobi.mybankapp.Payload.WithdrawalRequest;
import com.tobi.mybankapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public User registerUser(@RequestBody UserRequest userRequest){
        return userService.registerUser(userRequest);
    }

    @GetMapping
    public List<User> fetchAllRegisteredUsers(){
        return userService.fetchAllRegisteredUSer();
    }

    @GetMapping("/{firstname}")
    public User fetchUserByFirstName(@RequestParam(name = "firstname", required = true) String firstName){
        return userService.nameEnquiry(firstName);
    }

    @GetMapping("/number")
    public User fetchUserByAccountNumber(@RequestParam(name = "number", required = true)String accountNumber){
        return userService.fetchByAccountNumber(accountNumber);
    }

    @PutMapping("/{number}")
    public String depositAmount(@PathVariable String number, @RequestBody DepositRequest depositDTO){
        return userService.depositUsingAccountNumber(number, depositDTO);
    }

    @PutMapping("/withdrawal/{number}")
    public String withdrawalRequest(@PathVariable String number, @RequestBody WithdrawalRequest withdrawalRequest){
        return userService.withdrawalRequest(number, withdrawalRequest);
    }

    @PutMapping("/transfer/amount/{number}")
    public String transferToUser(@PathVariable String number, @RequestBody TransferRequest transferRequest){
        return userService.transferRequest(number, transferRequest);
    }

    @GetMapping("/number/enquiry")
    public String balanceEnquiry(@RequestParam(name = "enquiry", required = true) String accountNumber){
        return userService.balanceEnquiry(accountNumber);
    }





}
