package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UserService {

    List<User> getUsers();

    User getUserById(long id);

    User getUserByName(String username);

    void update(long id, User updatedUser);

    void deleteById(long id);

    void changePassword(long id, String password);

    void register(User user);


}
