package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ru.kata.spring.boot_security.demo.utils.ErrorsUtil.returnError;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    private final UserService userService;

    private final RoleService roleService;


    @Autowired
    public MyRESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> showAllUser() {
        return ResponseEntity.ok((userService.findAll()
                .stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new NoSuchUserException("User with ID = " + id + " not found in database");
        }
        return ResponseEntity.ok(mapToUserDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            returnError(bindingResult);

        User user = mapToUser(userDTO);
        userService.create(user);
        return ResponseEntity.ok(mapToUserDTO(user));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            returnError(bindingResult);
        }
        User user = mapToUser(userDTO);
        userService.updateUser(user.getId(), user);
        return ResponseEntity.ok(mapToUserDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {

        User userToDelete = userService.findById(id);
        if (userToDelete == null) {
            throw new NoSuchUserException("User with ID = " + id + " not found in database");
        }
        userService.delete(userToDelete.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAge(user.getAge());

        List<String> roles = user.getRoles().stream().map(Role::getName).toList();
        userDTO.setRoles(roles);
        return userDTO;
    }

    private User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAge(userDTO.getAge());

        for (Role role : roleService.findAll()) {
            for (String roleDto : userDTO.getRoles()) {
                if (role.getName().equals(roleDto)) {
                    user.addRole(role);
                }
            }
        }
        return user;
    }

}
