package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUser(Model model) {
        List<User> allUsers = userService.getAllUsers();
        User userAuth = userService.getAuthUser();
        List<Role> allRoles = userService.getAllRoles();

        model.addAttribute("users", allUsers);
        model.addAttribute("user", userAuth);
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/edit/{id}")
    public String editUser(@ModelAttribute("usEdit") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
