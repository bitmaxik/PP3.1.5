package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    private final UserValidator userValidator;
    @Autowired
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
@GetMapping("/user")
    public String userPage(Model model, Principal principal){
        model.addAttribute("user", userService.getUserByName(principal.getName()));
        return "/user";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user){
        return "registration";
    }
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors())
            return "registration";
        userService.register(user);
        return "redirect:/login";

    }




}
