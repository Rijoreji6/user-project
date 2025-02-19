package com.example.user.service;

import com.example.user.entity.UserDetails;
import com.example.user.models.UserRequest;
import com.example.user.models.UserResponse;
import com.example.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final RestTemplate restTemplate;

    @Override
    public String saveUser(String ip, UserRequest userRequest) {

        try {
            Map<Object, String> restResponse = restTemplate.getForObject("http://ip-api.com/json/" + ip, Map.class);

            String countryCode = restResponse.getOrDefault("countryCode", null);

            UserDetails userDetails = ConvertDtoToEntity(userRequest);
            userDetails.setIp(ip);
            userDetails.setCountryCode(countryCode);
            userRepository.save(userDetails);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getMessage().contains("PUBLIC.CONSTRAINT_INDEX_3 ON PUBLIC.USER_DETAILS(EMAIL NULLS FIRST)"))
                throw new DataIntegrityViolationException("Email should not be null or unique ");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return "Success";
    }

    @Override
    public List<UserResponse> readUsers() {
        return ConvertEntityToDto(userRepository.findAll());
    }

    @Override
    @Transactional
    public String deleteUser(String email) {
        userRepository.deleteByEmail(email);
        return "Success";
    }

    List<UserResponse> ConvertEntityToDto(List<UserDetails> user) {
        List<UserResponse> userResponses = new ArrayList<>();
        user.forEach(u ->
                userResponses.add(modelMapper.map(u, UserResponse.class)));
        return userResponses;
    }

    UserDetails ConvertDtoToEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, UserDetails.class);
    }
}
