package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("usersList", userService.findAll());
        model.addAttribute("rolesList", roleService.findAll());
        return "/admin/admin_page";
    }

    @PostMapping("/addNewUser")
    public String saveUser(User user) {
        userService.create(user);

        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id, User user) {
        userService.updateUser(user.getId(), user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long userId) {
        userService.delete(userId);
        return "redirect:/admin";
    }
}
