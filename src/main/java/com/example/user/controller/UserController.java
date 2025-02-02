package com.example.user.controller;

import com.example.user.models.UserRequest;
import com.example.user.models.UserResponse;
import com.example.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Save User details", description = "Store user details based on input. Only user privileged user can use this Api")
    public ResponseEntity<String> saveUser(HttpServletRequest httpRequest, @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userService.saveUser(httpRequest.getRemoteAddr(), userRequest));
    }

    @GetMapping("/read")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Fetch all User details", description = "Fetch all user details. Only admin privileged user can use this Api")
    public ResponseEntity<List<UserResponse>> readUsers() {
        return ResponseEntity.ok(userService.readUsers());
    }

    @DeleteMapping("/delete/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete User by email", description = "Delete User by providing email. Only admin privileged user can use this Api")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        return ResponseEntity.ok(userService.deleteUser(email));
    }
}
