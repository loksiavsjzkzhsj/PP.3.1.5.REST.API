package ru.kata.spring.boot_security.demo.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;


public class UserMapper {

//     private final RoleService roleService;
//     private final ModelMapper mapper;
//     User user;
//     UserDTO userDTO;
//
//    @Autowired
//    public UserMapper(RoleService roleService, ModelMapper mapper) {
//        this.roleService = roleService;
//        this.mapper = mapper;
//    }
//
//    public UserDTO toDTO(User user) {
//        return Objects.isNull(user) ? null : mapper.map(user, UserDTO.class);
//    }
//
//    public User toUser(UserDTO userDTO) {
//        return Objects.isNull(userDTO) ? null : mapper.map(userDTO, User.class);
//    }
//
//    @PostConstruct
//    public void setupMapper() {
//        mapper.createTypeMap(User.class, UserDTO.class)
//                .addMappings(m -> m.skip(UserDTO::setRoles)).setPostConverter(toDtoConvertor());
//        mapper.createTypeMap(UserDTO.class, User.class)
//                .addMappings(m ->m.skip(User :: setRoles)).setPostConverter(toUserConvertor());
//    }
//
//    public Converter<User, UserDTO> toDtoConvertor() {
//        return context -> {
//            User source = context.getSource();
//            UserDTO destination = context.getDestination();
//            mapSpecificFields(source, destination);
//            return context.getDestination();
//        };
//    }
//
//    public Converter<UserDTO, User> toUserConvertor() {
//        return context -> {
//            UserDTO source = context.getSource();
//            User destination = context.getDestination();
//            mapSpecificFields(source, destination);
//            return context.getDestination();
//        };
//    }
//
//    public void mapSpecificFields(User source, UserDTO destination) {
//        List<String> roles = source.getRoles().stream().map(Role :: getName).toList();
//        destination.setRoles(roles);
//    }
//
//    public void mapSpecificFields (UserDTO source, User destination) {
//        for (Role role : roleService.findAll()) {
//            for (String roleSource : source.getRoles()) {
//                if (role.getName().equals(roleSource)) {
//                    destination.addRole(role);
//                }
//            }
//        }
//    }
}
