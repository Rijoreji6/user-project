package com.example.user.entity;

import com.example.user.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Automatically generates the primary key
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String password;

    private String ip;

    private String countryCode;
}
