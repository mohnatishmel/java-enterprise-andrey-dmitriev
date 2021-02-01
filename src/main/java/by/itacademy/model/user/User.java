package by.itacademy.model.user;

import by.itacademy.security.GrantedAuthority;
import by.itacademy.security.UserDetails;

import java.util.Collection;

public class User implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
