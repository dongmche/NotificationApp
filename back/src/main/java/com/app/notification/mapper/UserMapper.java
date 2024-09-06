package com.app.notification.mapper;

import com.app.notification.dto.UserDto;
import com.app.notification.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        // split a name
//        String[] name = user.getName().split(" ");
//        userDto.setFirstName(name[0]);
//        userDto.setLastName(name[1]);

        userDto.setName(user.getName());


        if(user.getId() != null) {
            userDto.setId(user.getId());
        }

        return userDto;
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User();
//        user.setName(userDto.getFirstName() + " " + userDto.getLastName());

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());


        if (userDto.getId() != null) {
            user.setId(userDto.getId());
        }

        user.setPassword(userDto.getPassword());
        return user;
    }


}
