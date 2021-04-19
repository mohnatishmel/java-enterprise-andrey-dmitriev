package by.itacademy.front.converter.impl;

import by.itacademy.entities.front.FrontUser;
import by.itacademy.entities.user.User;
import by.itacademy.front.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class UserListToFrontUserListConverter implements Converter<List<User>, List<FrontUser>> {

    private Converter<User, FrontUser> converter = new UserToFrontUserConverter();

    @Override
    public List<FrontUser> convert(List<User> users) {
        List<FrontUser> frontUsers = new ArrayList<>();
        users.forEach(user -> frontUsers.add(converter.convert(user)));
        return frontUsers;
    }
}
