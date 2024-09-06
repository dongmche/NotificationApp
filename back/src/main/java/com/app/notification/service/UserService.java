package com.app.notification.service;

import com.app.notification.dto.UserDto;

import java.util.List;

public interface UserService {
    public Boolean createUser(UserDto userDto);

    UserDto findUserByEmail(String email);

    boolean updateUser(UserDto userDto);
    boolean returnBook(String email, long bookId);

    List<UserDto> findAllUser();

    UserDto findById(long id);
}
