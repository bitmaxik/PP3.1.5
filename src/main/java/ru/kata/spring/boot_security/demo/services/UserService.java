package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UserService {

    List<User> getUsers();

    User getUserById(long id);

    User getUserByName(String username);

    void update(long id, User updatedUser);

    void deleteUserById(long id);

    void register(User user);

}
