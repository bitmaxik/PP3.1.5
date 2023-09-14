package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;

@Controller
public class AdminController {

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public AdminController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping(value = "/admin")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin";
    }

    @GetMapping("/user/{id}")
    public String showOneUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/show";
    }

    @GetMapping("/new")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/new")
    public String performRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "new";
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "/edit";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "edit";

        userService.update(id, user);
        return "redirect:/admin";

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
