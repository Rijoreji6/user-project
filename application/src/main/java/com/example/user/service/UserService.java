package com.example.user.service;

import com.example.user.models.UserRequest;
import com.example.user.models.UserResponse;

import java.util.List;

public interface UserService {

    String saveUser(String ip, UserRequest userRequest);

    List<UserResponse> readUsers();

    String deleteUser(String email);
}
