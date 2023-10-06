package ru.kata.spring.boot_security.demo.config;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;

    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @PostConstruct
    public void addAdmin() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.save(roleAdmin);
        roleService.save(roleUser);
        Set<Role> adminSet = new HashSet<>();
        adminSet.add(roleAdmin);

        Set<Role> userSet = new HashSet<>();
        userSet.add(roleUser);

        User admin = new User("admin", "admin", 20, "123", adminSet);
        userService.saveUser(admin);

        User user = new User("user", "user", 20, "123", userSet);
        userService.saveUser(user);
    }
}
