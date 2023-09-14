package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserValidatorService {
    private final UserRepository userRepository;

    @Autowired
    public UserValidatorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> checkUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
