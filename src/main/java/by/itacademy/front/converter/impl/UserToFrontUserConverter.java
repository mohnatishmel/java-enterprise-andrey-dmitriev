package by.itacademy.front.converter.impl;

import by.itacademy.entities.front.FrontCredential;
import by.itacademy.entities.front.FrontPersonalInformation;
import by.itacademy.entities.front.FrontRole;
import by.itacademy.entities.front.FrontUser;
import by.itacademy.entities.user.Role;
import by.itacademy.entities.user.User;
import by.itacademy.front.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserToFrontUserConverter implements Converter<User, FrontUser> {

    @Override
    public FrontUser convert(User user) {

        Collection<FrontRole> roles = new RoleToFrontRoleConverter().convert((Collection<Role>) user.getAuthorities());
        FrontCredential credential = new FrontCredential(user.getLogin());
        FrontPersonalInformation personalInformation =
                new PersonalInformationToFrontPersonalInformationConverter().convert(user.getPersonalInformation());
        return FrontUser.builder()
                .accountNotLocked(user.isAccountNotLocked())
                .id(user.getId())
                .credential(credential)
                .personalInformation(personalInformation)
                .roles(roles)
                .build();
    }
}
