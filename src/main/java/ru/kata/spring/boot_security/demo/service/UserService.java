package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByName(String username);

    User getUser(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);
}
