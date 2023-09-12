package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "/admin";
    }

//    @GetMapping("/{id}")
//    public String showOneUser(@PathVariable("id") long id, Model model) {
//        model.addAttribute("person", userService.getUserById(id));
//        return "/admin";
//    }
//
//    @DeleteMapping ("{/id}")
//    public String deleteUserById(@PathVariable("id") long id){
//        userService.deleteById(id);
//        return "/admin";
// }
}
