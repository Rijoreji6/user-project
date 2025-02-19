package com.example.user.models;

import com.example.user.enums.Gender;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String name;

    private Gender gender;

    @Email(message = "Invalid email")
    private String email;

    private String password;

}
