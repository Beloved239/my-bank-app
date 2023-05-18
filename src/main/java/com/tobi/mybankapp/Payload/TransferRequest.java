package com.tobi.mybankapp.Payload;

import lombok.Data;

@Data
public class TransferRequest {
    private double amount;
    private String accountNumber;
}
