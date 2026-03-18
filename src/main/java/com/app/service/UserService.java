package com.app.service;

import com.app.entity.User;
import com.app.repo.UserRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(String name, String email) {
        User user = User.builder()
                .name(name)
                .email(email)
                .build();

        return userRepository.save(user);
    }
}