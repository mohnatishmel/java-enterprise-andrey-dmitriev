package by.itacademy.front.converter.impl;

import by.itacademy.entities.front.FrontRole;
import by.itacademy.entities.user.Role;
import by.itacademy.front.converter.Converter;

import java.util.Collection;
import java.util.HashSet;

public class RoleToFrontRoleConverter implements Converter<Collection<Role>, Collection<FrontRole>> {

    @Override
    public Collection<FrontRole> convert(Collection<Role> roles) {
        Collection<FrontRole> frontRoles = new HashSet<>();
        for (Role role : roles) {
            String roleName = role.getAuthority();
            FrontRole frontRole = new FrontRole(roleName);
            frontRoles.add(frontRole);
        }
        return frontRoles;
    }
}
