package com.example.user.service;

import com.example.user.entity.UserDetails;
import com.example.user.models.UserRequest;
import com.example.user.models.UserResponse;
import com.example.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    ModelMapper modelMapper;

    @Mock
    RestTemplate restTemplate;

    @Mock
    UserRepository userRepository;

    @Test
    void testSaveUser() {
        String testIp = "8.8.8.8";
        Map<Object, String> mockResponse = new HashMap<>();
        mockResponse.put("countryCode", "US");

        when(restTemplate.getForObject("http://ip-api.com/json/" + testIp, Map.class))
                .thenReturn(mockResponse);
        UserRequest userRequest = new UserRequest();
        UserDetails userDetails = new UserDetails();
        when(modelMapper.map(any(), any())).thenReturn(userDetails);
        when(userRepository.save(any(UserDetails.class))).thenReturn(userDetails);
        String response = userService.saveUser(testIp, new UserRequest());
        assertNotNull(response);
    }

    @Test
    void testReadUsers() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<UserDetails> listUserDetails = new ArrayList<>();
        UserDetails user = new UserDetails();
        user.setName("qwerty");
        user.setEmail("qwerty@gmail.com");
        listUserDetails.add(user);
        when(userRepository.findAll()).thenReturn(listUserDetails);
        assertNotNull(listUserDetails);
        List<UserResponse> userResponseList = new ArrayList<>();
        UserResponse userResponse = new UserResponse();
        when(modelMapper.map(any(), any())).thenReturn(userResponse);
        userResponseList = userService.readUsers();
        assertNotNull(userResponseList);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteByEmail(anyString());
        String answer = userService.deleteUser("abc@gmail.com");
        assertNotNull(answer);
    }
}
