package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUsers(ModelMap model) {
        model.addAttribute("usersList", userService.getAllUsers());
        return "admin/admin_page";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", userService.getAllRole());
        return "admin/new_user_form";
    }

    @PostMapping("/addNewUser/save")
    public String saveUser(@ModelAttribute("user") User user) {
            userService.saveUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/updateInfo{id}")
    public String changeUser(@RequestParam("id") Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("role", userService.getAllRole());
        return "admin/new_user_form";
    }

    @GetMapping("/deleteUser{id}")
    public String deleteUser(@RequestParam("id") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin";
    }

    @GetMapping("/info")
    public String info(Principal principal, ModelMap model) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("username", user);
        return "user/user_info";
    }

}
