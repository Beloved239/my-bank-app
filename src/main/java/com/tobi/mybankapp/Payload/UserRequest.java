package com.tobi.mybankapp.Payload;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

//DTO-- Data Transfer Object to move data from user to Database
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
//    @NonNull
    private String firstName;
    private String lastName;
    private String otherName;
    private String phoneNumber;
    private String alternativeNumber;
    private String email;
    private String gender;
    private String address;
    private String religion;
    private String bvn;
    private String referralCode;
    private Date dateOfBirth;
    private String password;
}
