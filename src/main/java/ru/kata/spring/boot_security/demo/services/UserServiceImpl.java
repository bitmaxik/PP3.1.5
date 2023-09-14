package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Нет пользователя с указанным Id");
        }
        return user.get();
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    @Transactional
    public void update(long id, User updatedUser) {
        User userToBeUpdated = getUserById(id);
        userToBeUpdated.setUsername(updatedUser.getUsername());
        userToBeUpdated.setAge(updatedUser.getAge());
        userToBeUpdated.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        userToBeUpdated.setRoles(updatedUser.getRoles());
    }

    @Override
    public void deleteUserById(long id) {
        if (userRepository.findById(id).isPresent())
            userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
