package com.example.user.models;

import com.example.user.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String name;

    private Gender gender;

    private String email;

    private String password;

    private String ip;

    private String countryCode;
}
