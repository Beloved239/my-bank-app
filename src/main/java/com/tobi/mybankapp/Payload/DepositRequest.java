package com.tobi.mybankapp.Payload;

import lombok.Data;

@Data
public class DepositRequest {
    private double amount;
    private String accountNumber;
}
