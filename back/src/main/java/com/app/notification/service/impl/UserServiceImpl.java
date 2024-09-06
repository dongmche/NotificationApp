package com.app.notification.service.impl;

import com.app.notification.repository.RoleRepository;
import com.app.notification.repository.UserRepository;
import com.app.notification.service.UserService;
import com.app.notification.statics.Statics;
import com.app.notification.dto.UserDto;
import com.app.notification.entity.Role;
import com.app.notification.entity.User;
import com.app.notification.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private static final String ADMIN = Statics.PRIVILEGED_USER;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public Boolean createUser(UserDto userDto) {

        User user = UserMapper.mapToUser(userDto);


        Role role = roleRepository.findByName(ADMIN);
        if(role == null){
            role = checkRoleExist(ADMIN);
        }


        // encrypt user password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return true;
    }




    @Override
    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user !=  null){
            return UserMapper.mapToUserDto(user);
        }
        return null;
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        userRepository.save(UserMapper.mapToUser(userDto));
        return true;
    }

    @Override
    public boolean returnBook(String email, long bookId) {
        return false;
    }

    @Override
    public List<UserDto> findAllUser() {
        return userRepository.findAll().stream()
                .map(user -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(long id) {
        User user = userRepository.findById(id).get();
        if(user != null){
            return UserMapper.mapToUserDto(user);
        }
        return null;
    }

    private Role checkRoleExist(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);
        return role;
    }
}
