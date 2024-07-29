package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Component
public class Test {

    private final UserService userService;

    @Autowired
    public Test(UserService userService) {
        this.userService = userService;
    }

    @Bean
    @Transactional
    public void addUsersInBD() {

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");

        Role userRole = new Role();
        userRole.setName("ROLE_USER");

        User user = new User();
        user.setFirstName("admin");
        user.setAge(30);
        user.setLastName("Adminovich");
        user.setEmail("admin@mail.ru");
        user.setPassword("123");
        user.addRole(adminRole);
        user.addRole(userRole);

        User user1 = new User();
        user1.setFirstName("user");
        user1.setAge(36);
        user1.setLastName("Userevich");
        user1.setEmail("user@mail.ru");
        user1.setPassword("user123");
        user1.addRole(userRole);

        userService.create(user);
        userService.create(user1);
    }
}
